package com.mndt.ghost.mndtivpokemon;

import android.graphics.Bitmap;
import android.util.DisplayMetrics;

import com.mndt.ghost.mndtivpokemon.mobdata.Mob;
import com.mndt.ghost.mndtivpokemon.mobdata.MobDataBase;
import com.mndt.ghost.mndtivpokemon.mobdata.MobNo;
import com.mndt.ghost.mndtivpokemon.mobdata.MobSkill;
import com.mndt.ghost.mndtivpokemon.mobdata.MobStar;

/**
 * Created by user on 2016/8/24.
 */
public class Data {
    public static String PIC_DIR_PATH = "/data/data/com.mndt.ghost.mndtivpokemon/Image.png";
    public static String PIC_DATA_PATH = "/data/data/com.mndt.ghost.mndtivpokemon/ImageData.GP";
    public static String SETTING_PATH = "/data/data/com.mndt.ghost.mndtivpokemon/SettingData.GP";
    public static String PATH = "/data/data/com.mndt.ghost.mndtivpokemon/";
    public static Bitmap PICTURE = null;
    public static int SETTING = 0;
    public static int HEIGHT = 200;
    public static int WIDTH = 200;
    public static int HEIGHT_PIXELS = 720;
    public static int WIDTH_PIXELS = 1180;
    public static int ANGLES_WIDTH = WIDTH_PIXELS / 9 * 8;
    public static int ANGLES_HEIGHT = WIDTH_PIXELS - ANGLES_WIDTH;
    public static int ANGLES_SIZE = (HEIGHT_PIXELS / 11) * 6;
    public static int ANGLES_OFFSET = ANGLES_WIDTH - 150;
    public static int[] CP_RANGE = {10, 10};
    public static int[] HP_RANGE = {10, 10};
    public static int PLAYER_LEVLE = 2;
    public static int SKILL_VAR = 1;
    public static String DATA_NAME = "Bulbasaur(妙蛙種子)";
    public static String DATA_LEVLE = "1";
    public static String DATA_STAR = "200";
    public static String DATA_HP = "11";
    public static String DATA_CP = "10";
    public static String DATA_IV = "0";
    public static String DATA_SKILL1 = "MoveUnset(無技能)";
    public static String DATA_SKILL2 = "MoveUnset(無技能)";
    public static String LEADER_ASK = "Unknow";
    public static String SAVE_NAME = "Unknow";

    public static final double[] CpM = {0.0939999967813492, 0.135137432089339, 0.166397869586945, 0.192650913155325, 0.215732470154762, 0.236572651424822, 0.255720049142838, 0.273530372106572, 0.290249884128571, 0.306057381389863
            , 0.321087598800659, 0.335445031996451, 0.349212676286697, 0.362457736609939, 0.375235587358475, 0.387592407713878, 0.399567276239395, 0.4111935532161, 0.422500014305115, 0.432926420512509, 0.443107545375824
            , 0.453059948165049, 0.46279838681221, 0.472336085311278, 0.481684952974319, 0.490855807179549, 0.499858438968658, 0.5087017489616, 0.517393946647644, 0.525942516110322, 0.534354329109192, 0.542635753803599
            , 0.550792694091797, 0.558830584490385, 0.566754519939423, 0.57456912814537, 0.582278907299042, 0.589887907888945, 0.597400009632111, 0.604823648665171, 0.61215728521347, 0.619404107958234, 0.626567125320435
            , 0.633649178748576, 0.6406529545784, 0.647580971386554, 0.654435634613037, 0.661219265805859, 0.667934000492096, 0.674581885647492, 0.681164920330048, 0.687684901255373, 0.694143652915955, 0.700542901033063
            , 0.706884205341339, 0.713169074873823, 0.719399094581604, 0.725575586915154, 0.731700003147125, 0.734741038550429, 0.737769484519958, 0.740785579737136, 0.743789434432983, 0.746781197247765, 0.749761044979095
            , 0.752729099732281, 0.75568550825119, 0.758630370209851, 0.761563837528229, 0.76448604959218, 0.767397165298462, 0.770297293677362, 0.773186504840851, 0.776064947064992, 0.778932750225067, 0.781790050767666
            , 0.784636974334717, 0.787473608513275, 0.790300011634827};

    public static int[] picture = {
            R.drawable.joy_1,
            R.drawable.joy_2,
            R.drawable.joy_3,
            R.drawable.joy_4,
            R.drawable.joy_5,
            R.drawable.joy_6,
            R.drawable.joy_7,
            R.drawable.joy_8,
            R.drawable.joy_9,
            R.drawable.joy_10,
            R.drawable.joy_11,
            R.drawable.joy_12,
    };


    public static void dataInit() {
        Mob.init();
        ANGLES_WIDTH = WIDTH_PIXELS / 9 * 8;
        ANGLES_HEIGHT = WIDTH_PIXELS - ANGLES_WIDTH + 20;
        ANGLES_SIZE = (HEIGHT_PIXELS / 11) * 6;
        ANGLES_OFFSET = ANGLES_WIDTH - 150;
    }

}
