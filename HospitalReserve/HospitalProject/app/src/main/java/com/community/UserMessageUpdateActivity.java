package com.community;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.TypeAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.ResponseEntry;
import com.pony.model.TypeModel;
import com.pony.model.UserModel;
import com.pony.util.FaceUtil;
import com.pony.util.ToastUtil;
import com.pony.view.CircleImageView;
import com.pony.view.DialogListMsg;
import com.pony.view.DialogMsg;
import com.squareup.picasso.Picasso;


public class UserMessageUpdateActivity extends BaseActivity {
    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private Button mSubmit;

    private EditText uname;
    private EditText uphone;


    private ImageView infoMusicOperating;
    private final int REQUEST_PICTURE_CHOOSE = 1;
    private final int REQUEST_CAMERA_IMAGE = 2;

    private Bitmap mImage = null;
    private byte[] mImageData = null;
    // authid为6-18个字符长度，用于唯一标识用户
    private Toast mToast;
    // 进度对话框
    private EditText online_authid;
    // 拍照得到的照片文件
    private File mPictureFile;
    // 采用身份识别接口进行在线人脸识别
    private String userImage;
    private LinearLayout mllImgae;
    private DialogMsg dialogMsg;
    private CircleImageView mivUserImg;


    private RadioGroup mrgChoice;
    private RadioButton mrb1 = null;
    private RadioButton mrb2 = null;

    private String choiceSex = "男";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inforuser);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {


        mrb1 = (RadioButton) findViewById(R.id.mrb1);
        mrb2 = (RadioButton) findViewById(R.id.mrb2);
        mrgChoice = (RadioGroup) findViewById(R.id.mrgChoice);



        uname = (EditText) findViewById(R.id.uname);
        uphone = (EditText) findViewById(R.id.uphone);

        mivUserImg = (CircleImageView) findViewById(R.id.mivUserImg);


        mSubmit = (Button) findViewById(R.id.mSubmit);
        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("更改用户信息");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mSubmit.setOnClickListener(this);
        mivUserImg.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                finish();
                break;
            case R.id.mSubmit:

                createTopicPost(true);
                break;


            case R.id.mivUserImg:
                dialogMsg.Show();
                break;

        }
    }


    @Override
    public void initData() {




        mrgChoice.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == mrb1.getId()) {
                    choiceSex = "男";
                } else if (checkedId == mrb2.getId()) {
                    choiceSex = "女";
                }
            }
        });


        UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");


        if(userModel.getuSex().equals("男")){
            mrb1.setChecked(true);
        }else{
            mrb2.setChecked(true);
        }

        userImage = userModel.getUserImage();
        uphone.setText(userModel.getUphone());
        uname.setText(userModel.getUname());
        if (!TextUtils.isEmpty(userImage)) {
            Picasso.with(this).load(Consts.URL_IMAGE + userImage).placeholder(R.drawable.default_drawable_show_pictrue).into(mivUserImg);
        }

        dialogMsg = new DialogMsg(this);
        dialogMsg.Set_Msg("请选择图像获取方式");
        dialogMsg.submit_no().setText("相册上传");
        dialogMsg.submit_ok().setText("拍照上传");

        dialogMsg.submit_no().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMsg.Close();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                startActivityForResult(intent, REQUEST_PICTURE_CHOOSE);
            }
        });
        dialogMsg.submit_ok().setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                dialogMsg.Close();
                mPictureFile = new File(Environment.getExternalStorageDirectory(), "picture" + System.currentTimeMillis() / 1000 + ".jpg");
                // 启动拍照,并保存到临时文件
                Intent mIntent = new Intent();
                mIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mPictureFile));
                mIntent.putExtra(MediaStore.Images.Media.ORIENTATION, 0);
                startActivityForResult(mIntent, REQUEST_CAMERA_IMAGE);

            }
        });


    }


    private void createTopicPost(boolean isShow) {

        AjaxParams params = new AjaxParams();
        params.put("action_flag", "updateUserInfor");
        params.put("uphone", uphone.getText().toString());
        params.put("uSex", choiceSex);
        params.put("uname", uname.getText().toString());
        params.put("uImg", userImage + "");
        params.put("uid", MemberUserUtils.getUid(this) + "");
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
    }



    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);
        switch (actionId) {

            case Consts.actionId.resultFlag:
                UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
                userModel.setUserImage(userImage);
                userModel.setUphone(uphone.getText().toString());
                userModel.setuSex(choiceSex);
                userModel.setUname(uname.getText().toString());
                MemberUserUtils.putBean(this, "user_messgae", userModel);
                ToastUtil.show(this,entry.getRepMsg());
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        finish();
                    }
                }, 2000);
                break;
        }
    }

    String likeId = "";
    String likeName = "";


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String fileSrc = null;



        if (resultCode == 10) {
            likeId = data.getStringExtra("likeId");
            likeName = data.getStringExtra("likeName");
            Log.i("pony_log",likeId);
            Log.i("pony_log",likeName);
        } else if (requestCode == REQUEST_PICTURE_CHOOSE) {

            if(data!=null){
                if ("file".equals(data.getData().getScheme())) {
                    // 有些低版本机型返回的Uri模式为file
                    fileSrc = data.getData().getPath();
                } else {
                    // Uri模型为content
                    String[] proj = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(data.getData(), proj, null, null, null);
                    cursor.moveToFirst();
                    int idx = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    fileSrc = cursor.getString(idx);
                    cursor.close();
                }
                // 跳转到图片裁剪页面
                FaceUtil.cropPicture(this, Uri.fromFile(new File(fileSrc)));
            }


        } else if (requestCode == REQUEST_CAMERA_IMAGE) {
            if (null == mPictureFile) {
                return;
            }

            fileSrc = mPictureFile.getAbsolutePath();
            updateGallery(fileSrc);
            // 跳转到图片裁剪页面
            FaceUtil.cropPicture(this, Uri.fromFile(new File(fileSrc)));
        } else if (requestCode == FaceUtil.REQUEST_CROP_IMAGE) {


            // 获取返回数据
            Bitmap bmp = data.getParcelableExtra("data");
            // 若返回数据不为null，保存至本地，防止裁剪时未能正常保存
            if (null != bmp) {
                FaceUtil.saveBitmapToFile(UserMessageUpdateActivity.this, bmp);
            }
            // 获取图片保存路径
            fileSrc = FaceUtil.getImagePath(UserMessageUpdateActivity.this);
            String[] arrPath = fileSrc.split("\\/");
            userImage = arrPath[arrPath.length - 1];
            Log.i("pony_log", userImage);
            Log.i("pony_log", arrPath[arrPath.length - 1]);
            // 获取图片的宽和高
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            mImage = BitmapFactory.decodeFile(fileSrc, options);

            // 压缩图片
            options.inSampleSize = Math.max(1, (int) Math.ceil(Math.max((double) options.outWidth / 1024f, (double) options.outHeight / 1024f)));
            options.inJustDecodeBounds = false;
            mImage = BitmapFactory.decodeFile(fileSrc, options);

            // 若mImageBitmap为空则图片信息不能正常获取
            if (null == mImage) {
                return;
            }

            // 部分手机会对图片做旋转，这里检测旋转角度
            int degree = FaceUtil.readPictureDegree(fileSrc);
            if (degree != 0) {
                // 把图片旋转为正的方向
                mImage = FaceUtil.rotateImage(degree, mImage);
            }

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            // 可根据流量及网络状况对图片进行压缩
            mImage.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            mImageData = baos.toByteArray();

            ((ImageView) findViewById(R.id.mivUserImg)).setImageBitmap(mImage);

            FinalHttp finalHttp = new FinalHttp();
            // 文件上传到服务器的位置

            AjaxParams params = new AjaxParams();
            // 获取要上传的本地资源
            try {
                params.put("userImage", new File(fileSrc));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            finalHttp.post(Consts.URL + Consts.APP.RegisterAction + "?action_flag=updateUserImage", params, new AjaxCallBack<Object>() {
                @Override
                public void onStart() {
                    // mbtnAdd.setText("开始上传");
                    super.onStart();
                }

                @Override
                public void onSuccess(Object o) {
                    // mbtnAdd.setText("上传成功");
                    ToastUtil.show(UserMessageUpdateActivity.this, "上传成功");
                    // updateUser(false);
                    super.onSuccess(o);
                }

                @Override
                public void onFailure(Throwable t, int errorNo, String strMsg) {
                    // mbtnAdd.setText("上传失败");
                    super.onFailure(t, errorNo, strMsg);
                }
            });

        }

    }

    private void updateGallery(String filename) {
        MediaScannerConnection.scanFile(this, new String[]{filename}, null, new MediaScannerConnection.OnScanCompletedListener() {

            @Override
            public void onScanCompleted(String path, Uri uri) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        /**
         * 设置为横屏
         */
        if (getRequestedOrientation() != ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }
    }

}
