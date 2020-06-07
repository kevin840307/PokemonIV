package com.mndt.ghost.mndtivpokemon.dataview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mndt.ghost.mndtivpokemon.Data;
import com.mndt.ghost.mndtivpokemon.R;
import com.mndt.ghost.mndtivpokemon.mobdata.Mob;
import com.mndt.ghost.mndtivpokemon.mobdata.MobSkillData;
import com.mndt.ghost.mndtivpokemon.search.MobSaveSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by user on 2016/9/5.
 */
public class MobSaveView extends Activity {

    TextView name, levle, hp, iv, skill1, skill2, bestSkill1, bestSkill2, star, cp;
    Button back, delete;
    ImageView image;
    int no = 0;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.save_view_layout);
        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MobSaveView.this, MobSaveSearch.class);
                startActivity(it);
                finish();
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(MobSaveView.this)
                        .setTitle("MNDT IV")
                        .setMessage("確定要刪除嗎?")
                        .setPositiveButton("確定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                try {
                                    deletePokemon();
                                    finish();
                                    Toast.makeText(getApplicationContext(), "刪除成功", Toast.LENGTH_SHORT).show();
                                } catch (Exception ex) {
                                    Toast.makeText(getApplicationContext(), "刪除失敗", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }});
    }

    void init() {
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("9684A7A09E3EA2991F6F3B1537F54012").build();
        mAdView.loadAd(adRequest);
        name = (TextView) findViewById(R.id.view_save_name);
        levle = (TextView) findViewById(R.id.view_save_levle);
        hp = (TextView) findViewById(R.id.view_save_hp);
        iv = (TextView) findViewById(R.id.view_save_iv);
        skill1 = (TextView) findViewById(R.id.view_save_skill2);
        skill2 = (TextView) findViewById(R.id.view_save_skill1);
        bestSkill1 = (TextView) findViewById(R.id.view_save_bast_skill1);
        bestSkill2 = (TextView) findViewById(R.id.view_save_bast_skill2);
        image = (ImageView) findViewById(R.id.image_pokemon_save);
        star = (TextView) findViewById(R.id.view_save_star);
        cp = (TextView) findViewById(R.id.view_save_cp);
        delete = (Button) findViewById(R.id.btn_save_delete);
        back = (Button) findViewById(R.id.btn_save_back);

        readData();
        initImage();
        bestSkill();
    }

    void initImage() {
        try {
            image.setImageResource(Mob.mobImage[no]);
        } catch (Exception ex) {
            image.setImageResource(Mob.mobImage[0]);
        }
    }

    protected void bestSkill() {
        MobSkillData skill;
        try {
            skill = (MobSkillData) Mob.bestSkill.get(name.getText().toString());
            bestSkill1.setText(skill.bestSkill1);
            bestSkill2.setText(skill.bestSkill2);
        } catch (Exception ex) {
            bestSkill1.setText("無推薦技能");
            bestSkill2.setText("進化 - > " + Mob.mobName[(int) Mob.mobNo.get(name.getText().toString()) + 1]);
        }
    }

    void readData() {
        FileReader fw = null;
        BufferedReader buff = null;
        String str;
        try {
            fw = new FileReader(Data.PATH + Data.SAVE_NAME);
            buff = new BufferedReader(fw);
            if ((str = buff.readLine()) != null) {
                no = Integer.valueOf(str);
                name.setText(buff.readLine());
                levle.setText(buff.readLine());
                hp.setText(buff.readLine());
                cp.setText(buff.readLine());
                star.setText(buff.readLine());
                skill1.setText(buff.readLine());
                skill2.setText(buff.readLine());
                iv.setText(buff.readLine());
            }
            buff.close();
            fw.close();
        } catch (IOException ex) {
            no = 0;
            name.setText("Unknow");
            levle.setText("Unknow");
            hp.setText("Unknow");
            cp.setText("Unknow");
            star.setText("Unknow");
            skill1.setText("Unknow");
            skill2.setText("Unknow");
            iv.setText("Unknow");
        }
    }

    void deletePokemon() {
        File file = new File(Data.PATH + Data.SAVE_NAME);
        file.delete();
    }
}

