package com.mndt.ghost.mndtivpokemon.mobdata;

/**
 * Created by user on 2016/9/6.
 */
public class Mob extends MobAppraise {
    public static void init() {
        MobSkill.skillInit();
        MobDataBase.baseInit();
        MobNo.noInit();
        MobStar.starInit();
    }
}
