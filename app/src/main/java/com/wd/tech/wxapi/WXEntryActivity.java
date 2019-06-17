package com.wd.tech.wxapi;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.bean.WechatBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.SciencePresenter;

public class WXEntryActivity extends Activity implements IWXAPIEventHandler,IContract.IWechatLogin {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxentry);
        MyApplication.LoginTools.handleIntent(getIntent(), this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        IContract.IPresenter iPresenter = new SciencePresenter<>(this);
       // Log.e("tag","((SendAuth.Resp) baseResp).code"  +  ((SendAuth.Resp) baseResp).code);
        iPresenter.wechatLogin(((SendAuth.Resp) baseResp).code);
    }

    @Override
    public void WechatLogin(WechatBean registerBean) {
        Toast.makeText(this, registerBean.message, Toast.LENGTH_SHORT).show();
        if (registerBean.message.equals("登陆成功")){
            MyApplication.UserId = registerBean.result.userId;
            MyApplication.SessionId  = registerBean.result.sessionId;
            MyApplication.Head = registerBean.result.headPic;
            MyApplication.name = registerBean.result.nickName;
            finish();
            FinishAnima();
        }

    }
    public void FinishAnima(){
        overridePendingTransition(0,R.anim.anim_exit);
    }
}
