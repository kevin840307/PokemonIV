package com.mndt.ghost.mndtivpokemon.mobdata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/9/6.
 */
public class MobStar extends MobImage{

    public static Map mobStarLevle = new HashMap();

    public static String[] mobStar = {
            "200",
            "400",
            "600",
            "800",
            "1000",
            "1300",
            "1600",
            "1900",
            "2200",
            "2500",
            "3000",
            "3500",
            "4000",
            "4500",
            "5000",
            "6000",
            "7000",
            "8000",
            "9000",
            "10000"
    };

    public static void starInit() {
        int len = mobStar.length;
        int level = 1;
        for (int pos = 0; pos < len; pos++) {
            mobStarLevle.put(mobStar[pos], level);
            level += 4;
        }
    }
}
