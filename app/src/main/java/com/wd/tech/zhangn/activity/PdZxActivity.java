package com.wd.tech.zhangn.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.wd.tech.R;

public class PdZxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pd_zx);

        Intent intent = getIntent();
        int id = intent.getIntExtra("id", 0);




    }
}
