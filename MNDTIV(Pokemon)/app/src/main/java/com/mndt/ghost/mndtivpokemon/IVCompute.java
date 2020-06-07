package com.mndt.ghost.mndtivpokemon;

import com.mndt.ghost.mndtivpokemon.mobdata.Mob;
import com.mndt.ghost.mndtivpokemon.mobdata.MobDataBaseData;

/**
 * Created by user on 2016/9/4.
 */
public class IVCompute {
    public static int GetStatIV(int hp, double Ecpm, double pok_base) {
        int save_iv = 0;
        for (double iv = 0; iv <= 15; iv++) {
            save_iv = (int) (Ecpm * (pok_base + iv));
            if (hp == save_iv) {
                if (((double) hp + 0.22) >= (Ecpm * (pok_base + iv + 1)) && ((double) hp) <= (Ecpm * (pok_base + iv + 1))) {
                    return (int) (iv + 1);
                }
                return (int) iv;
            }
        }
        return 0;
    }

    public static int[] GetIV() {
        int[] iv_range = {100, 0};
        int cp = Integer.valueOf(Data.DATA_CP);
        int hp = Integer.valueOf(Data.DATA_HP);
        MobDataBaseData id = (MobDataBaseData) Mob.dataBase.get(Data.DATA_NAME);
        double[] pok_base = {id.sta, id.att, id.def};
        int levle = (int) Mob.mobStarLevle.get(Data.DATA_STAR) - 1;
        double max = 1.5;
        int check = levle;
        while (check > 75) {
            max -= 0.5;
            check--;
        }

        for (double offset = 0; offset <= max; offset += 0.5) {
            double Ecpm = Data.CpM[levle + (int) (offset * 2)];
            int stat_iv = GetStatIV(hp, Ecpm, pok_base[0]);
            for (double def = 0; def <= 15; def++) {
                for (double att = 0; att <= 15; att++) {
                    int save_cp = (int) (((pok_base[1] + att) * Math.pow((pok_base[2] + def), 0.5) *
                            Math.pow((pok_base[0] + (double) stat_iv), 0.5) * Math.pow(Ecpm, 2)) / 10);
                    //int save_cp = (int)( Math.Pow(((pok_base[0] + (double)stat_iv) * (pok_base[2] + def)), 0.5) * (pok_base[1] + att) * Math.Pow(Ecpm, 2) / 10);
                    if (cp - 2 <= save_cp && cp + 4 >= save_cp) {
                        int iv = (int) (((double) stat_iv + def + att) / 45 * 100);
                        if (iv_range[1] < iv) iv_range[1] = iv;
                        if (iv_range[0] > iv) iv_range[0] = iv;
                    }
                }
            }
        }
        return iv_range;
    }

    public static int[] getHpRange() {
        int[] hp_range = {10, 10};
        try {
            MobDataBaseData id = (MobDataBaseData) Mob.dataBase.get(Data.DATA_NAME);
            double[] pok_base = {id.sta, id.att, id.def};
            int levle = Integer.valueOf(Data.DATA_LEVLE) - 1;

            double Ecpm = Data.CpM[levle];
            hp_range[0] = (int) (Ecpm * (pok_base[0]));
            hp_range[1] = (int) (Ecpm * (pok_base[0] + 15));
            if (hp_range[0] < 10) hp_range[0] = 10;
            if (hp_range[1] < 10) hp_range[1] = 10;
            Data.HP_RANGE = hp_range;
            return hp_range;
        } catch (Exception ex) {
            return hp_range;
        }
    }

    public static int[] getCpRange() {
        int[] cp_range = {10, 10};
        try {
            System.out.println(Data.DATA_NAME);
            System.out.println(Data.DATA_NAME);
            System.out.println(Data.DATA_NAME);
            MobDataBaseData id = (MobDataBaseData) Mob.dataBase.get(Data.DATA_NAME);
            double[] pok_base = {id.sta, id.att, id.def};
            System.out.println(id.sta);
            System.out.println(id.sta);
            System.out.println(id.sta);
            int levle = Integer.valueOf(Data.DATA_LEVLE) - 1;
            double Ecpm = Data.CpM[levle];

            cp_range[0] = (int) ((pok_base[1] * Math.pow(pok_base[2], 0.5) *
                    Math.pow(pok_base[0], 0.5) * Math.pow(Ecpm, 2)) / 10);
            cp_range[1] = (int) (((pok_base[1] + 15) * Math.pow((pok_base[2] + 15), 0.5) *
                    Math.pow((pok_base[0] + 15), 0.5) * Math.pow(Ecpm, 2)) / 10);

            if (cp_range[0] < 10) cp_range[0] = 10;
            if (cp_range[1] < 10) cp_range[1] = 10;
            Data.CP_RANGE = cp_range;
            return cp_range;
        } catch (Exception ex) {
            return cp_range;
        }
    }

    public static String getIV2() {
        String iv;
        double hp_iv = (Double.parseDouble(Data.DATA_HP) - (double) Data.HP_RANGE[0]) / (double) (Data.HP_RANGE[1] - Data.HP_RANGE[0]);
        double att_def_iv = (Double.parseDouble(Data.DATA_CP) - (double) Data.CP_RANGE[0]) / (double) (Data.CP_RANGE[1] - Data.CP_RANGE[0]);
        iv = String.valueOf((int) ((hp_iv * 15.0 + ((att_def_iv) * 30.0)) / 45.0 * 100.0)) + "%";
        return iv;
    }

    public static int[] getIV3() {
        int[] iv_range = {100, 0};
        int cp = Integer.valueOf(Data.DATA_CP);
        int hp = Integer.valueOf(Data.DATA_HP);
        MobDataBaseData id = (MobDataBaseData) Mob.dataBase.get(Data.DATA_NAME);
        double[] pok_base = {id.sta, id.att, id.def};
        int levle = Integer.valueOf(Data.DATA_LEVLE) - 1;

        double Ecpm = Data.CpM[levle];
        int stat_iv = GetStatIV(hp, Ecpm, pok_base[0]);
        for (double def = 0; def <= 15; def++) {
            for (double att = 0; att <= 15; att++) {
                int save_cp = (int) (((pok_base[1] + att) * Math.pow((pok_base[2] + def), 0.5) *
                        Math.pow((pok_base[0] + (double) stat_iv), 0.5) * Math.pow(Ecpm, 2)) / 10);
                //int save_cp = (int)( Math.Pow(((pok_base[0] + (double)stat_iv) * (pok_base[2] + def)), 0.5) * (pok_base[1] + att) * Math.Pow(Ecpm, 2) / 10);
                if (cp - 1 <= save_cp && cp + 4 >= save_cp) {
                    int iv = (int) (((double) stat_iv + def + att) / 45 * 100);
                    if (iv_range[1] < iv) iv_range[1] = iv;
                    if (iv_range[0] > iv) iv_range[0] = iv;
                }
            }
        }
        if (iv_range[0] == 100 && iv_range[1] == 0) iv_range[0] = 0;
        return iv_range;
    }

    public static int[] getAppraise() {
        int[] leader = {0, 0};
        String leaderAsk = Data.LEADER_ASK;
        if (leaderAsk == "Unknow") {

        } else if (leaderAsk == "best 黃隊(82%~100%)" || leaderAsk == "anything 紅隊(82%~100%)" || leaderAsk == "wonder 藍隊(82%~100%)") {
            leader[0] = 82;
            leader[1] = 100;
        } else if (leaderAsk == "strong 黃隊(66~80%)" || leaderAsk == "strong 紅隊(66~80%)" || leaderAsk == "attention 藍隊(66~80%)") {
            leader[0] = 66;
            leader[1] = 80;
        } else if (leaderAsk == "decent! 黃隊(51~64%)" || leaderAsk == "decent 紅隊(51~64%)" || leaderAsk == "average 藍隊(51~64%)") {
            leader[0] = 51;
            leader[1] = 64;
        } else if (leaderAsk == "has room 黃隊(0~50%)" || leaderAsk == "not 紅隊(0~50%)" || leaderAsk == "not 藍隊(0~50%)") {
            leader[0] = 0;
            leader[1] = 50;
        }
        return leader;
    }
}
