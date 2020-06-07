package com.mndt.ghost.mndtivpokemon;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;

import com.mndt.ghost.mndtivpokemon.dataview.FloatWindowView;

public class FloatWindowManager {


    private static FloatWindowView smallWindow;
    private static FloatWindowView.FloatWindowBigView bigWindow;
    private static FloatWindowView.AnglesSetting anglesSetting;
    private static android.view.WindowManager mWindowManager;

    private static android.view.WindowManager.LayoutParams smallWindowParams;
    private static android.view.WindowManager.LayoutParams bigWindowParams;
    private static android.view.WindowManager.LayoutParams pcmPointParams;
    private static android.view.WindowManager.LayoutParams anglesSettingParams;
    private static DrawAngle view = null;


    public static void createSmallWindow(Context context) {
        android.view.WindowManager windowManager = getWindowManager(context);
        if (smallWindow == null) {
            int screenWidth = windowManager.getDefaultDisplay().getWidth();
            int screenHeight = windowManager.getDefaultDisplay().getHeight();
            smallWindow = new FloatWindowView(context);
            if(smallWindowParams == null) {
                smallWindowParams = new android.view.WindowManager.LayoutParams();
                smallWindowParams.type = android.view.WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
                smallWindowParams.format = PixelFormat.RGBA_8888;
                smallWindowParams.flags = android.view.WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL |
                        android.view.WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE; //FloatWindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL   | FloatWindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
                smallWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                smallWindowParams.width = FloatWindowView.viewWidth;
                smallWindowParams.height = FloatWindowView.viewHeight;
                smallWindowParams.x = screenWidth;
                smallWindowParams.y = screenHeight / 2;
                smallWindow.setParams(smallWindowParams);
            }
            windowManager.addView(smallWindow, smallWindowParams);
        }
    }

    public static void removeSmallWindow(Context context) {
        if (smallWindow != null) {
            android.view.WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(smallWindow);
            smallWindow = null;
            smallWindowParams = null;
        }
    }

    public static void createBigWindow(Context context) {
        android.view.WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (bigWindow == null) {
            bigWindow = new FloatWindowView.FloatWindowBigView(context);
            if (bigWindowParams == null) {
                bigWindowParams = new android.view.WindowManager.LayoutParams();
                bigWindowParams.x = screenWidth / 2;// screenWidth / 2 - FloatWindowSmallView.FloatWindowBigView.viewWidth / 2
                bigWindowParams.y = screenHeight / 2;
                bigWindowParams.type = android.view.WindowManager.LayoutParams.TYPE_TOAST;
                bigWindowParams.format = PixelFormat.RGBA_8888;
                bigWindowParams.gravity = Gravity.LEFT | Gravity.TOP;
                bigWindowParams.width = screenWidth;
                bigWindowParams.height = screenHeight / 2;
            }
            windowManager.addView(bigWindow, bigWindowParams);
        }
    }

    public static void createPoint(Context context) {
        android.view.WindowManager windowManager = getWindowManager(context);
        if(view == null) {
            view = new DrawAngle(context);
            if (pcmPointParams == null) {
                pcmPointParams = new android.view.WindowManager.LayoutParams();
                pcmPointParams.type = android.view.WindowManager.LayoutParams.TYPE_PHONE;
                pcmPointParams.format = PixelFormat.RGBA_8888;
            }
        }
        if(view != null)
        windowManager.addView(view, pcmPointParams);
    }

    public static void updataPoint(Context context) {
        android.view.WindowManager windowManager = getWindowManager(context);
        if(view != null) {
            view.invalidate();
        }
    }

    public static void createSetting(Context context) {
        android.view.WindowManager windowManager = getWindowManager(context);
        int screenWidth = windowManager.getDefaultDisplay().getWidth();
        int screenHeight = windowManager.getDefaultDisplay().getHeight();
        if (anglesSetting == null) {
            anglesSetting = new FloatWindowView.AnglesSetting(context);
            if (anglesSettingParams == null) {
                anglesSettingParams = new android.view.WindowManager.LayoutParams();
                anglesSettingParams.x = screenWidth / 2 - FloatWindowView.AnglesSetting.viewWidth / 2;
                anglesSettingParams.y = screenHeight / 2 - FloatWindowView.AnglesSetting.viewHeight / 2;
                anglesSettingParams.type = android.view.WindowManager.LayoutParams.TYPE_TOAST;
                anglesSettingParams.format = PixelFormat.RGBA_8888;
                anglesSettingParams.gravity = Gravity.LEFT | Gravity.TOP;
                anglesSettingParams.width = FloatWindowView.AnglesSetting.viewWidth;
                anglesSettingParams.height = FloatWindowView.AnglesSetting.viewHeight;
            }
            windowManager.addView(anglesSetting, anglesSettingParams);
        }
    }

    public static void removeBigWindow(Context context) {
        if (bigWindow != null) {
            android.view.WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(bigWindow);
            bigWindow = null;
            bigWindowParams = null;
        }
    }

    public static void removePoint(Context context) {
        if (view != null) {
            android.view.WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(view);
            view = null;
            pcmPointParams = null;
        }
    }

    public static void removeSetting(Context context) {
        if (anglesSetting != null) {
            android.view.WindowManager windowManager = getWindowManager(context);
            windowManager.removeView(anglesSetting);
            anglesSetting = null;
            anglesSettingParams = null;
        }
    }

    private static android.view.WindowManager getWindowManager(Context context) {
        if (mWindowManager == null) {
            mWindowManager = (android.view.WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        }
        return mWindowManager;
    }
}