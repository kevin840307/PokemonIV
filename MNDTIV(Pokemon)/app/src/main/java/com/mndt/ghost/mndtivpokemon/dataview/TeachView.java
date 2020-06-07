package com.mndt.ghost.mndtivpokemon.dataview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;

import com.mndt.ghost.mndtivpokemon.R;

/**
 * Created by user on 2016/9/7.
 */
public class TeachView extends Activity{
    ImageView imageStep1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.teach_layout);
        init();
    }

    void init() {

    }
}
