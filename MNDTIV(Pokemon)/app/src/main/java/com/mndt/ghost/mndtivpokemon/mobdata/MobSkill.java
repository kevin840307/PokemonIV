package com.mndt.ghost.mndtivpokemon.mobdata;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 2016/9/6.
 */
public class MobSkill extends MobName {

    public static Map bestSkill = new HashMap();

    public static String[] mobSkill = {
            "MoveUnset(無技能)",
            "Thunder Shock(電擊)",
            "QuickAttack(電光石火)",
            "Scratch(利爪)",
            "Ember(火花)",
            "VineWhip(藤鞭)",
            "Tackle(衝擊)",
            "Razor Leaf(飛葉快刀)",
            "Take Down(猛撞)",
            "Water Gun(水槍)",
            "Bite(咬下)",
            "Pound(拍擊)",
            "Double Slap(連環巴掌)",
            "Wrap(卷緊)",
            "HyperBeam(破壞光線)",
            "Lick(舌舔)",
            "DarkPulse(惡之波動)",
            "Smog(迷霧)",
            "Sludge(淤泥攻擊)",
            "MetalClaw(金屬爪擊)",
            "ViceGrip(夾擊)",
            "FlameWheel(火焰輪轉攻擊)",
            "Megahorn(百萬角擊)",
            "WingAttack(翅膀拍擊)",
            "Flamethrower(火焰放射)",
            "SuckerPunch(偷襲)",
            "Dig(挖洞)",
            "LowKick(過肩摔)",
            "CrossChop(十字切)",
            "PsychoCut(幻象斬)",
            "Psybeam(精神光線)",
            "Earthquake(地震)",
            "StoneEdge(石刃)",
            "IcePunch(冷凍拳)",
            "HeartStamp(心靈壓迫)",
            "Discharge(放電)",
            "FlashCannon(光柵加農)",
            "Peck(啄擊)",
            "Drill Peck(衝鑽)",
            "IceBeam(冷凍光線)",
            "Blizzard(暴風雪)",
            "AirSlash(空氣砍)",
            "HeatWave(熱風)",
            "Twineedle(雙針)",
            "PoisonJab(毒突攻擊)",
            "AerialAce(燕返)",
            "DrillRun(鑽頭直擊)",
            "PetalBlizzard(花吹雪)",
            "MegaDrain(百萬吸取)",
            "BugBuzz(蟲鳴)",
            "PoisonFang(劇毒之牙)",
            "NightSlash(夜斬)",
            "Slash(斬擊)",
            "BubbleBeam(泡沫光線)",
            "Submission(地獄車)",
            "Karate Chop(手刀)",
            "LowSweep(掃堂腿)",
            "AquaJet(水流噴射)",
            "AquaTail(水之尾)",
            "SeedBomb(種子爆彈)",
            "Psyshock(幻象攻擊)",
            "RockThrow(落石)",
            "AncientPower(原始力量)",
            "RockTomb(岩石封)",
            "RockSlide(岩崩)",
            "PowerGem(力量寶石)",
            "ShadowSneak(影擊)",
            "ShadowPunch(暗影拳)",
            "ShadowClaw(暗影爪擊)",
            "OminousWind(妖風)",
            "ShadowBall(暗影球)",
            "BulletPunch(子彈拳)",
            "MagnetBomb(磁體炸彈)",
            "Steel Wing(鋼翼)",
            "IronHead(鐵頭槌)",
            "ParabolicCharge(拋物式充電)",
            "Spark(電火花)",
            "Thunder Punch(雷電掌)",
            "Thunder(雷電)",
            "Thunderbolt(十萬伏特)",
            "Twister(龍捲風)",
            "Dragon Breath(龍之息)",
            "Dragon Pulse(龍波動)",
            "Dragon Claw(龍爪)",
            "DisarmingVoice(魅音)",
            "DrainingKiss(吸收之吻)",
            "DazzlingGleam(魔法閃光)",
            "Moonblast(月爆)",
            "PlayRough(嬉鬧)",
            "Cross Poison(十字斬)",
            "Sludge Bomb(臭泥爆彈)",
            "Sludge Wave(淤泥波)",
            "Gunk Shot(灰塵射擊)",
            "Mud Shot(泥漿噴射)",
            "Bone Club(骨頭棍)",
            "Bulldoze(壓路)",
            "MudBomb(泥爆彈)",
            "FuryCutter(連續切)",
            "Bug Bite(蟲咬)",
            "SignalBeam(信號光線)",
            "X-Scissor(X-剪刀十字拳)",
            "FlameCharge(硝化衝鋒)",
            "FlameBurst(爆裂火焰)",
            "Fire Blast(大字爆)",
            "Brine(潮水)",
            "WaterPulse(水之波動)",
            "Scald(沸水)",
            "Hydro Pump (水砲)",
            "Psychic(幻象術)",
            "Psystrike(精神破壞)",
            "IceShard(冰之礫)",
            "IcyWind(冰凍之風)",
            "Frost Breath(冰吸)",
            "Absorb(Absorb)",
            "GigaDrain(億萬吸取)",
            "FirePunch(火焰拳)",
            "Solar Beam(陽光烈焰)",
            "Leaf Blade(刀葉)",
            "PowerWhip(強力鞭撻)",
            "Splash(水濺躍)",
            "Acid(溶解液)",
            "AirCutter(空氣刃)",
            "Hurricane(暴風)",
            "BrickBreak(瓦割)",
            "Cut(居合斬)",
            "Swift(迅星)",
            "HornAttack(角突)",
            "Stomp(踐踏)",
            "Headbutt(心靈壓迫)",
            "HyperFang(必殺門牙)",
            "Slam(摔打)",
            "BodySlam(壓制)",
            "Rest(睡眠)",
            "Struggle(拼命)",
            "ScaldBlastoise(沸水(水箭龜))",
            "HydroPumpBlastoise(水壓(水箭龜))",
            "WrapGreen(卷緊-綠)",
            "WrapPink(卷緊-粉)",
            "FuryCutterFast(連續切)",
            "BugBiteFast(蟲食)",
            "BiteFast(啃咬)",
            "SuckerPunchFast(偷襲)",
            "DragonBreathFast(龍之吐息)",
            "ThunderShockFast(電擊)",
            "SparkFast(電火花)",
            "LowKickFast(過肩摔)",
            "KarateChopFast(空手刀)",
            "EmberFast(火苗)",
            "WingAttackFast(翅膀拍擊)",
            "PeckFast(啄擊)",
            "LickFast(舌舔)",
            "ShadowClawFast(暗影爪擊)",
            "VineWhipFast(藤鞭攻擊)",
            "RazorLeafFast(飛葉斬)",
            "MudShotFast(泥漿噴射)",
            "IceShardFast(冰之礫)",
            "FrostBreathFast(冰之吐息)",
            "QuickAttackFast(電光石火)",
            "ScratchFast(抓擊)",
            "TackleFast(撞擊)",
            "PoundFast(拍打)",
            "CutFast(居合斬)",
            "PoisonJabFast(毒刺)",
            "AcidFast(溶解液)",
            "PsychoCutFast(精神切割)",
            "RockThrowFast(滾石)",
            "MetalClawFast(合金爪)",
            "BulletPunchFast(飛彈拳)",
            "WaterGunFast(水槍)",
            "SplashFast(水濺躍)",
            "WaterGunFastBlastoise(水槍)",
            "MudSlapFast(擲泥)",
            "Zen HeadbuttFast(意念頭錘)",
            "ConfusionFast(念力)",
            "PoisonStingFast(毒針)",
            "BubbleFast(泡泡)",
            "FeintAttackFast(虛晃一招)",
            "SteelWingFast(鋼之翼)",
            "FireFangFast(炎牙)",
            "RockSmashFast(岩石粉碎)"
    };

    public static void skillInit() {
        MobSkillData pokemonSkill;

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Vine Whip 藤鞭(10.77)";
        pokemonSkill.bestSkill2 = "Solar Beam 陽光烈焰(24.49)";
        bestSkill.put("Venusaur(妙蛙花)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Wing Attack 翅膀攻擊(12)";
        pokemonSkill.bestSkill2 = "Fire Blast 大字爆(24.39)";
        bestSkill.put("Charizard(噴火龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Water Gun 水槍(12)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Blastoise(水箭龜)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bug Bite 蟲咬(11.11)";
        pokemonSkill.bestSkill2 = "Bug Buzz 蟲鳴(17.65)";
        bestSkill.put("Butterfree(巴大蝴)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Poison Jab 毒刺(11.43)";
        pokemonSkill.bestSkill2 = "X-Scissor 臭泥爆彈(21.15)";
        bestSkill.put("Beedrill(大針蜂)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Wing Attack 翅膀攻擊(12)";
        pokemonSkill.bestSkill2 = "Hurricane 颶風(25)";
        bestSkill.put("Pidgeot(比鵰)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bite 咬下(12)";
        pokemonSkill.bestSkill2 = "Hyper Beam 破壞死光(24)";
        bestSkill.put("Raticate(拉達)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Steel Wing 鋼翼(11.28)";
        pokemonSkill.bestSkill2 = "Drill Peck 衝鑽(14.81)";
        bestSkill.put("Fearow(大嘴雀)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bite 咬下(12)";
        pokemonSkill.bestSkill2 = "Gunk Shot 灰塵射擊(21.67)";
        bestSkill.put("Arbok(阿柏怪)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Spark 閃電(10)";
        pokemonSkill.bestSkill2 = "Thunder 打雷(23.26)";
        bestSkill.put("Raichu(雷丘)", pokemonSkill);


        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Mud Shot 泥漿噴射(10.91)";
        pokemonSkill.bestSkill2 = "Earthquake 地震(23.81)";
        bestSkill.put("Sandslash(穿山王)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Poison Jab 毒刺(11.43)";
        pokemonSkill.bestSkill2 = "Stone Edge 尖石攻擊(25.81)";
        bestSkill.put("Nidoqueen(尼多后)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Poison Jab 毒刺(11.43)";
        pokemonSkill.bestSkill2 = "Earthquake 地震(23.81)";
        bestSkill.put("Nidoking(尼多王)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Pound 拍擊(12.96)";
        pokemonSkill.bestSkill2 = "Moonblast 月爆(20.73)";
        bestSkill.put("Clefable(皮可西)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Ember 火花(9.52)";
        pokemonSkill.bestSkill2 = "Fire Blast 大字爆(24.39)";
        bestSkill.put("Ninetales(九尾)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Pound 拍擊(12.96)";
        pokemonSkill.bestSkill2 = "Hyper Beam 破壞死光(24)";
        bestSkill.put("Wigglytuff(胖可丁)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Wing Attack 翅膀攻擊(12)";
        pokemonSkill.bestSkill2 = "Poison Fang 毒牙(10.42)";
        bestSkill.put("Golbat(大嘴蝠)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Razor Leaf 飛葉快刀(10.34)";
        pokemonSkill.bestSkill2 = "Solar Beam 陽光烈焰(24.49)";
        bestSkill.put("Vileplume(霸王花)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bug Bite 蟲咬(11.11)";
        pokemonSkill.bestSkill2 = "Solar Beam 陽光烈焰(24.49)";
        bestSkill.put("Parasect(派拉斯特)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bug Bite 蟲咬(11.11)";
        pokemonSkill.bestSkill2 = "Bug Buzz 蟲鳴(17.65)";
        bestSkill.put("Venomoth(末入蛾)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Mud Shot 泥漿噴射(10.91)";
        pokemonSkill.bestSkill2 = "Stone Edge 尖石攻擊(25.81)";
        bestSkill.put("Dugtrio(三地鼠)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Scratch 利爪(12)";
        pokemonSkill.bestSkill2 = "Play Rough 嬉戲(18.97)";
        bestSkill.put("Persian(貓老大)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Water Gun 水槍(12)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Golduck(哥達鴨)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Low Kick 低空踢(8.33)";
        pokemonSkill.bestSkill2 = "Cross Chop 十字斬(30)";
        bestSkill.put("Primeape(火爆猴)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Fire Fang 焰牙(11.9)";
        pokemonSkill.bestSkill2 = "Fire Blast 大字爆(24.39)";
        bestSkill.put("Arcanine(風速狗)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bubble 泡泡(10.87)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Poliwrath(快泳蛙)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Psycho Cut 幻象斬(14.04)";
        pokemonSkill.bestSkill2 = "Psychic 幻象術(19.64)";
        bestSkill.put("Alakazam(胡地)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Karate Chop 手刀(7.5)";
        pokemonSkill.bestSkill2 = "Cross Chop 十字斬(30)";
        bestSkill.put("Machamp(怪力)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Razor Leaf 飛葉快刀(10.34)";
        pokemonSkill.bestSkill2 = "Solar Beam 陽光烈焰(24.49)";
        bestSkill.put("Victreebel(大食花)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bubble 泡泡(10.87)";
        pokemonSkill.bestSkill2 = "Water Pulse 水波動(10.61)";
        bestSkill.put("Tentacruel(毒刺水母)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Mud Shot 泥漿噴射(10.91)";
        pokemonSkill.bestSkill2 = "Stone Edge 尖石攻擊(25.81)";
        bestSkill.put("Golem(隆隆岩)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Ember 火花(9.52)";
        pokemonSkill.bestSkill2 = "Fire Blast 大字爆(24.39)";
        bestSkill.put("Rapidash(烈焰馬)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Water Gun 水槍(12)";
        pokemonSkill.bestSkill2 = "Psychic 幻象術(19.64)";
        bestSkill.put("Slowbro(呆河馬)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Spark 閃電(10)";
        pokemonSkill.bestSkill2 = "Flash Cannon 光澤電炮(15.38)";
        bestSkill.put("Magneton(三合一磁怪)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Cut 一字斬(9.02)";
        pokemonSkill.bestSkill2 = "Leaf Blade 刀葉(19.64)";
        bestSkill.put("Farfetchd(大蔥鴨)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Feint Attack 虛晃一招(11.54)";
        pokemonSkill.bestSkill2 = "Drill Peck 衝鑽(14.81)";
        bestSkill.put("Dodrio(嘟嘟利)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Frost Breath 冰吸(11.11)";
        pokemonSkill.bestSkill2 = "Blizzard 暴風雪(25.64)";
        bestSkill.put("Dewgong(白海獅)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Poison Jab 毒刺(11.43)";
        pokemonSkill.bestSkill2 = "X-Scissor X-剪刀十字拳(16.67)";
        bestSkill.put("Muk(臭臭泥)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Frost Breath 冰吸(11.11)";
        pokemonSkill.bestSkill2 = "Blizzard 暴風雪(25.64)";
        bestSkill.put("Cloyster(鐵甲貝)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Shadow Claw 影爪(11.58)";
        pokemonSkill.bestSkill2 = "Sludge Wave 淤泥波(20.59)";
        bestSkill.put("Gengar(耿鬼)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Rock Throw 岩石投擲(8.82)";
        pokemonSkill.bestSkill2 = "Stone Edge 光澤電炮(25.81)";
        bestSkill.put("Onix(大岩蛇)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Zen Headbutt 意念頭槌(11.43)";
        pokemonSkill.bestSkill2 = "Psychic 幻象術(19.64)";
        bestSkill.put("Hypno(素利拍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Metal Claw 合金爪(12.7)";
        pokemonSkill.bestSkill2 = "X-Scissor X-剪刀十字拳(16.67)";
        bestSkill.put("Kingler(巨鉗蟹)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Spark 閃電(10)";
        pokemonSkill.bestSkill2 = "Thunderbolt 十萬伏特(20.37)";
        bestSkill.put("Electrode(頑皮彈)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Zen Headbutt 意念頭槌(11.43)";
        pokemonSkill.bestSkill2 = "Solar Beam 陽光烈焰(24.49)";
        bestSkill.put("Exeggutor(椰蛋樹)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Zen Headbutt 意念頭槌(11.43)";
        pokemonSkill.bestSkill2 = "Psychic 幻象術(19.64)";
        bestSkill.put("Hypno(素利拍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Mud Slap 泥漿拍打(11.11)";
        pokemonSkill.bestSkill2 = "Earthquake 地震(23.81)";
        bestSkill.put("Marowak(嘎拉嘎拉)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Rock Smash 岩石粉碎(10.64)";
        pokemonSkill.bestSkill2 = "Stone Edge 尖石攻擊(25.81)";
        bestSkill.put("Hitmonlee(沙瓦郎)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Rock Smash 岩石粉碎(10.64)";
        pokemonSkill.bestSkill2 = "Thunder Punch 雷光掌(16.67)";
        bestSkill.put("Hitmonchan(艾比郎)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Zen Headbutt 意念頭槌(11.43)";
        pokemonSkill.bestSkill2 = "Hyper Beam 破壞死光(24)";
        bestSkill.put("Lickitung(大舌頭)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Acid 溶解液(9.52)";
        pokemonSkill.bestSkill2 = "Sludge Bomb 臭泥爆彈(21.15)";
        bestSkill.put("Weezing(雙彈瓦斯)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Mud Slap 泥漿拍打(11.11)";
        pokemonSkill.bestSkill2 = "Stone Edge 尖石攻擊(25.81)";
        bestSkill.put("Rhydon(鐵甲暴龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Pound 拍擊(12.96)";
        pokemonSkill.bestSkill2 = "Psychic 幻象術(19.64)";
        bestSkill.put("Chansey(吉利蛋)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Vine Whip 藤鞭(10.77)";
        pokemonSkill.bestSkill2 = "Solar Beam 陽光烈焰(24.49)";
        bestSkill.put("Tangela(蔓藤怪)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Mud Slap 泥漿拍打(11.11)";
        pokemonSkill.bestSkill2 = "Earthquake 地震(23.81)";
        bestSkill.put("Kangaskhan(袋龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Water Gun 水槍(12)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Seadra(海刺龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Poison Jab 毒刺(11.43)";
        pokemonSkill.bestSkill2 = "Megahorn 百萬噸角擊(25)";
        bestSkill.put("Seaking(金魚王)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Water Gun 水槍(12)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Starmie(寶石海星)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Zen Headbutt 意念頭槌(11.43)";
        pokemonSkill.bestSkill2 = "Psychic 幻象術(19.64)";
        bestSkill.put("Mr.Mime(吸盤魔偶)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Steel Wing 鋼翼(11.28)";
        pokemonSkill.bestSkill2 = "Bug Buzz 蟲鳴(17.65)";
        bestSkill.put("Scyther(飛天螳螂)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Frost Breath 冰吸(11.11)";
        pokemonSkill.bestSkill2 = "Psyshock 幻象攻擊(14.29)";
        bestSkill.put("Jynx(迷唇姐)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Thunder Shock 電擊(8.33)";
        pokemonSkill.bestSkill2 = "Thunder 打雷(23.26)";
        bestSkill.put("Electabuzz(電擊獸)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Tackle 衝擊(10.91)";
        pokemonSkill.bestSkill2 = "Earthquake 地震(23.81)";
        bestSkill.put("Pinsir(大甲)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Ember 火花(9.52)";
        pokemonSkill.bestSkill1 = "大字爆 (24.39)";
        bestSkill.put("Magmar(鴨嘴火龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bite 咬下(12)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Gyarados(暴鯉龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Frost Breath 冰吸(11.11)";
        pokemonSkill.bestSkill2 = "Blizzard 暴風雪(25.64)";
        bestSkill.put("Lapras(乘龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Pound 拍擊(12.96)";
        pokemonSkill.bestSkill2 = "Struggle 搏鬥(8.85)";
        bestSkill.put("Ditto(百變怪)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Water Gun 水槍(12)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Vaporeon(水精靈)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Thunder Shock 電擊(8.33)";
        pokemonSkill.bestSkill2 = "Thunder 打雷(23.26)";
        bestSkill.put("Jolteon(雷精靈)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Ember 火花(9.52)";
        pokemonSkill.bestSkill2 = "Fire Blast 大字爆(24.39)";
        bestSkill.put("Flareon(火精靈)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Tackle 衝擊(10.91)";
        pokemonSkill.bestSkill2 = "Discharge 放電(14)";
        bestSkill.put("Porygon(３Ｄ龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Water Gun 水槍(12)";
        pokemonSkill.bestSkill2 = "Hydro Pump 水砲(23.68)";
        bestSkill.put("Omastar(多刺菊石獸)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Mud Shot 泥漿噴射(10.91)";
        pokemonSkill.bestSkill2 = "Stone Edge 尖石攻擊(25.81)";
        bestSkill.put("Kabutops(鐮刀盔)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Bite 咬下(12)";
        pokemonSkill.bestSkill2 = "Hyper Beam 破壞死光(24)";
        bestSkill.put("Aerodactyl(化石翼龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Zen Headbutt 意念頭槌(11.43)";
        pokemonSkill.bestSkill2 = "Body Slam 泰山壓頂(25.64)";
        bestSkill.put("Snorlax(卡比獸)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Frost Breath 冰吸(11.11)";
        pokemonSkill.bestSkill2 = "Blizzard 暴風雪(25.64)";
        bestSkill.put("Articuno(急凍鳥)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Thunder Shock 電擊(8.33)";
        pokemonSkill.bestSkill2 = "Thunder 打雷(23.26)";
        bestSkill.put("Zapdos(閃電鳥)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Ember 火花(9.52)";
        pokemonSkill.bestSkill2 = "Fire Blast 大字爆(24.39)";
        bestSkill.put("Moltres(火焰鳥)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Dragon Breath 龍之息(12)";
        pokemonSkill.bestSkill2 = "Dragon Claw 龍爪(23.33)";
        bestSkill.put("Dragonite(快龍)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Psycho Cut 幻象斬(14.04)";
        pokemonSkill.bestSkill2 = "Psychic 幻象術(19.64)";
        bestSkill.put("Mewtwo(超夢)", pokemonSkill);

        pokemonSkill = new MobSkillData();
        pokemonSkill.bestSkill1 = "Pound 拍擊(12.96)";
        pokemonSkill.bestSkill2 = "Hurricane 颶風(25)";
        bestSkill.put("Mew(夢幻)", pokemonSkill);
    }
}
