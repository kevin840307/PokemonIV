package com.mndt.ghost.mndtivpokemon.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mndt.ghost.mndtivpokemon.Data;
import com.mndt.ghost.mndtivpokemon.FloatWindowManager;
import com.mndt.ghost.mndtivpokemon.dataview.FloatWindowView;
import com.mndt.ghost.mndtivpokemon.R;
import com.mndt.ghost.mndtivpokemon.mobdata.Mob;

/**
 * Created by user on 2016/8/24.
 */

public class MobSkillSearch extends Activity implements SearchView.OnQueryTextListener {
    private SearchView pokemon_name;
    private ListView lv_main;
    ArrayAdapter adapter;
    AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_layout);
        Init();
        lv_main.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                if(Data.SKILL_VAR == 1) Data.DATA_SKILL1 = adapter.getItem(position).toString();
                else if(Data.SKILL_VAR == 2) Data.DATA_SKILL2 = adapter.getItem(position).toString();
                    FloatWindowManager.createPoint(FloatWindowView.getFloatContext());
                    MobSkillSearch.this.finish();
                    FloatWindowManager.createBigWindow(FloatWindowView.getFloatContext());
            }
        });
        pokemon_name.setIconifiedByDefault(false);
        pokemon_name.setOnQueryTextListener(this);
        pokemon_name.setSubmitButtonEnabled(true);
        pokemon_name.setQueryHint("搜尋");
    }

    private void Init() {
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("9684A7A09E3EA2991F6F3B1537F54012").build();
        mAdView.loadAd(adRequest);
        pokemon_name = (SearchView)findViewById(R.id.search_search);
        lv_main = (ListView)findViewById(R.id.list_search_list);
        adapter = new ArrayAdapter<>(
                getApplicationContext(), android.R.layout.simple_list_item_1,
                Mob.mobSkill);
        lv_main.setAdapter(adapter);
        lv_main.setTextFilterEnabled(true);
       // lv_main.setBackgroundColor(getResources().getColor(R.color.transparent_pink));
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

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return true;
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return true;
    }
}
