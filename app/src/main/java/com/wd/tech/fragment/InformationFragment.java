package com.wd.tech.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.stx.xhb.xbanner.XBanner;
import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.zhangn.MVP.ZnBaseConstract;
import com.wd.tech.zhangn.MVP.ZnPresenter;
import com.wd.tech.zhangn.activity.AllBkActivity;
import com.wd.tech.zhangn.activity.BannerXqActivity;
import com.wd.tech.zhangn.activity.ZxXqActivity;
import com.wd.tech.zhangn.adapter.ZnZxAdapter;
import com.wd.tech.zhangn.bean.DzBean;
import com.wd.tech.zhangn.bean.PingLMesBean;
import com.wd.tech.zhangn.bean.QxdzBean;
import com.wd.tech.zhangn.bean.ZnLbt;
import com.wd.tech.zhangn.bean.ZnZxBean;
import com.wd.tech.zhangn.bean.ZnZxXqBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

//import com.wd.tech.zhangn.adapter.ZnZxAdapter;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 16:11
 * @Description：YangXinYu 资讯
 */
public class InformationFragment extends BaseFragment implements ZnBaseConstract.View {
    @BindView(R.id.banner)
    XBanner banner;
    Unbinder unbinder;
    @BindView(R.id.recy)
    RecyclerView recy;
    @BindView(R.id.smart)
    SmartRefreshLayout smart;
    @BindView(R.id.menu)
    ImageView menu;
    @BindView(R.id.serach)
    ImageView serach;
    private ZnPresenter presenter;
    private int userId;
    private String sessionId;
    private ZnZxAdapter adapter1;


    @Override
    protected int getLayout() {
        return R.layout.fragment_information;
    }

    @Override
    protected void initView(View view) {
        //p 轮播图
        presenter = new ZnPresenter();
        presenter.attachView(this);
        presenter.requestDatazx(userId, sessionId);

        //刷新
        smart.setEnableRefresh(true);
        smart.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                presenter.requestDatazx(userId, sessionId);
                smart.finishRefresh();
            }
        });


    }


    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {


    }


    //轮播图
    @Override
    public void showData(final ZnLbt result) {
        Log.d("MessageFragment", result.getMessage());
        final List<ZnLbt.ResultBean> result1 = result.getResult();
        //集合设置给XBanner
        banner.setData(result1, null);
        //加载图片
        banner.loadImage(new XBanner.XBannerAdapter() {
            @Override
            public void loadBanner(XBanner banner, Object model, View view, int position) {
                //设置图片圆角角度
                RoundedCorners roundedCorners = new RoundedCorners(15);
                //通过RequestOptions扩展功能
                RequestOptions options = RequestOptions.bitmapTransform(roundedCorners).override(300, 300);
                Glide.with(getActivity()).load(result1.get(position).getImageUrl()).apply(options).into((ImageView) view);
                //延迟时间
                banner.setPageChangeDuration(3000);
            }
        });
        /**
         * 点击跳转
         */
        banner.setOnItemClickListener(new XBanner.OnItemClickListener() {
            @Override
            public void onItemClick(XBanner banner, Object model, View view, int position) {
                String jumpUrl = result1.get(position).getJumpUrl();
                Intent intent = new Intent(getActivity(), BannerXqActivity.class);
                intent.putExtra("url", jumpUrl);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        userId = MyApplication.UserId;
        sessionId = MyApplication.SessionId;
    }

    //资讯
    @Override
    public void showData2(ZnZxBean bean) {
        final List<ZnZxBean.ResultBean> result = bean.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recy.setLayoutManager(manager);
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).getWhetherAdvertising() == 1) {
                result.get(i).setType(0);
            } else {
                result.get(i).setType(1);
            }
        }

        adapter1 = new ZnZxAdapter(result);
        recy.setAdapter(adapter1);
        //收藏
        adapter1.setScCall(new ZnZxAdapter.ScCall() {
            @Override
            public void oncall(int id, int i) {
                if (i == 2) {
                    presenter.requestDatasc(userId, sessionId, id);
                } else if (i == 1) {
                    //取消收藏
                    presenter.requestDataqx(userId, sessionId, id);
                }
            }
        });
        adapter1.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                int id = result.get(position).getId();
                Intent intent = new Intent(getActivity(), ZxXqActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
            }
        });
    }

    @Override
    public void showData3(ZnZxXqBean bean) {

    }

    //收藏
    @Override
    public void showDatasc(DzBean bean) {
        Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
        presenter.requestDatazx(userId, sessionId);
    }

    //取消收藏
    @Override
    public void showDataqx(QxdzBean bean) {
        Toast.makeText(mContext, bean.getMessage(), Toast.LENGTH_SHORT).show();
        presenter.requestDatazx(userId, sessionId);
    }

    @Override
    public void showDatasepl(PingLMesBean bean) {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.menu, R.id.serach})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.menu:

                //菜单
                startActivity(new Intent(getActivity(),AllBkActivity.class));

                break;
            case R.id.serach:

                //搜索

                break;
        }
    }
}