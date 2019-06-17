package com.wd.tech.zhangn.adapter;

import android.net.Uri;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.zhangn.bean.PingLMesBean;
import com.wd.tech.zhangn.bean.ZnZxXqBean;
import com.wd.tech.zhangn.utils.TimeUtils;

import java.sql.Time;
import java.util.List;

public class PingjiaAdapter extends BaseQuickAdapter<PingLMesBean.ResultBean,BaseViewHolder> {

    public PingjiaAdapter(int layoutResId, @Nullable List<PingLMesBean.ResultBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PingLMesBean.ResultBean item) {
        SimpleDraweeView view = helper.getView(R.id.img);
        String s = item.getHeadPic();
        view.setImageURI(Uri.parse(s));
        helper.setText(R.id.name,item.getNickName()+"");
        long time = item.getCommentTime();
        String s1 = TimeUtils.longToDate(time);
        helper.setText(R.id.time,s1+"");
        helper.setText(R.id.context,item.getContent());
    }


}
