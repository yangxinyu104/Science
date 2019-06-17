package com.wd.tech.activity;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.LoginBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.encryption.RsaCoder;
import com.wd.tech.presenter.SciencePresenter;
import com.wd.tech.R;

public class LoginActivity extends BaseActivity implements IContract.ILogin {
    boolean isHideFirst =true;
    private ImageView login_eye;
    private EditText login_phone;
    private EditText login_pwd;
    private TextView login_regist;
    private ImageView login_weixin;
    private ImageView login_renlian;
    private Button login;
    private IContract.IPresenter iPresenter;
    private String encrypt;


    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void findView() {

        login_eye = findViewById(R.id.login_eye);
        login_phone = findViewById(R.id.login_phone);
        login_pwd = findViewById(R.id.login_pwd);
        login_regist = findViewById(R.id.login_regist);
        login_weixin = findViewById(R.id.login_weixin);
        login_renlian = findViewById(R.id.login_renlian);
        login = findViewById(R.id.login);

    }

    @Override
    protected void initData() {



        login_eye.setOnClickListener(this);
        login_regist.setOnClickListener(this);
        login_weixin.setOnClickListener(this);
        login_renlian.setOnClickListener(this);
        login.setOnClickListener(this);
        iPresenter = new SciencePresenter<>(this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_eye:
                if (isHideFirst == true) {
                    HideReturnsTransformationMethod instance = HideReturnsTransformationMethod.getInstance();
                    login_pwd.setTransformationMethod(instance);
                    isHideFirst = false;
                } else {
                    PasswordTransformationMethod instance = PasswordTransformationMethod.getInstance();
                    login_pwd.setTransformationMethod(instance);
                    isHideFirst = true;
                }
                int index = login_pwd.getText().toString().length();
                login_pwd.setSelection(index);
                break;
            case R.id.login_regist:
                toClass(this,RegisterActivity.class);
                overridePendingTransition(R.anim.anim_ins,R.anim.anim_exit2);
                break;
            case R.id.login:
                    if (login_pwd.getText().toString().equals("")){
                        Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
                    }else{
                        try {
                            encrypt = RsaCoder.encryptByPublicKey(login_pwd.getText().toString());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        iPresenter.login(login_phone.getText().toString(),encrypt);
                    }

                break;
            case  R.id.login_weixin:
                MyApplication.wxLogin(this);
                finish();
                FinishAnima();
                break;

        }

    }

    @Override
    public void login(LoginBean loginBean) {
        Toast.makeText(mContext, loginBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (loginBean.getMessage().equals("登录成功")){
            finish();
            FinishAnima();
            MyApplication.UserId = loginBean.getResult().getUserId();
            MyApplication.SessionId = loginBean.getResult().getSessionId();
            MyApplication.Head = loginBean.getResult().headPic;
            MyApplication.name = loginBean.getResult().getNickName();
            MyApplication.vipfalg = loginBean.getResult().getWhetherVip();
        }


    }

    public void FinishAnima(){
        overridePendingTransition(0,R.anim.anim_exit);
    }
}
