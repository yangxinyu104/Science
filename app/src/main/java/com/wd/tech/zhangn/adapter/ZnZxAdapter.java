package com.wd.tech.zhangn.adapter;

import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.zhangn.bean.ZnZxBean;
import com.wd.tech.zhangn.utils.TimeUtils;

import java.util.List;

public class ZnZxAdapter extends BaseMultiItemQuickAdapter<ZnZxBean.ResultBean, BaseViewHolder> {
    /**
     * Same as QuickAdapter#QuickAdapter(Context,int) but with
     * some initialization data.
     *
     * @param data A new list is created out of this one to avoid mutable list
     */

    ScCall call;

    public interface ScCall {
        public void oncall(int itemId, int id);
    }

    public void setScCall(ScCall call) {
        this.call = call;
    }


    public ZnZxAdapter(List<ZnZxBean.ResultBean> data) {
        super(data);
        addItemType(ZnZxBean.ResultBean.type_1, R.layout.zx_iteam1);
        addItemType(ZnZxBean.ResultBean.type_2, R.layout.zx_iteam2);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ZnZxBean.ResultBean item) {
        int type = item.getType();
        switch (type) {
            case 0:
                //广告
                helper.setText(R.id.title, item.getInfoAdvertisingVo().getContent());
                SimpleDraweeView view = helper.getView(R.id.img);
                String url = item.getInfoAdvertisingVo().getPic();
                view.setImageURI(Uri.parse(url));

                break;
            case 1:

                SimpleDraweeView view1 = helper.getView(R.id.img);
                String s = item.getThumbnail();
                view1.setImageURI(Uri.parse(s));
                helper.setText(R.id.title, item.getTitle());
                helper.setText(R.id.t2, item.getSummary());
                helper.setText(R.id.ly, item.getSource());
                //时间
                String s1 = TimeUtils.longToDate(item.getReleaseTime());
                helper.setText(R.id.time, s1);
                helper.setText(R.id.xa, item.getCollection() + "");
                helper.setText(R.id.fx, item.getShare() + "");


                //点赞
                final int i = item.getWhetherCollection();
                final ImageView view2 = helper.getView(R.id.sc);
                Log.d(TAG, "i-----------------------:" + i);
                //1是收藏 2是不收藏
                if (i == 1) {
                    view2.setImageResource(R.mipmap.common_icon_collect_s);
                } else if(i==2){
                    view2.setImageResource(R.mipmap.common_icon_collect_n);
                }
                //点赞
                view2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int i1 = item.getWhetherCollection();
                        if (i1==1){
                            call.oncall(item.getId(),i1);
                            view2.setImageResource(R.mipmap.common_icon_collect_n);
                        }else if(i==2){
                            call.oncall(item.getId(),i1);
                            view2.setImageResource(R.mipmap.common_icon_collect_s);
                        }
                    }
                });







                break;
        }
    }
}
