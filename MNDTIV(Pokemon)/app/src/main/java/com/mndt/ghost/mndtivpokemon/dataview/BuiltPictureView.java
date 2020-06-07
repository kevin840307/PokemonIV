package com.mndt.ghost.mndtivpokemon.dataview;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import com.mndt.ghost.mndtivpokemon.Data;
import com.mndt.ghost.mndtivpokemon.MainActivity;
import com.mndt.ghost.mndtivpokemon.R;
import com.mndt.ghost.mndtivpokemon.mobdata.Mob;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by user on 2016/9/5.
 */
public class BuiltPictureView extends Activity {

    GridView picturView;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.built_picture_layout);
        Init();
        List<Map<String, Object>> items = new ArrayList<Map<String,Object>>();
        for (int i = 0; i < Data.picture.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("image", Data.picture[i]);
            item.put("text", String.valueOf(i + 1));
            items.add(item);
        }
        SimpleAdapter adapter = new SimpleAdapter(this,
                items, R.layout.grid_item, new String[]{"image", "text"},
                new int[]{R.id.image_grid_view, R.id.view_grid_name});
        picturView.setAdapter(adapter);
        picturView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(Data.picture[position]);
                Bitmap bm2 = bd.getBitmap();
                Data.PICTURE = bm2;
                MainActivity.writePicture();
                finish();
            }

        });
    }

    void Init() {
        picturView = (GridView)findViewById(R.id.gridView_built_list);
        picturView.setNumColumns(3);
    }
}
