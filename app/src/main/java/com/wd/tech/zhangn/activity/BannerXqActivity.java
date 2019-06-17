package com.wd.tech.zhangn.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.zhangn.MVP.ZnBaseConstract;
import com.wd.tech.zhangn.MVP.ZnPresenter;
import com.wd.tech.zhangn.adapter.TuiJianAdapter;
import com.wd.tech.zhangn.bean.DzBean;
import com.wd.tech.zhangn.bean.PingLMesBean;
import com.wd.tech.zhangn.bean.QxdzBean;
import com.wd.tech.zhangn.bean.ZnLbt;
import com.wd.tech.zhangn.bean.ZnZxBean;
import com.wd.tech.zhangn.bean.ZnZxXqBean;
import com.wd.tech.zhangn.utils.TimeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 轮播图
 */
public class BannerXqActivity extends AppCompatActivity implements ZnBaseConstract.View {

    @BindView(R.id.web)
    WebView web;
    @BindView(R.id.xq_name)
    TextView xqName;
    @BindView(R.id.xqtime)
    TextView xqtime;
    @BindView(R.id.ly)
    TextView ly;
    @BindView(R.id.lay)
    LinearLayout lay;
    @BindView(R.id.img)
    SimpleDraweeView img;
    @BindView(R.id.zw)
    TextView zw;
    @BindView(R.id.bq)
    Button bq;
    @BindView(R.id.tjrecy)
    RecyclerView tjrecy;
    private ZnPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banner_xq);
        ButterKnife.bind(this);

        int userId = MyApplication.UserId;
        String sessionId = MyApplication.SessionId;

        Intent intent = getIntent();
        String url = intent.getStringExtra("url");


        presenter = new ZnPresenter();
        presenter.attachView(this);

        String s = url.substring(0, 1);
        Log.d("BannerXqActivity", s);
        if (s.equals("w")) {
            //原生 跳页面
            //webview 隐藏  原生的显示
            web.setVisibility(View.GONE);
            String s1 = url.substring(24);
            int i = Integer.parseInt(s1);
            presenter.requestDatazxxq(userId, sessionId, i);
            lay.setVisibility(View.VISIBLE);
        } else {
            //原生隐藏 webview 显示
            lay.setVisibility(View.GONE);
            web.setVisibility(View.VISIBLE);
            web.loadUrl(url);
            finish();
        }

    }


    //资讯详情
    @Override
    public void showData3(ZnZxXqBean bean) {
        ZnZxXqBean.ResultBean result = bean.getResult();

        xqName.setText(result.getTitle() + "");
        long time = result.getReleaseTime();
        xqtime.setText(TimeUtils.longToDate(time));
        ly.setText(result.getSource());
        String s = result.getThumbnail();
        img.setImageURI(Uri.parse(s));
//        String c = result.getContent();
//        //正文
//        zw.setText(Html.fromHtml(c));
//        if (result.getPlate().size()!=0) {
//            bq.setVisibility(View.VISIBLE);
//            bq.setText(result.getPlate().get(0).getName() + "");
//        }
        //推荐
        LinearLayoutManager manager=new LinearLayoutManager(this);
        tjrecy.setLayoutManager(manager);
        TuiJianAdapter tjadapter=new TuiJianAdapter(R.layout.tuijian,result.getInformationList());
        tjrecy.setAdapter(tjadapter);



    }

    @Override
    public void showDatasc(DzBean bean) {

    }

    @Override
    public void showDataqx(QxdzBean bean) {

    }

    @Override
    public void showDatasepl(PingLMesBean bean) {

    }


    @Override
    public void showData(ZnLbt result) {

    }

    @Override
    public void showData2(ZnZxBean bean) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.dattachView(this);
    }
}
