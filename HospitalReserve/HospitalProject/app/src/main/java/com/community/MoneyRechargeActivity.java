package com.community;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.ToastUtil;

import net.tsz.afinal.http.AjaxParams;


public class MoneyRechargeActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnUpdate;
	private EditText moneyInfor;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_money_cz);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {
		moneyInfor = (EditText) findViewById(R.id.moneyInfor);
		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("账户充值");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnUpdate.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.mIvBack:
				MoneyRechargeActivity.this.finish();
				break;
			case R.id.mbtnUpdate:

				if (TextUtils.isEmpty(moneyInfor.getText().toString())) {
					ToastUtil.ShowCentre(MoneyRechargeActivity.this, "请输入充值金额");
					return;
				}

				RegisterAction(true);
				break;
		}
	}

	@Override
	public void initData() {
		userModel  = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
	}

	private void RegisterAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updateCZ");
		params.put("userMoney", (Double.valueOf(userModel.getUmoney())+Double.valueOf(moneyInfor.getText().toString()))+"");
		params.put("umoney", moneyInfor.getText().toString()+"");
		params.put("userId", MemberUserUtils.getUid(this));
		params.put("userName", MemberUserUtils.getName(this));
		httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在更新...");
	}
	UserModel userModel;
	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		userModel.setUmoney((Double.valueOf(userModel.getUmoney())+Double.valueOf(moneyInfor.getText().toString()))+"");
		MemberUserUtils.putBean(this, "user_messgae", userModel);
		ToastUtil.show(MoneyRechargeActivity.this, entry.getRepMsg());

		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				MoneyRechargeActivity.this.finish();
			}
		}, 1000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(MoneyRechargeActivity.this, strMsg);

	}
}
