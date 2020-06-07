package com.mndt.ghost.mndtivpokemon.search;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mndt.ghost.mndtivpokemon.Data;
import com.mndt.ghost.mndtivpokemon.FloatWindowManager;
import com.mndt.ghost.mndtivpokemon.dataview.FloatWindowView;
import com.mndt.ghost.mndtivpokemon.IVCompute;
import com.mndt.ghost.mndtivpokemon.adapter.ImageAdapter;
import com.mndt.ghost.mndtivpokemon.R;
import com.mndt.ghost.mndtivpokemon.mobdata.Mob;

/**
 * Created by user on 2016/8/24.
 */

public class MobNameSearch extends Activity implements SearchView.OnQueryTextListener {
    private SearchView pokemonName;
    private ListView lvMain;
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
        adapter = new ImageAdapter(this, GetInteger(), Mob.mobName);
        lvMain.setAdapter(adapter);
        lvMain.setTextFilterEnabled(true);
        lvMain.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                synchronized(this) {
                    Data.DATA_NAME = adapter.getItem(position).toString();
                    IVCompute.getCpRange();
                    IVCompute.getHpRange();
                    Data.DATA_CP = String.valueOf(Data.CP_RANGE[0]);
                    Data.DATA_HP = String.valueOf(Data.HP_RANGE[0]);
                    FloatWindowManager.createPoint(FloatWindowView.getFloatContext());
                    MobNameSearch.this.finish();
                    FloatWindowManager.createBigWindow(FloatWindowView.getFloatContext());
                }
            }
        });
        pokemonName.setIconifiedByDefault(false);
        pokemonName.setOnQueryTextListener(this);
        pokemonName.setSubmitButtonEnabled(true);
        pokemonName.setQueryHint("搜尋");
    }

    Integer[] GetInteger() {
        Integer[] integer = new Integer[151];
        for(int pos = 0; pos < 151; pos++) {
            integer[pos] = Mob.mobImage[pos];
        }
        return integer;
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
