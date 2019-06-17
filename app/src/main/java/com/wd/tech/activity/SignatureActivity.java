package com.wd.tech.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.SignatureBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.SciencePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignatureActivity extends BaseActivity  implements IContract.ISginature {


    @BindView(R.id.signature_finish)
    ImageView signatureFinish;
    private EditText signatureEditText;
    private TextView signature_textView;
    int num=1;
    private IContract.IPresenter iPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_signature;
    }

    @Override
    protected void findView() {
        signatureEditText = findViewById(R.id.signature_EditText);
        signature_textView = findViewById(R.id.signature_TextView);

    }

    @Override
    protected void initData() {
        iPresenter = new SciencePresenter<>(this);
       // MyApplication.signature = signatureEditText.getText().toString();
      //  Log.e("tag"," dasdas         "+MyApplication.signature);
        if (MyApplication.signature==null){
            signatureEditText.setText("您还编写没有签名");
            signature_textView.setText(8+"/20");
            num=8;
        }else{
            signatureEditText.setText(MyApplication.signature);
            num=MyApplication.signature.length();
            signature_textView.setText(MyApplication.signature.length()+"/20");
        }
        signatureEditText.setSelection(signatureEditText.getText().length());

    }

    @Override
    protected void setListener() {

        signatureEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                if (count==1){
                    int i = --num;
                    signature_textView.setText(i+"/20");
                }else{
                    int i = ++num;
                    signature_textView.setText(i+"/20");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });



      signatureEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                //当actionId == XX_SEND 或者 XX_DONE时都触发
                //或者event.getKeyCode == ENTER 且 event.getAction == ACTION_DOWN时也触发
                //注意，这是一定要判断event != null。因为在某些输入法上会返回null。
                if (actionId == EditorInfo.IME_ACTION_SEND
                        || actionId == EditorInfo.IME_ACTION_DONE
                        || (event != null && KeyEvent.KEYCODE_ENTER == event.getKeyCode() && KeyEvent.ACTION_DOWN == event.getAction())) {
                    //处理事件

                    finish();
                    iPresenter.sginature(signatureEditText.getText().toString());
                    overridePendingTransition(0, R.anim.anim_exit);
                }
                return false;
            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick(R.id.signature_finish)
    public void onViewClicked() {
        finish();
        overridePendingTransition(0, R.anim.anim_exit);
    }

    @Override
    public void sginature(SignatureBean registerBean) {
        Toast.makeText(mContext, registerBean.getMessage(), Toast.LENGTH_SHORT).show();
    }
}
