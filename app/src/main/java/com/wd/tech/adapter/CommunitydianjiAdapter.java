package com.wd.tech.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.bean.CommunityBean;

import java.util.List;

public class CommunitydianjiAdapter extends RecyclerView.Adapter<CommunitydianjiAdapter.holder> {
    private final Context context;
    private final List<CommunityBean.ResultBean.CommunityCommentVoListBean> communityCommentVoList;

    public CommunitydianjiAdapter(Context context, List<CommunityBean.ResultBean.CommunityCommentVoListBean> communityCommentVoList) {
        this.context = context ;
        this.communityCommentVoList = communityCommentVoList ;
    }

    @NonNull
    @Override
    public CommunitydianjiAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.adapter_community_dianji_adapter, null);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunitydianjiAdapter.holder holder, int position) {
        String nickName = communityCommentVoList.get(position).getNickName();
        String content = communityCommentVoList.get(position).getContent();
        holder.name.setText(nickName);
        holder.title.setText(content);
    }

    @Override
    public int getItemCount() {
        return communityCommentVoList.size();
    }

    class holder extends RecyclerView.ViewHolder {
        TextView name , title ;
        public holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.adapter_community_dianji_adapter_name);
            title = itemView.findViewById(R.id.adapter_community_dianji_adapter_title);
        }
    }
}
