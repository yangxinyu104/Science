package com.wd.tech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.wd.tech.R;
import com.wd.tech.bean.CommunityBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class CommunityAdapter extends RecyclerView.Adapter<CommunityAdapter.holder> {
    private final Context context;
    private final List<CommunityBean.ResultBean> list;
    setOnClickItem setOnClickItem;

    public CommunityAdapter(Context context, List<CommunityBean.ResultBean> list) {
        this.context = context ;
        this.list = list ;
    }

    @NonNull
    @Override
    public holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_community_adapter,null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final holder holder, final int i) {
        String headPic = list.get(i).getHeadPic();
        String file = list.get(i).getFile();
        String nickName = list.get(i).getNickName();
        long publishTime = list.get(i).getPublishTime();
        String signature = list.get(i).getSignature();
        String content = list.get(i).getContent();
        int comment = list.get(i).getComment();
        int praise = list.get(i).getPraise();
        final int great = list.get(i).getWhetherGreat();

        Date date = new Date(publishTime);
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        holder.headPic.setImageURI(headPic);
        holder.file.setImageURI(file);
        //Log.e("评论图片",""+file);
        holder.nickName.setText(nickName);
        holder.publishTime.setText(format.format(date));
        holder.signature.setText(signature);
        holder.content.setText(content);
        holder.comment.setText(comment+"");
        holder.praise.setText(praise+"");

        /*CommunityAdapterper adapterper = new CommunityAdapterper();
        holder.xRecyclerView.setAdapter(adapterper);*/

        holder.CK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (holder.CK.isChecked()){
                    holder.relativeLayout.setVisibility(View.VISIBLE);

                }else {
                    holder.relativeLayout.setVisibility(View.GONE);
                }
            }
        });
        //支持刷新加载
        holder.xRecyclerView.setLoadingMoreEnabled(true);
        holder.xRecyclerView.setPullRefreshEnabled(true);
        //设置方向
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        holder.xRecyclerView.setLayoutManager(linearLayoutManager);

        //适配器
        CommunitydianjiAdapter adapter = new CommunitydianjiAdapter(context,list.get(i).getCommunityCommentVoList());
        holder.xRecyclerView.setAdapter(adapter);

        //判断当前是否点赞
        if (great == 1){
            holder.imageView.setBackgroundResource(R.mipmap.common_icon_praise);
        }else {
            holder.imageView.setBackgroundResource(R.mipmap.common_icon_prise);
        }

        //点赞调用接口
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (great == 1){
                    setOnClickItem.onGreat(list.get(i).getId(),great);
                    list.get(i).setWhetherGreat(2);
                }else {
                    setOnClickItem.onGreat(list.get(i).getId(),great);
                    list.get(i).setWhetherGreat(1);
                }
            }
        });


    }

    //点击事件，接口回调
    public void setSetOnClickItem(setOnClickItem item){
        setOnClickItem = item;
    }
    public interface setOnClickItem{
        void onGreat(int id, int great);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class holder extends RecyclerView.ViewHolder {

        SimpleDraweeView headPic , file ;
        TextView nickName , publishTime , signature , content , comment , praise ;
        XRecyclerView xRecyclerView ;
        CheckBox CK ;
        RelativeLayout relativeLayout ;
        ImageView imageView ;
        public holder(View itemView) {
            super(itemView);
            headPic = itemView.findViewById(R.id.Community_Adapter_headPic);
            file = itemView.findViewById(R.id.Community_Adapter_file);
            nickName = itemView.findViewById(R.id.Community_Adapter_nickName);
            publishTime = itemView.findViewById(R.id.Community_Adapter_publishTime);
            signature = itemView.findViewById(R.id.Community_Adapter_signature);
            content = itemView.findViewById(R.id.Community_Adapter_content);
            comment = itemView.findViewById(R.id.Community_Adapter_comment);
            praise = itemView.findViewById(R.id.Community_Adapter_praise);
            xRecyclerView = itemView.findViewById(R.id.Community_Adapter_communityCommentVoList);
            CK = itemView.findViewById(R.id.Community_Adapter_common_icon_comment_n_hdpi);
            relativeLayout = itemView.findViewById(R.id.Community_Adapter_RelativeLayout);
            imageView = itemView.findViewById(R.id.Community_Adapter_praise_imager);
        }
    }
}
