package com.community;

import net.tsz.afinal.http.AjaxParams;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.model.ResponseEntry;
import com.pony.util.LoadingDialog;
import com.pony.util.ToastUtil;

/**
 * 用户注册
 * 
 *
 */
public class RegisterActivity extends BaseActivity {

	private String choiceType = "男";
	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnReg;
	private EditText uAge;
	private EditText metName;
	private EditText metPhone;
	private EditText metPswd;
	
	// 加在dialog
	private LoadingDialog mdialog;
	private RadioGroup mrgChoice;
	private RadioButton mrb1 = null;
	private RadioButton mrb2 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {

		mrb1 = (RadioButton) findViewById(R.id.mrb1);
		mrb2 = (RadioButton) findViewById(R.id.mrb2);
		mrgChoice = (RadioGroup) findViewById(R.id.mrgChoice);
		mdialog = new LoadingDialog(this, "正在注册");
		metName = (EditText) findViewById(R.id.metName);
		metPhone = (EditText) findViewById(R.id.metPhone);
		metPswd = (EditText) findViewById(R.id.metPswd);
		
		uAge = (EditText) findViewById(R.id.uAge);
		

		mbtnReg = (Button) findViewById(R.id.mbtnReg);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("注册");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnReg.setOnClickListener(this);
		
		
		mrgChoice.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				if (checkedId == mrb1.getId()) {
					choiceType = "男";
				} else if (checkedId == mrb2.getId()) {
					choiceType = "女";
				}
			}
		});

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mIvBack:
			RegisterActivity.this.finish();
			break;
		case R.id.mbtnReg:
			
			
			if (TextUtils.isEmpty(metName.getText().toString())) {
				ToastUtil.ShowCentre(RegisterActivity.this, "请输入用户名");
				return;
			}
			
			if (TextUtils.isEmpty(metPhone.getText().toString())) {
				ToastUtil.ShowCentre(RegisterActivity.this, "请输入手机号码");
				return;
			}

			if (TextUtils.isEmpty(uAge.getText().toString())) {
				ToastUtil.ShowCentre(RegisterActivity.this, "请输入身份证号");
				return;
			}

			if (TextUtils.isEmpty(metPswd.getText().toString())) {
				ToastUtil.ShowCentre(RegisterActivity.this, "请输入密码");
				return;
			}
			
			registUserPost(true);
			break;
		}
	}

	@Override
	public void initData() {
		
		

	}

	private void registUserPost(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "add");
		params.put("uname", metName.getText().toString());
		params.put("upswd", metPswd.getText().toString());
		params.put("uphone", metPhone.getText().toString());
		params.put("uAge", uAge.getText().toString());
		params.put("choiceType", choiceType+"");
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在注册...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		mdialog.dismiss();
		ToastUtil.show(RegisterActivity.this, entry.getRepMsg());
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				RegisterActivity.this.finish();
			}
		}, 1000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		mdialog.dismiss();
		ToastUtil.show(RegisterActivity.this, strMsg);

	}
}
