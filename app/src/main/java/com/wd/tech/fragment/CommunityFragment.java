package com.wd.tech.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.adapter.CommunityAdapter;
import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.CommunityBean;
import com.wd.tech.bean.CommunityGreat;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.SciencePresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 16:11
 * @Description：YangXinYu
 */
public class CommunityFragment extends BaseFragment implements IContract.VSheQv {
    XRecyclerView sheqv_xRecyclerView;
    ImageView sheqv_img;
    CommunityAdapter adapte;
    int UserId;
    String SessionId;
    List<CommunityBean.ResultBean> list=new ArrayList<>();
    int page=1;
    IContract.PSheQv pSheQv;
    @Override
    protected int getLayout() {
        return R.layout.fragment_community;
    }

    @Override
    protected void initView(View view) {

        sheqv_xRecyclerView=view.findViewById(R.id.xrecyclerview_shequ);
        sheqv_img=view.findViewById(R.id.shequ_xiugai);
    }
    //操作
    @Override
    protected void initData() {
        //P
        pSheQv=new SciencePresenter(this);
        //设置管理器
        LinearLayoutManager layoutManager=new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        sheqv_xRecyclerView.setLayoutManager(layoutManager);
        //设置上下拉
        sheqv_xRecyclerView.setLoadingMoreEnabled(true);
        sheqv_xRecyclerView.setPullRefreshEnabled(true);
        //设置适配器
        adapte=new CommunityAdapter(getActivity(),list);
        sheqv_xRecyclerView.setAdapter(adapte);

        pSheQv.PSheQv(1,5,UserId,SessionId);
        //设置上下拉监听
        sheqv_xRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                list.clear();
                //下拉刷新
                pSheQv.PSheQv(1,3,UserId,SessionId);
                adapte.notifyDataSetChanged();
            }

            @Override
            public void onLoadMore() {
                //上拉加载
                page++;
                pSheQv.PSheQv(page,3,UserId,SessionId);
                adapte.notifyDataSetChanged();
            }
        });

        //点赞的调用接口
        adapte.setSetOnClickItem(new CommunityAdapter.setOnClickItem() {
            @Override
            public void onGreat(int id , int great) {
                if (great == 1){
                    pSheQv.toCommunity_NoGreat(id);
                }else {
                    pSheQv.toCommunity_Great(id);
                }
                adapte.notifyDataSetChanged();
            }
        });
    }
    //点击事件
    @Override
    public void onClick(View v) {

    }

    @Override
    public void VSheQv(List<CommunityBean.ResultBean> lit) {
        sheqv_xRecyclerView.loadMoreComplete();
        sheqv_xRecyclerView.refreshComplete();
        list.clear();
        this.list.addAll(lit);
        adapte.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
       UserId=MyApplication.UserId;
        SessionId=MyApplication.SessionId;
    }
    @Override
    public void showCommunityGreat(Object object) {
        //Log.e("点赞",""+object);
        CommunityGreat communityGreat = (CommunityGreat) object;
        String message = communityGreat.getMessage();
        if (message.equals("点赞成功")){
            Toast.makeText(getContext(),""+message,Toast.LENGTH_SHORT).show();
            adapte.notifyDataSetChanged();
        }else {
            Toast.makeText(getContext(),""+message,Toast.LENGTH_SHORT).show();
            adapte.notifyDataSetChanged();
        }
    }
}
