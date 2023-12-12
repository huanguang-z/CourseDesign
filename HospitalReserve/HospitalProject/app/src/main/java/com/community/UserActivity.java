package com.community;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.ToastUtil;
import com.pony.view.CircleImageView;
import com.pony.view.DialogTipMsg;
import com.squareup.picasso.Picasso;

/**
 * 
 * @author wangxuan 用户信息的展示
 */
public class UserActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mName;
	private String state;
	private RelativeLayout mtvUserName;
	private TextView mPhone;

	private RelativeLayout mrlPhone;
	private RelativeLayout mrlAddress;

	private TextView mIvStu;
	private RelativeLayout mrlMoney;

	private TextView umoney;
	private TextView tvidcard;
	private CircleImageView mivUserImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user);
		initWidget();
		initData();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			UserActivity.this.finish();
			break;

		case R.id.mrlAddress:
			Intent mrlAddress = new Intent(UserActivity.this, UpdatePswdActivity.class);
			startActivity(mrlAddress);
			break;

		case R.id.mrlMoney:
			break;
		case R.id.mIvStu:
			Intent mIvStu = new Intent(UserActivity.this, UserMessageUpdateActivity.class);
			startActivity(mIvStu);
			break;
		}
	}

	@Override
	public void initWidget() {
		tvidcard = (TextView) findViewById(R.id.tvidcard);
		mivUserImg = (CircleImageView) findViewById(R.id.mivUserImg);
		umoney = (TextView) findViewById(R.id.umoney);

		mrlMoney = (RelativeLayout) findViewById(R.id.mrlMoney);
		mrlMoney.setOnClickListener(this);

		mIvStu = (TextView) findViewById(R.id.mIvStu);

		mPhone = (TextView) findViewById(R.id.mPhone);
		mrlAddress = (RelativeLayout) findViewById(R.id.mrlAddress);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mName = (TextView) findViewById(R.id.mName);

		mTvTitle.setText("我的资料");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mrlAddress.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
		mIvStu.setText("修改");
		mIvStu.setVisibility(View.VISIBLE);
	}

	@Override
	public void initData() {

			UserModel userModel = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
			mName.setText(MemberUserUtils.getName(this)+"  "+userModel.getuSex());
			mPhone.setText(userModel.getUphone());
			tvidcard.setText("身份证号："+userModel.getuCard());
			umoney.setText("当前余额："+userModel.getUmoney()+"元");

			if (!TextUtils.isEmpty(userModel.getUserImage())) {
				Picasso.with(this).load(Consts.URL_IMAGE + userModel.getUserImage()).placeholder(R.drawable.default_drawable_show_pictrue).into(mivUserImg);
			}




	}

	@Override
	protected void onResume() {
		super.onResume();
	
	}


}
