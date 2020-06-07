package com.mndt.ghost.mndtivpokemon;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class WindowServer extends Service {

    private static final String TAG = "WindowServer";

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Intent it = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, it, 0);
        FloatWindowManager.createSmallWindow(getApplicationContext());
        Notification notification = new Notification.Builder(this)
                .setContentIntent(contentIntent)
                .setLargeIcon(Data.PICTURE)
                .setSmallIcon(R.drawable.pokemon_151)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(false)
                .setContentTitle("MNDT IV(Pokemon)")
                .setContentText("MNDT IV 運行中")
                .getNotification();
        startForeground(1, notification);
        Log.i(TAG, "oncreat");
    }

   @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent.getBooleanExtra("isStop", false)) {
            Log.e(TAG, "huifu()");
            huifu();
        }
        return super.onStartCommand(intent, flags, startId);
    }

    private void huifu() { //恢复优先级
        stopForeground(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
