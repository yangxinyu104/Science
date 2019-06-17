package com.wd.tech.throwable;

import android.app.ActivityManager;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Looper;
import android.widget.Toast;

import com.wd.tech.activity.MainActivity;
import com.wd.tech.app.MyApplication;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.5.21 18:41
 * @Description：YangXinYu
 */
public class ThrowDispose implements Thread.UncaughtExceptionHandler {
    Context context;
    private static ThrowDispose aThrow;

    private ThrowDispose() {
    }

    public static ThrowDispose GetInstance() {
        if (aThrow == null) {
            aThrow = new ThrowDispose();
        }
        return aThrow;
    }

    public void Init(Context context) {
        this.context = context;
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(context, "遇到了异常", Toast.LENGTH_SHORT).show();
                restartApplication();
                Looper.loop();
            }
        }.start();

    }

    private void restartApplication() {
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent restartIntent = PendingIntent.getActivity(
                context, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        //退出程序
        AlarmManager mgr = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 500,
                restartIntent); // 1秒钟后重启应用
        android.os.Process.killProcess(android.os.Process.myPid());
    }


}
