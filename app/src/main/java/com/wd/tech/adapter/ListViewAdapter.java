package com.wd.tech.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.wd.tech.R;
import com.wd.tech.app.MyApplication;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.11 14:56
 * @Description：YangXinYu
 */
public class ListViewAdapter extends BaseAdapter {

    String title []  = {"我的收藏","我的关注","我的贴子","我的通知","我的积分","我的任务","设置"};
    int image [] = {R.mipmap.collect,R.mipmap.attention,R.mipmap.card,R.mipmap.notices,R.mipmap.notice,R.mipmap.task,R.mipmap.setting};
    private LayoutInflater mInflater;

    @Override
    public int getCount() {
        return 7;
    }

    @Override
    public Object getItem(int position) {
        return title[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView =  View.inflate(MyApplication.GetContext(),R.layout.list_item, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.listview_image);
            viewHolder.textView = (TextView)convertView.findViewById(R.id.listview_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.imageView.setImageResource(image[position]);
        viewHolder.textView.setText(title[position]);
        return convertView;
    }

    private final class ViewHolder {
        ImageView imageView;
        TextView textView;
    }

}
