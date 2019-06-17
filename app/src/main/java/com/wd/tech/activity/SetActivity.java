package com.wd.tech.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.drawee.view.SimpleDraweeView;
import com.wd.tech.R;
import com.wd.tech.app.MyApplication;
import com.wd.tech.base.BaseActivity;
import com.wd.tech.bean.HeadBean;
import com.wd.tech.bean.UserBean;
import com.wd.tech.contract.IContract;
import com.wd.tech.presenter.SciencePresenter;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity implements IContract.ISet {

    @BindView(R.id.set_finish)
    ImageView setFinish;
    @BindView(R.id.set_backlogin)
    RelativeLayout setBacklogin;
    @BindView(R.id.set_head)
    SimpleDraweeView setHead;
    @BindView(R.id.set_name)
    TextView setName;
    @BindView(R.id.set_sex)
    TextView setSex;
    @BindView(R.id.set_qian)
    RelativeLayout setQian;
    @BindView(R.id.set_data)
    TextView setData;
    @BindView(R.id.set_phone)
    TextView setPhone;
    @BindView(R.id.set_email)
    TextView setEmail;
    @BindView(R.id.set_fen)
    TextView setFen;
    @BindView(R.id.set_vip)
    TextView setVip;
    @BindView(R.id.set_id)
    TextView setId;
    RelativeLayout setHeadRelativeLayout;
    private IContract.IPresenter iPresenter;
    private PopupWindow popupWindow;
    @Override
    protected int getLayout() {
        return R.layout.activity_set;
    }

    @Override
    protected void findView() {
        setHeadRelativeLayout = findViewById(R.id.set_head_RelativeLayout);

    }

    @Override
    protected void initData() {

        iPresenter = new SciencePresenter<>(this);
        iPresenter.user();
        applypermission();

    }

    @Override
    protected void setListener() {
        setHeadRelativeLayout.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        setHeadRelativeLayout.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View v) {
                View view_popupwindow = View.inflate(SetActivity.this, R.layout.view_popupwindow, null);
                popupWindow = new PopupWindow(view_popupwindow, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                LinearLayout  popw_dimess  = view_popupwindow.findViewById(R.id.popw_dimess);
                LinearLayout  popw_xc  = view_popupwindow.findViewById(R.id.popw_xc);
                LinearLayout  popw_ps  = view_popupwindow.findViewById(R.id.popw_ps);
                popw_ps.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent openCameraIntent = new Intent(
                                MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(openCameraIntent, 1);
                        // takePictureByCamera(SetActivity.this, 1);
                    }
                });
                popw_xc.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent openAlbumIntent = new Intent(
                                Intent.ACTION_PICK);
                        openAlbumIntent.setType("image/*");
                        //用startActivityForResult方法，待会儿重写onActivityResult()方法，拿到图片做裁剪操作
                        startActivityForResult(openAlbumIntent, 2);
                    }
                });





                popw_dimess.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                });


                popupWindow.setBackgroundDrawable(new BitmapDrawable());
                popupWindow.setOutsideTouchable(true);
                popupWindow.setAnimationStyle(R.style.popwin_anim_style);
                popupWindow.showAtLocation(view_popupwindow, Gravity.CENTER_HORIZONTAL, 0, 100);
            }
        });

    }
    public void applypermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            //检查是否已经给了权限
            int CAMERAmissions = ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CAMERA);
            if (CAMERAmissions != PackageManager.PERMISSION_GRANTED) {//没有给权限
                Log.e("permission", "动态申请CAMERA");
                ActivityCompat.requestPermissions(SetActivity.this, new String[]{Manifest.permission.CAMERA}, 1);
            }

        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    //第二步就是返回的时候获取相册的路径
    File file1;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //在相册里面选择好相片之后调回到现在的这个activity中
        switch (requestCode) {
            case 1:
                Bundle extras = data.getExtras();
                Log.e("tag","1"  + extras);
                GetFiles(data);
                break;
            case 2:
                Uri uri = data.getData();
                crop(uri);
                Log.e("tag","2");
                break;
            case 3:
                Log.e("tag","3");
                //从相册中取出照片
                GetFiles(data);
                break;
        }

    }
    private void GetFiles(Intent data) {
        Bitmap bitmap = data.getParcelableExtra("data");
        String path = Environment.getExternalStorageDirectory() + "/yxy";
        File file = new File(path);//将要保存图片的路径
        if (!file.exists()) {
            file.mkdir();
        }
        file1 = new File(file, "123456.png");
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file1));
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            bos.flush();
            bos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        iPresenter.head(file1);
    }
    public void crop(Uri uri) {
// 裁剪图片意图
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // 裁剪框的比例，1：1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // 裁剪后输出图片的尺寸大小
        intent.putExtra("outputX", 250);
        intent.putExtra("outputY", 250);

        intent.putExtra("outputFormat", "JPEG");// 图片格式
        intent.putExtra("noFaceDetection", false);// 取消人脸识别
        intent.putExtra("return-data", true);
        // 开启一个带有返回值的Activity，请求码为PHOTO_REQUEST_CUT
        startActivityForResult(intent, 3);
    }

    @OnClick({R.id.set_finish, R.id.set_backlogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.set_finish:
                finish();
                overridePendingTransition(0, R.anim.anim_exit);
                break;
            case R.id.set_backlogin:
                MyApplication.UserId = 0;
                MyApplication.SessionId = null;
                finish();
                overridePendingTransition(0, R.anim.anim_exit);
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        iPresenter.user();
    }

    @Override
    public void user(UserBean registerBean) {
        MyApplication.signature = registerBean.getResult().getSignature();
        Log.e("tag","registerBean.getResult().getHeadPic()  "   +registerBean.getResult().getHeadPic() );
        setHead.setImageURI(registerBean.getResult().getHeadPic());
        setName.setText(registerBean.getResult().getNickName());
        if (registerBean.getResult().getSex() == 1) {
            setSex.setText("男");
        } else {
            setSex.setText("女");
        }
        String time = longToDate(registerBean.getResult().birthday);
        setData.setText(time);
        setPhone.setText(registerBean.getResult().getPhone());
        setEmail.setText(registerBean.getResult().getEmail());
        setFen.setText(registerBean.getResult().getIntegral() + "");
        if (registerBean.getResult().getWhetherVip() == 1) {
            setVip.setText("是");
        } else {
            setVip.setText("否");
        }
        if (registerBean.getResult().getWhetherFaceId() == 1) {
            setId.setText("已绑定");
        } else {
            setVip.setText("未绑定");
        }
    }

    @Override
    public void head(HeadBean headBean) {
        Toast.makeText(mContext, headBean.getMessage(), Toast.LENGTH_SHORT).show();
        popupWindow.dismiss();
        MyApplication.Head = headBean.getResult();
        iPresenter.user();
    }
    public static String longToDate(long lo) {
        Date date = new Date(lo);
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sd.format(date);
    }

    @OnClick(R.id.set_qian)
    public void onViewClicked() {
        toClass(this, SignatureActivity.class);
        overridePendingTransition(R.anim.anim_ins, R.anim.anim_exit2);

    }
}
