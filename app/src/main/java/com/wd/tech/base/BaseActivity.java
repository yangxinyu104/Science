package com.wd.tech.base;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.zackratos.ultimatebar.UltimateBar;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.8 19:20
 * @Description：YangXinYu
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    public Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        findView();

        initData();

        setListener();

        mContext = BaseActivity.this;

        UltimateBar ultimateBar = new UltimateBar(this);
        ultimateBar.setTransparentBar(Color.BLUE, 50);


    }
    //获取布局的id
    protected  abstract int getLayout();
    //查找页面上的组件
    protected  abstract void findView();
    //初始化数据
    protected  abstract void initData();
    //设置监听
    protected  abstract void  setListener();

    /**
     * Intent跳转
     * @param context
     * @param clazz
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz){
        Intent intent = new Intent(context,clazz);
        startActivity(intent);
    }

    /**
     * Intent带值跳转
     * @param context
     * @param clazz
     * @param bundle
     */
    protected void toClass(Context context, Class<? extends BaseActivity> clazz, Bundle bundle){
        Intent intent = new Intent(context,clazz);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     * 带返回值的跳转
     * @param context
     * @param clazz
     * @param bundle
     * @param reuqestCode
     */
    protected void toClass(Context context,Class<? extends BaseActivity> clazz,Bundle bundle,int reuqestCode){
        Intent intent = new Intent(context,clazz);
        intent.putExtras(bundle);
        startActivityForResult(intent,reuqestCode);
    }



}
