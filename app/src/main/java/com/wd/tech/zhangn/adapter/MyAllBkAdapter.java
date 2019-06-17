package com.wd.tech.zhangn.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.zhangn.bean.AllBkBean;

import java.util.List;

public class MyAllBkAdapter extends BaseQuickAdapter<AllBkBean.ResultBean,BaseViewHolder> {
    public MyAllBkAdapter(int layoutResId, @Nullable List<AllBkBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, AllBkBean.ResultBean item) {
        helper.setText(R.id.text,item.getName());
        SimpleDraweeView view = helper.getView(R.id.img);
        String pic = item.getPic();
        view.setImageURI(Uri.parse(pic));

    }
}
