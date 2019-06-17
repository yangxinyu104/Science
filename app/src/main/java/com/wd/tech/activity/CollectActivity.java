package com.wd.tech.activity;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.CollectBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.SciencePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CollectActivity extends BaseActivity implements IContract.ICollect {


    @BindView(R.id.collect_finish)
    ImageView collectFinish;
    @BindView(R.id.collect_delete)
    ImageView collectDelete;
    @BindView(R.id.collect_RecyclerView)
    RecyclerView collectRecyclerView;

    @Override
    protected int getLayout() {
        return R.layout.activity_collect;
    }

    @Override
    protected void findView() {

    }

    @Override
    protected void initData() {

        IContract.IPresenter iPresenter = new SciencePresenter<>(this);
        iPresenter.collect(1,10);
        Log.e("tag",MyApplication.UserId +   MyApplication.SessionId + "              CollectActivity ");
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.collect_finish, R.id.collect_delete})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.collect_finish:
                finish();
                FinishAnima();
                break;
            case R.id.collect_delete:
                break;
        }
    }

    public void FinishAnima(){
        overridePendingTransition(0,R.anim.anim_exit);
    }

    @Override
    public void collect(CollectBean registerBean) {
        Toast.makeText(mContext, "registerBean.getResult().size():" + registerBean.getResult().size(), Toast.LENGTH_SHORT).show();
    }
}
