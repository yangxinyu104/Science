package com.wd.tech.zhangn.adapter;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.zhangn.bean.ZnZxXqBean;

import java.util.List;

public class TuiJianAdapter extends BaseQuickAdapter<ZnZxXqBean.ResultBean.InformationListBean,BaseViewHolder> {

    public TuiJianAdapter(int layoutResId, @Nullable List<ZnZxXqBean.ResultBean.InformationListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ZnZxXqBean.ResultBean.InformationListBean item) {
        SimpleDraweeView view = helper.getView(R.id.img);
        String s = item.getThumbnail();
        view.setImageURI(Uri.parse(s));
        helper.setText(R.id.text,item.getTitle()+"");

    }
}
