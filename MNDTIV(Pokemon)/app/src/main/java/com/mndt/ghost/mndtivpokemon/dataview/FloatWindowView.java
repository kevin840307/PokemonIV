package com.mndt.ghost.mndtivpokemon.dataview;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mndt.ghost.mndtivpokemon.Data;
import com.mndt.ghost.mndtivpokemon.DrawAngle;
import com.mndt.ghost.mndtivpokemon.FloatWindowManager;
import com.mndt.ghost.mndtivpokemon.IVCompute;
import com.mndt.ghost.mndtivpokemon.MainActivity;
import com.mndt.ghost.mndtivpokemon.R;
import com.mndt.ghost.mndtivpokemon.WindowServer;
import com.mndt.ghost.mndtivpokemon.mobdata.Mob;
import com.mndt.ghost.mndtivpokemon.search.MobAppraiseSearch;
import com.mndt.ghost.mndtivpokemon.search.MobNameSearch;
import com.mndt.ghost.mndtivpokemon.search.MobSkillSearch;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;

public class FloatWindowView extends LinearLayout {

    public static int viewWidth, viewHeight, statusBarHeight;
    private float xInScreen, yInScreen, xDownInScreen, yDownInScreen, xInView, yInView;
    private android.view.WindowManager windowManager;
    private android.view.WindowManager.LayoutParams mParams;
    static Context c;

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    public FloatWindowView(Context context) {
        super(context);
        windowManager = (android.view.WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        LayoutInflater.from(context).inflate(R.layout.float_window_small_layout, this);
        c = context;
        View view = findViewById(R.id.small_window_layout);
        LinearLayout.LayoutParams layout_params = new LinearLayout.LayoutParams(Data.WIDTH, Data.HEIGHT);
        view.setLayoutParams(layout_params);
        readPicture();
        Bitmap bm = Data.PICTURE;
        BitmapDrawable bd = new BitmapDrawable(getResources(), bm);
        viewWidth = Data.WIDTH;
        viewHeight = Data.HEIGHT;
        view.setBackground(bd);
    }

    public void readPicture() {
        try {
            File file = new File(Data.PIC_DIR_PATH);
            Bitmap bm = BitmapFactory.decodeFile(file.getPath());
            if (bm.getWidth() < 2 && bm.getHeight() < 2) {
                BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(R.drawable.joy_5);
                Bitmap bm2 = bd.getBitmap();
                Data.PICTURE = bm2;
                MainActivity.writePicture();
            } else {
                Data.PICTURE = bm;
            }
        } catch (Exception ex) {
            BitmapDrawable bd = (BitmapDrawable) getResources().getDrawable(R.drawable.joy_5);
            Bitmap bm = bd.getBitmap();
            Data.PICTURE = bm;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        xInScreen = event.getRawX();
        yInScreen = event.getRawY() - getStatusBarHeight();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                xInView = event.getX();
                yInView = event.getY();
                xDownInScreen = event.getRawX();
                yDownInScreen = event.getRawY() - getStatusBarHeight();
                break;
            case MotionEvent.ACTION_MOVE:
                updateViewPosition();
                break;
            case MotionEvent.ACTION_UP:
                if (xDownInScreen + 10 >= xInScreen && yDownInScreen + 10 >= yInScreen && xDownInScreen - 10 <= xInScreen && yDownInScreen - 10 <= yInScreen) {
                    openBigWindow();
                } else {
                    updateViewPosition();
                }
                break;
            default:
                break;
        }
        return true;
    }

    public void setParams(android.view.WindowManager.LayoutParams params) {
        mParams = params;
    }

    private void updateViewPosition() {
        if (mParams != null) {
            mParams.x = (int) (xInScreen - xInView);
            mParams.y = (int) (yInScreen - yInView);
            windowManager.updateViewLayout(this, mParams);
        }
    }

    private void openBigWindow() {
        synchronized (this) {
            FloatWindowManager.createPoint(getContext());
            FloatWindowManager.createBigWindow(getContext());
            FloatWindowManager.removeSmallWindow(getContext());
        }
    }

    private int getStatusBarHeight() {
        if (statusBarHeight == 0) {
            try {
                Class<?> c = Class.forName("com.android.internal.R$dimen");
                Object o = c.newInstance();
                Field field = c.getField("status_bar_height");
                int x = (Integer) field.get(o);
                statusBarHeight = getResources().getDimensionPixelSize(x);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return statusBarHeight;
    }

    public static Context getFloatContext() {
        return c;
    }

    public static class FloatWindowBigView extends LinearLayout {

        public static int viewWidth;
        public static int viewHeight;
        private View view;
        private Button close, back, setting, search, searchSkill1, searchSkill2, searchAppraise, savePokemon;
        private TextView viewPokemonName, viewSkill1, viewSkill2, viewPlayerLevel, viewMobLevel, viewStar, viewHp, viewCp, viewHpRange, viewCpRange, viewAppraise;
        private Button btnPlayerLevelLess, btnPlayerLevelAdd, btnMobLevelLess, btnMobLevelAdd, btnStarlLess, btnStarAdd, btnHpLess, btnHpAdd, btnCplLess, btnCpAdd;
        private SeekBar seekBarPlayerLevel, seekBarMobLevel, seekBarStar, seekBarHp, seekBarCp;
        AdView mAdView;
        AdRequest adRequest;

        public FloatWindowBigView(final Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.float_window_big_layout, this);
            init();

            btnPlayerLevelLess.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = getTextViewInt(viewPlayerLevel, 0) - 1;
                            viewPlayerLevel.setText(String.valueOf(save));
                            Data.PLAYER_LEVLE = save;
                            seekBarPlayerLevel.setProgress(save - 1);
                            DrawAngle.initAngles();
                            FloatWindowManager.updataPoint(context);
                        }
                    }
            );

            btnPlayerLevelAdd.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = getTextViewInt(viewPlayerLevel, 1) + 1;
                            viewPlayerLevel.setText(String.valueOf(save));
                            Data.PLAYER_LEVLE = save;
                            seekBarPlayerLevel.setProgress(save - 1);
                            DrawAngle.initAngles();
                            seekBarMobLevel.setMax((Data.PLAYER_LEVLE - 1) * 2 + 3);
                            FloatWindowManager.updataPoint(context);
                        }
                    }
            );

            seekBarPlayerLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int save = progress + 1;
                    viewPlayerLevel.setText(String.valueOf(save));
                    seekBarPlayerLevel.setProgress(save - 1);
                    Data.PLAYER_LEVLE = save;
                    DrawAngle.initAngles();
                    int mobLevle = (Data.PLAYER_LEVLE - 1) * 2 + 4;
                    if (getTextViewInt(viewMobLevel, 0) > mobLevle) {
                        viewMobLevel.setText(String.valueOf(mobLevle));
                        Data.DATA_LEVLE = String.valueOf(mobLevle);
                        seekBarMobLevel.setProgress(mobLevle);
                    }
                    seekBarMobLevel.setMax(mobLevle - 1);
                    FloatWindowManager.updataPoint(context);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            btnMobLevelLess.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = getTextViewInt(viewMobLevel, 0) - 1;
                            viewMobLevel.setText(String.valueOf(save));
                            Data.DATA_LEVLE = String.valueOf(save);
                            seekBarMobLevel.setProgress(save - 1);
                            FloatWindowManager.updataPoint(context);
                        }
                    }
            );

            btnMobLevelAdd.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = getTextViewInt(viewMobLevel, 1) + 1;
                            viewMobLevel.setText(String.valueOf(save));
                            Data.DATA_LEVLE = String.valueOf(save);
                            seekBarMobLevel.setProgress(save - 1);
                            FloatWindowManager.updataPoint(context);
                        }
                    }
            );

            seekBarMobLevel.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int save = progress + 1;
                    viewMobLevel.setText(String.valueOf(save));
                    Data.DATA_LEVLE = String.valueOf(save);
                    seekBarMobLevel.setProgress(save - 1);
                    FloatWindowManager.updataPoint(context);

                    int save_star = getStarPos(viewMobLevel);
                    viewStar.setText(Mob.mobStar[save_star]);
                    Data.DATA_STAR = Mob.mobStar[save_star];
                    seekBarStar.setProgress(save_star);

                    IVCompute.getCpRange();
                    IVCompute.getHpRange();
                    seekBarCp.setMax(Data.CP_RANGE[1] - Data.CP_RANGE[0]);
                    seekBarHp.setMax(Data.HP_RANGE[1] - Data.HP_RANGE[0]);
                    viewCpRange.setText(String.valueOf(Data.CP_RANGE[0]) + " ~ " + String.valueOf(Data.CP_RANGE[1]));
                    viewHpRange.setText(String.valueOf(Data.HP_RANGE[0]) + " ~ " + String.valueOf(Data.HP_RANGE[1]));
                    viewCp.setText(String.valueOf(Data.CP_RANGE[0]));
                    viewHp.setText(String.valueOf(Data.HP_RANGE[0]));
                    seekBarCp.setProgress(0);
                    seekBarHp.setProgress(0);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            btnStarlLess.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = seekBarStar.getProgress() - 1;
                            if (save < 0) save = 0;
                            viewStar.setText(Mob.mobStar[save]);
                            Data.DATA_STAR = Mob.mobStar[save];
                            seekBarStar.setProgress(save);
                        }
                    }
            );

            btnStarAdd.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = seekBarStar.getProgress() + 1;
                            if (save > 19) save = 19;
                            viewStar.setText(Mob.mobStar[save]);
                            Data.DATA_STAR = Mob.mobStar[save];
                            seekBarStar.setProgress(save);
                        }
                    }
            );

            seekBarStar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int save = progress;
                    viewStar.setText(Mob.mobStar[save]);
                    Data.DATA_STAR = Mob.mobStar[save];
                    seekBarStar.setProgress(save);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            btnCplLess.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = seekBarCp.getProgress() - 1;
                            if (save < 0) save = 0;
                            seekBarCp.setProgress(save);
                            save += Data.CP_RANGE[0];
                            viewCp.setText(String.valueOf(save));
                            Data.DATA_CP = String.valueOf(save);
                        }
                    }
            );

            btnCpAdd.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = seekBarCp.getProgress() + 1;
                            int range = Data.CP_RANGE[1] - Data.CP_RANGE[0];
                            if (save > range) save = range;
                            seekBarCp.setProgress(save);
                            save += Data.CP_RANGE[0];
                            viewCp.setText(String.valueOf(save));
                            Data.DATA_CP = String.valueOf(save);
                        }
                    }
            );

            seekBarCp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int save = progress + Data.CP_RANGE[0];
                    viewCp.setText(String.valueOf(save));
                    Data.DATA_CP = String.valueOf(save);
                    seekBarCp.setProgress(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });

            btnHpLess.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = seekBarHp.getProgress() - 1;
                            if (save < 0) save = 0;
                            seekBarHp.setProgress(save);
                            save += Data.HP_RANGE[0];
                            viewHp.setText(String.valueOf(save));
                            Data.DATA_HP = String.valueOf(save);
                        }
                    }
            );

            btnHpAdd.setOnClickListener(
                    new OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            int save = seekBarHp.getProgress() + 1;
                            int range = Data.HP_RANGE[1] - Data.HP_RANGE[0];
                            if (save > range) save = range;
                            seekBarHp.setProgress(save);
                            save += Data.HP_RANGE[0];
                            viewHp.setText(String.valueOf(save));
                            Data.DATA_HP = String.valueOf(save);
                        }
                    }
            );

            seekBarHp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    int save = progress + Data.HP_RANGE[0];
                    viewHp.setText(String.valueOf(save));
                    Data.DATA_HP = String.valueOf(save);
                    seekBarHp.setProgress(progress);
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            });


            search.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    FloatWindowManager.removePoint(getContext());
                    FloatWindowManager.removeBigWindow(getContext());
                    Intent it = new Intent(getContext(), MobNameSearch.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                }
            });

            close.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent it = new Intent(getContext(), WindowServer.class);
                    getContext().stopService(it);
                    FloatWindowManager.removeBigWindow(context);
                    FloatWindowManager.removePoint(context);
                    FloatWindowManager.removeSmallWindow(context);
                }
            });

            searchSkill1.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    FloatWindowManager.removePoint(getContext());
                    FloatWindowManager.removeBigWindow(getContext());
                    Data.SKILL_VAR = 1;
                    Intent it = new Intent(getContext(), MobSkillSearch.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                }
            });

            searchSkill2.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    FloatWindowManager.removePoint(getContext());
                    FloatWindowManager.removeBigWindow(getContext());
                    Data.SKILL_VAR = 2;
                    Intent it = new Intent(getContext(), MobSkillSearch.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                }
            });

            back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FloatWindowManager.createSmallWindow(context);
                    FloatWindowManager.removeBigWindow(context);
                    FloatWindowManager.removePoint(context);
                }
            });

            setting.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data.SETTING = 1;
                    FloatWindowManager.removeBigWindow(context);
                    FloatWindowManager.createSetting(context);
                }
            });

            savePokemon.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    FloatWindowManager.removePoint(getContext());
                    FloatWindowManager.removeBigWindow(getContext());
                    Intent it = new Intent(getContext(), MobIvView.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                }
            });

            searchAppraise.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    FloatWindowManager.removePoint(getContext());
                    FloatWindowManager.removeBigWindow(getContext());
                    Intent it = new Intent(getContext(), MobAppraiseSearch.class);
                    it.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(it);
                }
            });

        }

        private void init() {
            mAdView = (AdView) findViewById(R.id.adView);
            adRequest = new AdRequest.Builder().addTestDevice("9684A7A09E3EA2991F6F3B1537F54012").build();
            mAdView.loadAd(adRequest);
            view = findViewById(R.id.big_window_layout);
            viewWidth = view.getLayoutParams().width;
            viewHeight = view.getLayoutParams().height;
            close = (Button) findViewById(R.id.btn_big_stop);
            back = (Button) findViewById(R.id.btn_big_back);
            search = (Button) findViewById(R.id.search_search);
            setting = (Button) findViewById(R.id.btn_big_angle_setting);

            viewPokemonName = (TextView) findViewById(R.id.view_big_name);
            savePokemon = (Button) findViewById(R.id.btn_big_iv_view);
            searchSkill1 = (Button) findViewById(R.id.btn_big_search_skill1);
            searchSkill2 = (Button) findViewById(R.id.btn_big_search_skill2);
            viewSkill1 = (TextView) findViewById(R.id.view_big_skill1);
            viewSkill2 = (TextView) findViewById(R.id.view_big_skill2);
            viewPlayerLevel = (TextView) findViewById(R.id.view_big_player_level);
            viewMobLevel = (TextView) findViewById(R.id.view_big_mob_level);
            viewPlayerLevel = (TextView) findViewById(R.id.view_big_player_level);
            viewStar = (TextView) findViewById(R.id.view_big_star);
            viewCp = (TextView) findViewById(R.id.view_big_cp);
            viewHp = (TextView) findViewById(R.id.view_big_hp);
            viewCpRange = (TextView) findViewById(R.id.view_big_cp_range);
            viewHpRange = (TextView) findViewById(R.id.view_big_hp_range);
            viewAppraise = (TextView) findViewById(R.id.view_big_appraise);
            btnPlayerLevelLess = (Button) findViewById(R.id.btn_big_player_level_less);
            btnPlayerLevelAdd = (Button) findViewById(R.id.btn_big_player_level_add);
            btnMobLevelLess = (Button) findViewById(R.id.btn_big_mob_level_less);
            btnMobLevelAdd = (Button) findViewById(R.id.btn_big_mob_level_add);
            btnStarAdd = (Button) findViewById(R.id.btn_big_star_add);
            btnStarlLess = (Button) findViewById(R.id.btn_big_star_less);
            btnCpAdd = (Button) findViewById(R.id.btn_big_cp_add);
            btnCplLess = (Button) findViewById(R.id.btn_big_cp_less);
            btnHpAdd = (Button) findViewById(R.id.btn_big_hp_add);
            btnHpLess = (Button) findViewById(R.id.btn_big_hp_less);
            searchAppraise = (Button) findViewById(R.id.btn_big_appraise_search);
            seekBarStar = (SeekBar) findViewById(R.id.seekBar_big_star);
            seekBarCp = (SeekBar) findViewById(R.id.seekBar_big_cp);
            seekBarHp = (SeekBar) findViewById(R.id.seekBar_big_hp);
            seekBarPlayerLevel = (SeekBar) findViewById(R.id.seekBar_big_player_level);
            seekBarMobLevel = (SeekBar) findViewById(R.id.seekBar_big_mob_level);
            seekBarPlayerLevel.setMax(37);
            seekBarStar.setMax(19);
            readData();
        }

        int getStarPos(TextView text) {
            int save = Integer.valueOf(text.getText().toString());
            if (save % 4 == 0) {
                return (save / 4 - 1);
            }
            return save / 4;
        }

        int getTextViewInt(TextView text, int i) {
            int MAX = 10;
            if (text == viewPlayerLevel) MAX = 38;
            else if (text == viewStar) MAX = 20;
            else if (text == viewMobLevel) MAX = (Data.PLAYER_LEVLE - 1) * 2 + 4;
            int save = Integer.parseInt(text.getText().toString());
            if (save == 1) {
                if (i == 1) save = 1;
                else save = 2;
            } else if (save == MAX) {
                if (i == 0) save = MAX;
                else save = MAX - 1;
            }
            return save;
        }

        public void readData() {
            IVCompute.getCpRange();
            IVCompute.getHpRange();
            viewPokemonName.setText(Data.DATA_NAME);
            viewSkill1.setText(Data.DATA_SKILL1);
            viewSkill2.setText(Data.DATA_SKILL2);
            viewPlayerLevel.setText(String.valueOf(Data.PLAYER_LEVLE));
            viewMobLevel.setText(Data.DATA_LEVLE);
            seekBarPlayerLevel.setProgress(Data.PLAYER_LEVLE - 1);
            seekBarMobLevel.setMax((Data.PLAYER_LEVLE - 1) * 2 + 3);
            seekBarMobLevel.setProgress(Integer.valueOf(Data.DATA_LEVLE));
            seekBarStar.setProgress(Integer.valueOf(Data.DATA_STAR));
            viewStar.setText(String.valueOf(Data.DATA_STAR));
            viewCp.setText(Data.DATA_CP);
            viewHp.setText(Data.DATA_HP);
            viewCpRange.setText(String.valueOf(Data.CP_RANGE[0]) + " ~ " + String.valueOf(Data.CP_RANGE[1]));
            viewHpRange.setText(String.valueOf(Data.HP_RANGE[0]) + " ~ " + String.valueOf(Data.HP_RANGE[1]));
            seekBarCp.setProgress(Integer.valueOf(Data.DATA_CP) - Data.CP_RANGE[0]);
            seekBarHp.setProgress(Integer.valueOf(Data.DATA_HP) - Data.HP_RANGE[0]);
            seekBarCp.setMax(Data.CP_RANGE[1] - Data.CP_RANGE[0]);
            seekBarHp.setMax(Data.HP_RANGE[1] - Data.HP_RANGE[0]);
            viewAppraise.setText(Data.LEADER_ASK);
            IVCompute.getCpRange();
            IVCompute.getHpRange();

        }

    }
    public static class AnglesSetting extends LinearLayout {
        private int SEEK_MAX = 400;
        public static int viewWidth;
        public static int viewHeight;
        private View view;
        TextView textViewWidth, textViewHeight, textViewSize;
        private Button anglesWidthAdd, anglesWidthLess, anglesHeightAdd, anglesHeightLess, anglesSizeAdd, anglesSizeLess, setBack;
        SeekBar seekBarWidth, seekBarHeight, seekBarSize;
        AdView mAdView;
        AdRequest adRequest;

        public AnglesSetting(final Context context) {
            super(context);
            LayoutInflater.from(context).inflate(R.layout.angle_setting_layout, this);
            init();

            anglesWidthLess.setOnClickListener((new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int save = getTextViewInt(textViewWidth) - 1;
                    textViewWidth.setText(String.valueOf(save));
                    Data.ANGLES_WIDTH = save + Data.ANGLES_OFFSET;
                    seekBarWidth.setProgress(save);
                    FloatWindowManager.updataPoint(context);
                }
            }));

            anglesWidthAdd.setOnClickListener((new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int save = getTextViewInt(textViewWidth) + 1;
                    textViewWidth.setText(String.valueOf(save));
                    Data.ANGLES_WIDTH = save + Data.ANGLES_OFFSET;
                    seekBarWidth.setProgress(save);
                    FloatWindowManager.updataPoint(context);
                }
            }));

            seekBarWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    // TODO Auto-generated method stub
                    textViewWidth.setText(String.valueOf(progress));
                    Data.ANGLES_WIDTH = progress + Data.ANGLES_OFFSET;
                    FloatWindowManager.updataPoint(context);
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

            anglesHeightLess.setOnClickListener((new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int save = getTextViewInt(textViewHeight) - 1;
                    textViewHeight.setText(String.valueOf(save));
                    Data.ANGLES_HEIGHT = save;
                    FloatWindowManager.updataPoint(context);
                }
            }));

            anglesHeightAdd.setOnClickListener((new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int save = getTextViewInt(textViewHeight) + 1;
                    textViewHeight.setText(String.valueOf(save));
                    Data.ANGLES_HEIGHT = save;
                    FloatWindowManager.updataPoint(context);
                }
            }));

            seekBarHeight.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    // TODO Auto-generated method stub
                    textViewHeight.setText(String.valueOf(progress));
                    Data.ANGLES_HEIGHT = progress;
                    FloatWindowManager.updataPoint(context);
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

            anglesSizeLess.setOnClickListener((new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int save = getTextViewInt(textViewSize) - 1;
                    int save2 = getTextViewInt(textViewHeight) + 1;
                    textViewSize.setText(String.valueOf(save));
                    textViewHeight.setText(String.valueOf(save2));
                    Data.ANGLES_SIZE = save + Data.ANGLES_OFFSET;
                    Data.ANGLES_HEIGHT = save2;
                    FloatWindowManager.updataPoint(context);
                }
            }));


            anglesSizeAdd.setOnClickListener((new OnClickListener() {
                @Override
                public void onClick(View v) {
                    int save = getTextViewInt(textViewSize) + 1;
                    int save2 = getTextViewInt(textViewHeight) - 1;
                    textViewSize.setText(String.valueOf(save));
                    textViewHeight.setText(String.valueOf(save2));
                    Data.ANGLES_SIZE = save + Data.ANGLES_OFFSET;
                    Data.ANGLES_HEIGHT = save2;
                    FloatWindowManager.updataPoint(context);
                }
            }));

            seekBarSize.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

                @Override
                public void onProgressChanged(SeekBar seekBar, int progress,
                                              boolean fromUser) {
                    // TODO Auto-generated method stub
                    textViewSize.setText(String.valueOf(progress));
                    Data.ANGLES_SIZE = progress + Data.ANGLES_OFFSET;
                    FloatWindowManager.updataPoint(context);
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

            setBack.setOnClickListener((new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data.SETTING = 0;
                    writeData();
                    FloatWindowManager.createBigWindow(context);
                    FloatWindowManager.removeSetting(context);
                }
            }));

        }

        private void init() {
            mAdView = (AdView) findViewById(R.id.adView);
            adRequest = new AdRequest.Builder().addTestDevice("9684A7A09E3EA2991F6F3B1537F54012").build();
            mAdView.loadAd(adRequest);
            view = findViewById(R.id.angles_setting);
            viewWidth = view.getLayoutParams().width;
            viewHeight = view.getLayoutParams().height;
            anglesWidthLess = (Button) findViewById(R.id.btn_angle_width_less);
            anglesWidthAdd = (Button) findViewById(R.id.btn_angle_width_add);
            anglesHeightLess = (Button) findViewById(R.id.btn_angles_height_less);
            anglesHeightAdd = (Button) findViewById(R.id.btn_angle_height_add);
            anglesSizeLess = (Button) findViewById(R.id.btn_angle_size_less);
            anglesSizeAdd = (Button) findViewById(R.id.btn_angles_size_add);
            textViewWidth = (TextView) findViewById(R.id.view_angle_width);
            textViewHeight = (TextView) findViewById(R.id.view_angle_height);
            textViewSize = (TextView) findViewById(R.id.view_angle_size);
            seekBarWidth = (SeekBar) findViewById(R.id.seekBar_angle_width);
            seekBarHeight = (SeekBar) findViewById(R.id.seekBar_angle_height);
            seekBarSize = (SeekBar) findViewById(R.id.seekBar_angle_size);
            setBack = (Button) findViewById(R.id.btn_angle_setting_back);

            seekBarWidth.setMax(SEEK_MAX);
            seekBarHeight.setMax(SEEK_MAX);
            seekBarSize.setMax(SEEK_MAX);
            initData();
        }

        int getTextViewInt(TextView text) {
            int save = Integer.parseInt(text.getText().toString());
            if (save == 1) {
                save = 1;
            } else if (save == SEEK_MAX) {
                save = SEEK_MAX - 1;
            }
            return save;
        }

        void initData() {
            textViewWidth.setText(String.valueOf(Data.ANGLES_WIDTH - Data.ANGLES_OFFSET));
            seekBarWidth.setProgress(Data.ANGLES_WIDTH - Data.ANGLES_OFFSET);
            textViewHeight.setText(String.valueOf(Data.ANGLES_HEIGHT));
            seekBarHeight.setProgress(Data.ANGLES_HEIGHT);
            textViewSize.setText(String.valueOf(Data.ANGLES_SIZE - Data.ANGLES_OFFSET));
            seekBarSize.setProgress(Data.ANGLES_SIZE - Data.ANGLES_OFFSET);
            FloatWindowManager.updataPoint(getContext());
        }

        public void writeData() {
            FileWriter fw = null;
            BufferedWriter buff = null;
            try {
                fw = new FileWriter(Data.SETTING_PATH, false);
                buff = new BufferedWriter(fw);
                buff.write(String.valueOf(Data.ANGLES_WIDTH));
                buff.newLine();
                buff.write(String.valueOf(Data.ANGLES_HEIGHT));
                buff.newLine();
                buff.write(String.valueOf(Data.ANGLES_SIZE));
                buff.close();
                fw.close();
            } catch (IOException ex) {
            }
        }
    }
}