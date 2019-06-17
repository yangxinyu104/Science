package com.wd.tech.activity;

import android.view.View;

import com.wd.tech.base.BaseActivity;
import com.wd.tech.R;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {
    private Timer mTimer;
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initData() {
        mTimer = new Timer();
        TimerTask mTimerTask = new TimerTask() {
            int num = 3;

            @Override
            public void run() {
                //run方法中执行需要间隔执行的代码
                num--;
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (num == 0) {
                            toClass(MainActivity.this, ScienceActivity.class);
                            overridePendingTransition(R.anim.anim_in, R.anim.anim_out);
                            finish();
                            mTimer.cancel();
                        }
                    }
                });
            }
        };
        //2s后开始执行，间隔为4s
        mTimer.schedule(mTimerTask, 0, 1000);

    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }
}
