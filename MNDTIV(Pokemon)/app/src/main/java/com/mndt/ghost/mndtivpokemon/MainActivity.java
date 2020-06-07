package com.mndt.ghost.mndtivpokemon;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mndt.ghost.mndtivpokemon.dataview.BuiltPictureView;
import com.mndt.ghost.mndtivpokemon.dataview.TeachView;
import com.mndt.ghost.mndtivpokemon.search.MobSaveSearch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MainActivity extends Activity {
    private final int SYSTEM_ALERT_WINDOW_CODE = 1234;
    private int SEEK_MAX = 1000;
    private final String IMAGE_TYPE = "image/*";
    private final int IMAGE_CODE = 0;
    private TextView textViewImageWidth, textViewImageHeight;
    private Button startFloatWindow, btnSetPicture, stopFloatWindow, viewPokemon, btnBuiltPicture, btnTeach;
    private Button imageWidthLess, imageWidthAdd,imageHeightLess, imageHeightAdd;
    private SeekBar seekBarImageWidth, seekBarImageHeight;
    private DisplayMetrics displayMetrics;
    boolean isFinish = true;
    AdView mAdView;
    AdRequest adRequest;

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Init();
        checkAPI();

        imageHeightLess.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int save = getTextViewInt(textViewImageHeight) - 1;
                textViewImageHeight.setText(String.valueOf(save));
                Data.HEIGHT = save;
                seekBarImageHeight.setProgress(save);
            }
        }));


        imageHeightAdd.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int save = getTextViewInt(textViewImageHeight) + 1;
                textViewImageHeight.setText(String.valueOf(save));
                Data.HEIGHT = save;
                seekBarImageHeight.setProgress(save);
            }
        }));

        seekBarImageHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                textViewImageHeight.setText(String.valueOf(progress));
                Data.HEIGHT = progress;
                seekBarImageHeight.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        imageWidthLess.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int save = getTextViewInt(textViewImageWidth) - 1;
                textViewImageWidth.setText(String.valueOf(save));
                Data.WIDTH = save;
                seekBarImageWidth.setProgress(save);
            }
        }));


        imageWidthAdd.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int save = getTextViewInt(textViewImageWidth) + 1;
                textViewImageWidth.setText(String.valueOf(save));
                Data.WIDTH = save;
                seekBarImageWidth.setProgress(save);
            }
        }));

        seekBarImageWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,
                                          boolean fromUser) {
                // TODO Auto-generated method stub
                textViewImageWidth.setText(String.valueOf(progress));
                Data.WIDTH = progress;
                seekBarImageWidth.setProgress(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }
        });

        startFloatWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                writeData();
                Data.WIDTH = Integer.parseInt(textViewImageWidth.getText().toString());
                Data.HEIGHT = Integer.parseInt(textViewImageHeight.getText().toString());
                Intent it = new Intent(MainActivity.this, WindowServer.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startService(it);
                readData();
            }
        });

        stopFloatWindow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, WindowServer.class);
                stopService(it);
                FloatWindowManager.removeBigWindow(getApplicationContext());
                FloatWindowManager.removePoint(getApplicationContext());
                FloatWindowManager.removeSmallWindow(getApplicationContext());
            }
        });

        viewPokemon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, MobSaveSearch.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
            }
        });

        btnBuiltPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, BuiltPictureView.class);
                startActivity(it);
                isFinish = false;
            }
        });

        btnSetPicture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                setImage();
                isFinish = false;
            }
        });

        btnTeach.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(MainActivity.this, TeachView.class);
                it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(it);
                isFinish = false;
            }
        });
    }

    @TargetApi(23)
    public void checkAPI() {
        if(Build.VERSION.SDK_INT >= 23){
            if (! Settings.canDrawOverlays(MainActivity.this)) {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                        Uri.parse("package:" + getPackageName()));
                startActivityForResult(intent,10);
            }
        }
        else {
        }
    }

    @Override
    protected void onStop() {
        if(isFinish) {
            MainActivity.this.finish();
        }
        super.onStop();
    }

    @Override
    protected void onStart() {
        isFinish = true;
        super.onStart();
    }

    int getTextViewInt(TextView text) {
        int save = Integer.parseInt(text.getText().toString());
        if(save == 0 ) {
            save = 1;
        } else if(save == SEEK_MAX) {
            save = SEEK_MAX - 1;
        }
        return save;
    }

    void Init() {
        mAdView = (AdView) findViewById(R.id.adView);
        adRequest = new AdRequest.Builder().addTestDevice("9684A7A09E3EA2991F6F3B1537F54012").build();
        mAdView.loadAd(adRequest);
        startFloatWindow = (Button) findViewById(R.id.start_float_window);
        stopFloatWindow = (Button)findViewById(R.id.stop_float_window);
        btnSetPicture = (Button) findViewById(R.id.btn_select_picture);
        textViewImageHeight = (TextView) findViewById(R.id.view_image_height);
        textViewImageWidth = (TextView)findViewById(R.id.view_image_width);
        imageHeightLess = (Button)findViewById(R.id.btn_image_height_less);
        imageHeightAdd = (Button)findViewById(R.id.btn_image_height_add);
        imageWidthLess = (Button)findViewById(R.id.btn_image_width_less);
        imageWidthAdd = (Button)findViewById(R.id.btn_image_width_add);
        btnBuiltPicture = (Button)findViewById(R.id.btn_built_picture);
        viewPokemon = (Button)findViewById(R.id.btn_search_save_mob);
        btnTeach = (Button)findViewById(R.id.btn_teach);
        seekBarImageHeight = (SeekBar)findViewById(R.id.seekBar_image_height);
        seekBarImageWidth = (SeekBar)findViewById(R.id.seekBar_image_width);
        seekBarImageHeight.setMax(SEEK_MAX);
        seekBarImageWidth.setMax(SEEK_MAX);
        displayMetrics = this.getResources().getDisplayMetrics();
        Data.HEIGHT_PIXELS = displayMetrics.heightPixels;
        Data.WIDTH_PIXELS = displayMetrics.widthPixels;
        Data.dataInit();
        readData();
        filePokemonData();
    }

    private void setImage() {
        Intent getAlbum = new Intent(Intent.ACTION_GET_CONTENT);
        getAlbum.setType(IMAGE_TYPE);
        startActivityForResult(getAlbum, IMAGE_CODE);
    }

    public static void writePicture() {
        try {
            File file = new File(Data.PIC_DIR_PATH);
            FileOutputStream out = new FileOutputStream(file);
            Data.PICTURE.compress ( Bitmap. CompressFormat.PNG , 90 , out);
            out.flush ();
            out.close ();
        } catch (FileNotFoundException e) {
            e.printStackTrace ();
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public void readData() {
        String str;
        FileReader fr = null;
        BufferedReader buff = null;
        try {
            fr = new FileReader(Data.PIC_DATA_PATH);
            buff = new BufferedReader(fr);
            if ((str = buff.readLine()) != null) {
                textViewImageHeight.setText(str);
                seekBarImageHeight.setProgress(Integer.valueOf(str));
                if ((str = buff.readLine()) != null) {
                    seekBarImageWidth.setProgress(Integer.valueOf(str));
                    textViewImageWidth.setText(str);
                }
            }
            fr.close();
        } catch (IOException ex) {
            seekBarImageHeight.setProgress(200);
            seekBarImageWidth.setProgress(200);
            textViewImageHeight.setText("200");
            textViewImageWidth.setText("200");
        }
    }

    public void writeData() {
        FileWriter fw = null;
        BufferedWriter buff = null;
        try {
            fw = new FileWriter(Data.PIC_DATA_PATH, false);
            buff = new BufferedWriter(fw);
            buff.write(textViewImageHeight.getText().toString());
            buff.newLine();
            buff.write(textViewImageWidth.getText().toString());
            buff.close();
            fw.close();
        } catch (IOException ex) {
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        doNext(requestCode,grantResults);
    }

    private void doNext(int requestCode, int[] grantResults) {
        if (requestCode == SYSTEM_ALERT_WINDOW_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
            } else {
                // Permission Denied
            }
        }
    }

    protected void filePokemonData() {
        String path = "/data/data/com.example.user.flybutton/pokemondata/s.cpp";
        FileWriter fw = null;
        BufferedWriter buff = null;
        try {
            fw = new FileWriter(path, false);
            buff = new BufferedWriter(fw);
            buff.close();
            fw.close();
        } catch (IOException ex) {
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            return;
        }
        Bitmap bm = null;
        ContentResolver resolver = getContentResolver();
        if (requestCode == IMAGE_CODE) {
            try {
                Uri originalUri = data.getData();
                bm = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                Data.PICTURE = bm;
                writePicture();
            }
            catch (IOException e) {
            }
        }
        if (requestCode == 10) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(MainActivity.this,"not granted", Toast.LENGTH_SHORT);
            }
        }
    }
}