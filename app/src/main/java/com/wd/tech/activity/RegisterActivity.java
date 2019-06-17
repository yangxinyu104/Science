package com.wd.tech.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.RegisterBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.encryption.RsaCoder;
import com.wd.tech.presenter.SciencePresenter;
import com.wd.tech.R;

public class RegisterActivity extends BaseActivity implements IContract.IRegister {


    private EditText register_name;
    private EditText register_phone;
    private EditText register_pwd;
    private Button register;
    private TextView register_note;
    private IContract.IPresenter iPresenter;
    private String encrypt;

    @Override
    protected int getLayout() {
        return R.layout.activity_register;
    }

    @Override
    protected void findView() {
        register_name = findViewById(R.id.register_name);
        register_phone = findViewById(R.id.register_phone);
        register_pwd = findViewById(R.id.register_pwd);
        register = findViewById(R.id.register);
        register_note = findViewById(R.id.register_note);

    }

    @Override
    protected void initData() {
        register.setOnClickListener(this);
        iPresenter = new SciencePresenter<>(this);
    }

    @Override
    protected void setListener() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                if (register_pwd.getText().toString().equals("")){
                    Toast.makeText(mContext, "密码不能为空", Toast.LENGTH_SHORT).show();
                }else{
                    try {
                        encrypt = RsaCoder.encryptByPublicKey(register_pwd.getText().toString());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    iPresenter.register(register_phone.getText().toString(),register_name.getText().toString(),encrypt);
                }


                break;
        }

    }

    @Override
    public void register(RegisterBean registerBean) {
        Toast.makeText(mContext, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
        if (registerBean.getMessage().equals("注册成功")){
            finish();
            overridePendingTransition(0,R.anim.anim_exit);
        }

    }
}
