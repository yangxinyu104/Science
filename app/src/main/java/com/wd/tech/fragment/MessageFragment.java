package com.wd.tech.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.wd.tech.R;
import com.wd.tech.base.BaseFragment;
import com.wd.tech.popupwindow.ConfirmPopWindow;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * @Author：Y
 * @E-mail： 2447892835@qq.com
 * @Date：2019.6.9 16:11
 * @Description：YangXinYu
 */
public class MessageFragment extends BaseFragment {

    ImageView imageView;
    @BindView(R.id.rbmessage_id)
    RadioButton rbmessageId;
    @BindView(R.id.rblinkman_id)
    RadioButton rblinkmanId;
    Unbinder unbinder;

    private RotateAnimation rotateAnimation;

    @Override
    protected int getLayout() {
        return R.layout.fragment_message;
    }

    @Override
    protected void initView(View view) {

        imageView = view.findViewById(R.id.cross_id);
    }

    @Override
    protected void initData() {

        //十字架
        rotateAnimation = (RotateAnimation) AnimationUtils.loadAnimation(getContext(), R.anim.rotating_anim);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageView.setAnimation(rotateAnimation);
                imageView.startAnimation(rotateAnimation);


                new ConfirmPopWindow(getActivity()).showAtBottom(imageView);
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.rbmessage_id, R.id.rblinkman_id})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rbmessage_id:
                rbmessageId.setTextColor(Color.WHITE);
                rblinkmanId.setTextColor(Color.BLACK);
                break;
            case R.id.rblinkman_id:
                rbmessageId.setTextColor(Color.BLACK);
                rblinkmanId.setTextColor(Color.WHITE);
                break;
        }
    }
}
