package com.wd.tech.fragment;
//类注释设置模板


import android.app.Application;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.bean.LookFroFriendBeen;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.SciencePresenter;

/**
 * @Description: $description$ 类（或接口）是
 * @Author: yuhua
 * @Date: $date$
 */
public class AddFriendFragment extends BaseFragment implements IContract.IFriend {
    ImageView imageView;
    EditText editText;
    IContract.IPresenter iPresenter;
    TextView textView;
    @Override
    protected int getLayout() {
        return R.layout.fragment_addfriend;
    }

    @Override
    protected void initView(View view) {

        imageView=view.findViewById(R.id.add_friend_img_id);
        editText=view.findViewById(R.id.add_friend_edit_id);
        textView=view.findViewById(R.id.text_me_id);
    }

    @Override
    protected void initData() {
       iPresenter=new SciencePresenter<>(this);
        imageView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        Log.e("tag",MyApplication.UserId + MyApplication.SessionId);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = editText.getText().toString();
                iPresenter.friend(MyApplication.UserId,MyApplication.SessionId,s);

            }
        });
    }

    @Override
    public void friend(LookFroFriendBeen lookFroFriendBeen) {

        //Log.e("tag","lookFroFriendBeen.getMessage()   "  +lookFroFriendBeen.getMessage());

        String message = lookFroFriendBeen.getMessage();

        if(!message.equals("查询成功")){
            textView.setVisibility(TextView.VISIBLE);
        }else{
            textView.setVisibility(TextView.INVISIBLE);
        }

    }
}
