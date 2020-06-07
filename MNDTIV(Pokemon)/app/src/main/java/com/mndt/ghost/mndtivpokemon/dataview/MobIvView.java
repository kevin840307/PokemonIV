package com.mndt.ghost.mndtivpokemon.dataview;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mndt.ghost.mndtivpokemon.Data;
import com.mndt.ghost.mndtivpokemon.FloatWindowManager;
import com.mndt.ghost.mndtivpokemon.IVCompute;
import com.mndt.ghost.mndtivpokemon.R;
import com.mndt.ghost.mndtivpokemon.mobdata.Mob;
import com.mndt.ghost.mndtivpokemon.mobdata.MobSkillData;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by user on 2016/9/1.
 */
public class MobIvView extends Activity {
    TextView name, levle, hp, iv, skill1, skill2, bestSkill1, bestSkill2, star, cp;
    Button back, save;
    ImageView image;
    String saveName = "";
    AdView mAdView;
    AdRequest adRequest;
    int no = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.iv_data_layout);
        init();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FloatWindowManager.createPoint(getApplicationContext());
                FloatWindowManager.createBigWindow(getApplicationContext());
                finish();
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View item = LayoutInflater.from(MobIvView.this).inflate(R.layout.alert_dialog_edittext_layout, null);
                saveName = String.valueOf(Mob.mobNo.get(Data.DATA_NAME)) + "_" +
                        Data.DATA_NAME + "_" + Data.DATA_IV.substring(5) + "_";

                new AlertDialog.Builder(MobIvView.this)
                        .setTitle("請輸入檔名")
                        .setView(item)
                        .setPositiveButton("儲存", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                EditText editText = (EditText) item.findViewById(R.id.edit_alert_name);
                                if (canSave(editText.getText().toString())) {
                                    writeSaveData(saveName);
                                    Toast.makeText(getApplicationContext(), "儲存成功\n檔名: " + editText.getText().toString(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "儲存失敗\n可能檔名重複 ", Toast.LENGTH_SHORT).show();
                                }
                            }
                        })
                        .setNeutralButton("返回", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                            }
                        }).show();
            }
        });
    }

    void init() {
        mAdView = (AdView) findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().addTestDevice("9684A7A09E3EA2991F6F3B1537F54012").build();
        mAdView.loadAd(adRequest);
        image = (ImageView) findViewById(R.id.image_iv_mob_image);
        name = (TextView) findViewById(R.id.view_iv_name);
        levle = (TextView) findViewById(R.id.view_iv_levle);
        hp = (TextView) findViewById(R.id.view_iv_hp);
        iv = (TextView) findViewById(R.id.view_iv_iv);
        skill1 = (TextView) findViewById(R.id.view_iv_skill2);
        skill2 = (TextView) findViewById(R.id.view_iv_skill1);
        bestSkill1 = (TextView) findViewById(R.id.view_iv_best_skill1);
        bestSkill2 = (TextView) findViewById(R.id.view_iv_best_skill2);
        star = (TextView) findViewById(R.id.view_iv_star);
        cp = (TextView) findViewById(R.id.view_iv_cp);
        save = (Button) findViewById(R.id.btn_iv_save);
        back = (Button) findViewById(R.id.btn_iv_back);

        initImage();
        name.setText(Data.DATA_NAME);
        bestSkill();
        levle.setText(Data.DATA_LEVLE);
        skill1.setText(Data.DATA_SKILL1);
        skill2.setText(Data.DATA_SKILL2);
        iv.setText(getIVString());
        hp.setText(Data.DATA_HP);
        cp.setText(Data.DATA_CP);
        star.setText(Data.DATA_STAR);
    }

    String getIVString() {
        String iv1_string = "0% ~ 0%";
        String iv2_string = "0% ~ 0%";
        String iv3_string = "";
        String iv_string = "0% ~ 0%";
        String iv = "0% ~ 0%";
        IVCompute.getHpRange();
        IVCompute.getCpRange();
        try {
            int[] iv1 = IVCompute.GetIV();
            int[] iv2 = IVCompute.getIV3();
            int[] iv3 = IVCompute.getAppraise();
            int min, max;
            if (iv3[1] != 0) {
                min = (iv1[0] < iv2[0]) ? iv2[0] : iv1[0];
                max = (iv1[1] > iv2[1]) ? iv2[1] : iv1[1];
                min = (min < iv3[0] && min != 100 && max != 0) ? iv3[0] : min;
                max = (max > iv3[1] && min != 100 && max != 0) ? iv3[1] : max;
                iv3_string = "隊長評價: " + String.valueOf(iv3[0]) + "% ~ " + String.valueOf(iv3[1]) + "%\n";
            } else {
                min = (iv1[0] < iv2[0]) ? iv2[0] : iv1[0];
                max = (iv1[1] > iv2[1]) ? iv2[1] : iv1[1];
            }
            iv1_string = "公式1: " + String.valueOf(iv1[0]) + "% ~ " + String.valueOf(iv1[1]) + "%";
            iv2_string = "公式2: " + String.valueOf(iv2[0]) + "% ~ " + String.valueOf(iv2[1]) + "%";
            iv_string = "總和IV: " + String.valueOf(min) + "% ~ " + String.valueOf(max) + "%";
            iv = iv3_string + iv1_string + "\n" + iv2_string + "\n" + iv_string;
            Data.DATA_IV = iv_string;
            return iv;
        } catch (Exception e) {
            return iv;
        }

    }

    public boolean canSave(String str) {
        String name = saveName + str + ".MNDT";
        File f = new File(Data.PATH + name);
        if (f.exists()) {
            return false;
        }
        saveName = name;
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            FloatWindowManager.createPoint(getApplicationContext());
            FloatWindowManager.createBigWindow(getApplicationContext());
            finish();
        }
        return true;
    }

    void initImage() {
        try {
            no = (int) Mob.mobNo.get(Data.DATA_NAME);
            image.setImageResource(Mob.mobImage[no]);
        } catch (Exception ex) {
            no = 0;
            image.setImageResource(Mob.mobImage[0]);
        }
    }

    protected void bestSkill() {
        MobSkillData skill;
        try {
            skill = (MobSkillData) Mob.bestSkill.get(Data.DATA_NAME);
        } catch (Exception ex) {
            skill = null;
        }
        if (skill == null) {

            bestSkill1.setText("無推薦技能");
            bestSkill2.setText("進化 - > " + Mob.mobName[(int) Mob.mobNo.get(Data.DATA_NAME) + 1]);
        } else {
            bestSkill1.setText(skill.bestSkill1);
            bestSkill2.setText(skill.bestSkill2);
        }
    }

    void writeSaveData(String name) {
        FileWriter fw = null;
        BufferedWriter buff = null;
        try {
            fw = new FileWriter(Data.PATH + name, false);
            buff = new BufferedWriter(fw);
            buff.write(String.valueOf(no));
            buff.newLine();
            buff.write(Data.DATA_NAME);
            buff.newLine();
            buff.write(Data.DATA_LEVLE);
            buff.newLine();
            buff.write(Data.DATA_HP);
            buff.newLine();
            buff.write(Data.DATA_CP);
            buff.newLine();
            buff.write(Data.DATA_STAR);
            buff.newLine();
            buff.write(Data.DATA_SKILL1);
            buff.newLine();
            buff.write(Data.DATA_SKILL2);
            buff.newLine();
            buff.write(Data.DATA_IV);
            buff.close();
            fw.close();
        } catch (IOException ex) {
        }
    }
}
