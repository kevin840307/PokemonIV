package com.mndt.ghost.mndtivpokemon;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.view.View;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by user on 2016/8/28.
 */
public class DrawAngle extends View {

    static float []saveAngle;

    public DrawAngle(Context context) {
        super(context);
        readData();
        initAngles();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        try {
            float angels;
            if(Data.SETTING == 0) {
                angels = saveAngle[Integer.valueOf(Data.DATA_LEVLE) - 1];
            } else {
                angels = 180;
            }
            super.onDraw(canvas);
            Paint p = new Paint();
            Paint p2 = new Paint();
            p.setColor(Color.RED);
            p2.setColor(Color.RED);
            Shader shader = new LinearGradient(0, 0, 10, 10,
                    new int[]{Color.argb(105, 245, 254, 25), Color.argb(105, 245, 254, 25)}, null, Shader.TileMode.CLAMP);
            Shader shader2 = new LinearGradient(0, 0, 10, 10,
                    new int[]{Color.argb(105, 0, 0, 0), Color.argb(105, 0, 0, 0)}, null, Shader.TileMode.CLAMP);
            p.setShader(shader);
            p2.setShader(shader2);
            p2.setStyle(Paint.Style.STROKE);
            RectF oval2 = new RectF((Data.WIDTH_PIXELS - Data.ANGLES_WIDTH), Data.ANGLES_HEIGHT, Data.ANGLES_WIDTH, Data.ANGLES_SIZE);// 设置个新的长方形，扫描测量X Y距離 X Y大小 1184 720
            canvas.drawArc(oval2, 180, angels, true, p);
            canvas.drawArc(oval2, 180, angels, true, p2);
        } catch (Exception ex) {}
    }

    public static void initAngles() {
        saveAngle = new float[Data.PLAYER_LEVLE * 2 + 2];
        int pos = 0;
        double level = Data.PLAYER_LEVLE;
        for (double pokeLevel = 1.0; pokeLevel <= level + 1.5; pokeLevel += 0.5, pos++) {
            //double angleInDegrees = (Data.CpM[(int) (pokeLevel * 2 - 2)] - 0.094) * 203.037116 / Data.CpM[Data.PLAYER_LEVLE * 2 - 2];
            //double angleInDegrees = (Data.CpM[(int) (pokeLevel * 2 - 2)] - 0.094) / ((Data.CpM[(int)le vel - 1] + Data.CpM[(int)level - 1] / 2) - 0.094) * 180;
            double angleInDegrees =  (Data.CpM[(int) (pokeLevel * 2 - 2)] - 0.094) * 180.0 / (Data.CpM[min(78, (int)(level * 2 + 2))] - 0.094);
            saveAngle[pos] = (float)angleInDegrees;
        }
    }
static int min(int i, int j) {
    return (i < j) ? i : j;
}

    public void readData() {
        String str;
        FileReader fr = null;
        BufferedReader buff = null;
        try {
            fr = new FileReader(Data.SETTING_PATH);
            buff = new BufferedReader(fr);
            if ((str = buff.readLine()) != null) {
                Data.ANGLES_WIDTH = Integer.valueOf(str);
                if ((str = buff.readLine()) != null) {
                    Data.ANGLES_HEIGHT = Integer.valueOf(str);
                    if ((str = buff.readLine()) != null) {
                        Data.ANGLES_SIZE = Integer.valueOf(str);
                    }
                }
            }
            fr.close();
        } catch (IOException ex) {
            Data.ANGLES_WIDTH = (Data.WIDTH_PIXELS / 9) * 8;
            Data.ANGLES_HEIGHT = Data.WIDTH_PIXELS - Data.ANGLES_WIDTH + 20;
            Data.ANGLES_SIZE = (Data.HEIGHT_PIXELS / 11) * 6;
            Data.ANGLES_OFFSET = Data.ANGLES_WIDTH - 150;
        }
    }
}
