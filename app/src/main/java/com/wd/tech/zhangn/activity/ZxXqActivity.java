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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.zhangn.MVP.ZnBaseConstract;
import com.wd.tech.zhangn.MVP.ZnPresenter;
import com.wd.tech.zhangn.adapter.PingjiaAdapter;
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
import butterknife.OnClick;

public class ZxXqActivity extends AppCompatActivity implements ZnBaseConstract.View {

    @BindView(R.id.xq_name)
    TextView xqName;
    @BindView(R.id.xqtime)
    TextView xqtime;
    @BindView(R.id.ly)
    TextView ly;
    @BindView(R.id.img)
    SimpleDraweeView img;
    @BindView(R.id.zw)
    TextView zw;
    @BindView(R.id.bq)
    Button bq;
    @BindView(R.id.tjrecy)
    RecyclerView tjrecy;
    @BindView(R.id.lay)
    LinearLayout lay;
    @BindView(R.id.plrecy)
    RecyclerView plrecy;
    @BindView(R.id.back)
    ImageView back;
    @BindView(R.id.ed)
    EditText ed;
    @BindView(R.id.pl)
    ImageView pl;
    @BindView(R.id.zan)
    ImageView zan;
    @BindView(R.id.sc)
    ImageView sc;
    @BindView(R.id.fx)
    ImageView fx;
    @BindView(R.id.pll)
    LinearLayout pll;
    private ZnPresenter presenter;
    private int userId;
    private String sessionId;
    private int id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zx_xq);
        ButterKnife.bind(this);

        userId = MyApplication.UserId;
        sessionId = MyApplication.SessionId;


        Intent intent = getIntent();
        id = intent.getIntExtra("id", 0);

        presenter = new ZnPresenter();
        presenter.attachView(this);
        presenter.requestDatazxxq(userId, sessionId, id);


    }

    @Override
    public void showData(ZnLbt result) {

    }

    @Override
    public void showData2(ZnZxBean bean) {

    }

    @Override
    public void showData3(ZnZxXqBean bean) {
        ZnZxXqBean.ResultBean result = bean.getResult();
        xqName.setText(result.getTitle() + "");
        long time = result.getReleaseTime();
        xqtime.setText(TimeUtils.longToDate(time));
        ly.setText(result.getSource());
        String s = result.getThumbnail();
        img.setImageURI(Uri.parse(s));
        String c = result.getContent();
        //正文
        zw.setText(Html.fromHtml(c));
        if (result.getPlate().size() != 0) {
            bq.setVisibility(View.VISIBLE);
            bq.setText(result.getPlate().get(0).getName() + "");
        }
        //推荐
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        tjrecy.setLayoutManager(manager);
        TuiJianAdapter tjadapter = new TuiJianAdapter(R.layout.tuijian, result.getInformationList());
        tjrecy.setAdapter(tjadapter);

        int i = result.getWhetherCollection();
        if (i == 1) {
            sc.setImageResource(R.mipmap.common_icon_collect_s);
        } else {
            sc.setImageResource(R.mipmap.common_icon_collect_n);
        }
        int i1 = result.getWhetherGreat();
        if (i1 == 1) {
            zan.setImageResource(R.mipmap.common_icon_praise_s);
        } else {
            zan.setImageResource(R.mipmap.common_icon_prise_n);
        }


    }

    @Override
    public void showDatasc(DzBean bean) {

    }

    @Override
    public void showDataqx(QxdzBean bean) {

    }

    @Override
    public void showDatasepl(PingLMesBean bean) {
            pll.setVisibility(View.VISIBLE);
            LinearLayoutManager manager = new LinearLayoutManager(this);
            plrecy.setLayoutManager(manager);
            PingjiaAdapter adapter=new PingjiaAdapter(R.layout.pl,bean.getResult());
            plrecy.setAdapter(adapter);

    }

    @OnClick({R.id.back, R.id.ed, R.id.pl, R.id.zan, R.id.sc, R.id.fx})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.back:

                //返回
                finish();

                break;
            case R.id.ed:

                //评论


                break;
            case R.id.pl:

                Toast.makeText(this, "我是评论", Toast.LENGTH_SHORT).show();
                //评论的消息
                presenter.requestDataplmsg(userId, sessionId, id);


                break;
            case R.id.zan:

                //赞

                break;
            case R.id.sc:

                //收藏

                break;
            case R.id.fx:

                //分享

                break;
        }
    }
}
