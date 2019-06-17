package com.wd.tech.activity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.adapter.ListViewAdapter;
import com.wd.tech.adapter.ViewPagerAdapter;
import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.fragment.CommunityFragment;
import com.wd.tech.fragment.InformationFragment;
import com.wd.tech.fragment.MessageFragment;
import com.wd.tech.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ScienceActivity extends BaseActivity {

    @BindView(R.id.science_head)
    SimpleDraweeView scienceHead;
    @BindView(R.id.science_name)
    TextView scienceName;
    @BindView(R.id.science_geqian)
    TextView scienceGeqian;
    @BindView(R.id.science_map)
    ImageView scienceMap;
    @BindView(R.id.login_ListView)
    ListView loginListView;
    private LinearLayout science_information;
    private LinearLayout science_community;
    private LinearLayout science_message;
    private ImageView science_information_imageView;
    private RadioButton science_information_radioButton;
    private ImageView science_message_imageView;
    private RadioButton science_message_radioButton;
    private ImageView science_community_imageView;
    private RadioButton science_community_radioButton;
    private ViewPager science_noScrollViewPager;
    private DrawerLayout science_drawerLayout;
    private SimpleDraweeView science_simple;
    private LinearLayout science_drawerLayout_login;
    private LinearLayout science_drawerLayout_loginSucceed;

    @Override
    protected int getLayout() {
        return R.layout.activity_science;
    }

    @Override
    protected void findView() {
        applypermission();
        science_noScrollViewPager = findViewById(R.id.science_NoScrollViewPager);
        science_information = findViewById(R.id.science_information);
        science_message = findViewById(R.id.science_message);
        science_community = findViewById(R.id.science_community);
        science_information_imageView = findViewById(R.id.science_information_ImageView);
        science_information_radioButton = findViewById(R.id.science_information_RadioButton);
        science_message_imageView = findViewById(R.id.science_message_ImageView);
        science_message_radioButton = findViewById(R.id.science_message_RadioButton);
        science_community_imageView = findViewById(R.id.science_community_ImageView);
        science_community_radioButton = findViewById(R.id.science_community_RadioButton);
        science_drawerLayout = findViewById(R.id.science_DrawerLayout);
        science_simple = findViewById(R.id.science_Simple);
        science_drawerLayout_login = findViewById(R.id.science_DrawerLayout_login);
        science_drawerLayout_loginSucceed = findViewById(R.id.science_DrawerLayout_loginSucceed);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(MyApplication.Remember()){
            science_drawerLayout_login.setVisibility(View.GONE);
            science_drawerLayout_loginSucceed.setVisibility(View.VISIBLE);
        }else{
            science_drawerLayout_login.setVisibility(View.VISIBLE);
            science_drawerLayout_loginSucceed.setVisibility(View.GONE);
        }
        scienceHead.setImageURI(MyApplication.Head);
        scienceName.setText(MyApplication.name);
        if (MyApplication.vipfalg == 1) {
            scienceMap.setVisibility(View.VISIBLE);
        } else {
            scienceMap.setVisibility(View.GONE);
        }
        ListViewAdapter adapters = new ListViewAdapter();
        loginListView.setAdapter(adapters);
        loginListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        toClass(ScienceActivity.this,CollectActivity.class);
                        Anima();
                        break;
                    case 1:
                        toClass(ScienceActivity.this,AttentionActivity.class);
                        Anima();
                        break;
                    case 2:
                        toClass(ScienceActivity.this,InvitationActivity.class);
                        Anima();
                        break;
                    case 3:
                        toClass(ScienceActivity.this,InformActivity.class);
                        Anima();
                        break;
                    case 4:
                        toClass(ScienceActivity.this,IntegralActivity.class);
                        Anima();
                        break;
                    case 5:
                        toClass(ScienceActivity.this,TaskActivity.class);
                        Anima();
                        break;
                    case 6:
                        toClass(ScienceActivity.this,SetActivity.class);
                        Anima();
                        break;
                }
            }
        });


    }
    public void applypermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //检查是否已经给了权限
            int checkpermission = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.READ_EXTERNAL_STORAGE);
            int checkpermissions = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (checkpermission != PackageManager.PERMISSION_GRANTED) {//没有给权限
                Log.e("permission", "READ_EXTERNAL_STORAGE");
                ActivityCompat.requestPermissions(ScienceActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
            }
            if (checkpermissions != PackageManager.PERMISSION_GRANTED) {
                Log.e("permission", "动态申请WRITE_EXTERNAL_STORAGE");
                //参数分别是当前活动，权限字符串数组，requestcode
                ActivityCompat.requestPermissions(ScienceActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }

        }
    }
    @Override
    protected void initData() {
        if(MyApplication.Remember()){
            science_drawerLayout_login.setVisibility(View.GONE);
            science_drawerLayout_loginSucceed.setVisibility(View.VISIBLE);
        }else{
            science_drawerLayout_login.setVisibility(View.VISIBLE);
            science_drawerLayout_loginSucceed.setVisibility(View.GONE);
        }
        Uri uri = Uri.parse("res://com.wd.tech/" + R.mipmap.login_icon_n);
        science_simple.setImageURI(uri);
        science_information.setOnClickListener(this);
        science_message.setOnClickListener(this);
        science_community.setOnClickListener(this);
        science_drawerLayout_login.setOnClickListener(this);
        List<Fragment> list = initList();
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),list);
        science_noScrollViewPager.setAdapter(adapter);
    }

    private List<Fragment> initList() {
        List<Fragment> list = new ArrayList<>();
        list.add(new InformationFragment());
        list.add(new MessageFragment());
        list.add(new CommunityFragment());
        return list;
    }


    @Override
    protected void setListener() {
        science_drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {
                // 得到contentView 实现侧滑界面出现后主界面向右平移避免侧滑界面遮住主界面
                View content = science_drawerLayout.getChildAt(0);
                int offset = (int) (view.getWidth() * v);
                content.setTranslationX(offset);
            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
            }
            @Override
            public void onDrawerClosed(@NonNull View view) {
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });
    }
    public void Anima(){
        overridePendingTransition(R.anim.anim_ins, R.anim.anim_exit2);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.science_information:
                science_noScrollViewPager.setCurrentItem(0);
                science_information_imageView.setImageResource(R.mipmap.common_tab_informatiion_s);
                science_message_imageView.setImageResource(R.mipmap.common_tab_message_n);
                science_community_imageView.setImageResource(R.mipmap.common_tab_community_n);
                science_information_radioButton.setTextColor(Color.parseColor("#727272"));
                science_message_radioButton.setTextColor(Color.parseColor("#B6B6B6"));
                science_community_radioButton.setTextColor(Color.parseColor("#B6B6B6"));
                break;
            case R.id.science_message:
                science_noScrollViewPager.setCurrentItem(1);
                science_information_imageView.setImageResource(R.mipmap.common_tab_information_n);
                science_message_imageView.setImageResource(R.mipmap.common_tab_message_s);
                science_community_imageView.setImageResource(R.mipmap.common_tab_community_n);
                science_information_radioButton.setTextColor(Color.parseColor("#B6B6B6"));
                science_message_radioButton.setTextColor(Color.parseColor("#727272"));
                science_community_radioButton.setTextColor(Color.parseColor("#B6B6B6"));
                break;

            case R.id.science_community:
                science_noScrollViewPager.setCurrentItem(2);
                science_information_imageView.setImageResource(R.mipmap.common_tab_information_n);
                science_message_imageView.setImageResource(R.mipmap.common_tab_message_n);
                science_community_imageView.setImageResource(R.mipmap.common_tab_community_s);
                science_information_radioButton.setTextColor(Color.parseColor("#B6B6B6"));
                science_message_radioButton.setTextColor(Color.parseColor("#B6B6B6"));
                science_community_radioButton.setTextColor(Color.parseColor("#727272"));
                break;
            case R.id.science_DrawerLayout_login:
                toClass(this,LoginActivity.class);
                overridePendingTransition(R.anim.anim_ins,R.anim.anim_exit2);
                break;


        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
