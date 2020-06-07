package com.mndt.ghost.mndtivpokemon.search;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mndt.ghost.mndtivpokemon.adapter.ImageAdapter;
import com.mndt.ghost.mndtivpokemon.dataview.MobSaveView;
import com.mndt.ghost.mndtivpokemon.Data;
import com.mndt.ghost.mndtivpokemon.R;
import com.mndt.ghost.mndtivpokemon.mobdata.Mob;

import java.io.File;
import java.io.FilenameFilter;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/9/1.
 */
public class MobSaveSearch extends Activity implements SearchView.OnQueryTextListener {

    private SearchView pokemonName;
    private ListView lvMain;
    Map filePos = new HashMap();
    File[] fileList;
    String[] fileName;
    String[] showName;
    ImageAdapter adapter;
    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Init();
    }

    private void Init() {
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("9684A7A09E3EA2991F6F3B1537F54012").build();
        mAdView.loadAd(adRequest);
        pokemonName = (SearchView)findViewById(R.id.search_search);
        lvMain = (ListView)findViewById(R.id.list_search_list);
        lvMain.setBackgroundColor(Color.argb(200, 247, 10, 113));
        InitAllPath();
        adapter = new ImageAdapter(this, GetInteger(), showName);
        lvMain.setAdapter(adapter);
        lvMain.setTextFilterEnabled(true);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Data.SAVE_NAME = fileName[(int)filePos.get(adapter.getItem(position).toString())];
                Intent it = new Intent(MobSaveSearch.this, MobSaveView.class);
                startActivity(it);
                MobSaveSearch.this.finish();
            }
        });
        pokemonName.setIconifiedByDefault(false);
        pokemonName.setOnQueryTextListener(this);
        pokemonName.setSubmitButtonEnabled(true);
        pokemonName.setQueryHint("搜尋");
    }

    Integer[] GetInteger() {
        try {
            int len = fileName.length;
            Integer[] integer = new Integer[len];
            showName = new String[len];
            for (int file_pos = 0; file_pos < len; file_pos++) {
                int pos = 0;
                String save_value = "";
                while (fileName[file_pos].charAt(pos) != '_') {
                    save_value += fileName[file_pos].charAt(pos);
                    pos++;
                }
                showName[file_pos] = splitString(fileName[file_pos], pos);
                integer[file_pos] = Mob.mobImage[Integer.valueOf(save_value)];
                filePos.put(showName[file_pos], file_pos);
            }
            return integer;
        } catch (Exception ex) {
            return new Integer[0];
        }
    }

    public String splitString(String name, int pos) {
        String save = "名稱: ";
        pos++;
        while(name.charAt(pos) != '_') {
            save += name.charAt(pos);
            pos++;
        }
        pos++;
        save += "\nIV值: ";
        while(name.charAt(pos) != '_') {
            save += name.charAt(pos);
            pos++;
        }
        save += "\n檔名:";
        pos++;
        save = save + name.substring(pos, name.length() - 5);
        return save;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter.getFilter().filter(newText);
        return false;
    }

    public void InitAllPath() {
        FilenameFilter namefilter = new FilenameFilter(){
            private String[] filter = { ".MNDT" };
            @Override
            public boolean accept(File dir, String filename){
                for(int i = 0; i < filter.length; i++){
                    if(filename.indexOf(filter[i]) != -1)
                        return true;
                }
                return false;
            }
        };
        try{
            File filePath = new File(Data.PATH);
            //String str = filePath.getName();取得檔案夾名稱
            //textFileName.setText(str);
            fileList = filePath.listFiles(namefilter);
            fileName = new String[fileList.length];
            for(int i = 0; i < fileList.length; i++){
                fileName[i] = fileList[i].getName();
            }
        }catch(Exception e) {
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            finish();
        }
        return true;
    }
}
