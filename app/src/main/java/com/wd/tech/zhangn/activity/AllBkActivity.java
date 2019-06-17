package com.wd.tech.zhangn.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.wd.tech.R;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.SciencePresenter;
import com.wd.tech.zhangn.adapter.MyAllBkAdapter;
import com.wd.tech.zhangn.bean.AllBkBean;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

//所有版块
public class AllBkActivity extends AppCompatActivity implements IContract.AllBkView {

    @BindView(R.id.recy)
    RecyclerView recy;
    private SciencePresenter<AllBkActivity> presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_bk);
        ButterKnife.bind(this);

        presenter = new SciencePresenter<>(this);
        presenter.request();


    }

    @Override
    public void show(AllBkBean bean) {
        final List<AllBkBean.ResultBean> result = bean.getResult();
        GridLayoutManager manager=new GridLayoutManager(this,2);
        recy.setLayoutManager(manager);
        MyAllBkAdapter allBkAdapter=new MyAllBkAdapter(R.layout.allbk,result);
        recy.setAdapter(allBkAdapter);
        allBkAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                //频道资讯列表
                Intent intent=new Intent(AllBkActivity.this,PdZxActivity.class);
                int id = result.get(position).getId();
                intent.putExtra("id",id);
                startActivity(intent);
            }
        });
    }
}
