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
import com.pony.util.ToastUtil;

import net.tsz.afinal.http.AjaxParams;


/**
 */
public class ForgetPswdActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnUpdate;
	private EditText metName;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_forget_pswd);
		initWidget();
	}

	@Override
	public void initWidget() {
		metName = (EditText) findViewById(R.id.metName);
		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("忘记密码");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnUpdate.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
			case R.id.mIvBack:
				ForgetPswdActivity.this.finish();
				break;
			case R.id.mbtnUpdate:

				if (TextUtils.isEmpty(metName.getText().toString())) {
					ToastUtil.ShowCentre(ForgetPswdActivity.this, "请输入身份证号码");
					return;
				}


				if (metName.getText().toString().length()<17) {
					ToastUtil.ShowCentre(ForgetPswdActivity.this, "身份证号码位数不对");
					return;
				}
				RegisterAction(true);
				break;
		}
	}

	@Override
	public void initData() {

	}

	private void RegisterAction(boolean isShow) {


		String  cardInfor = metName.getText().toString();
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "updatePswd");
		params.put("pswd", cardInfor.substring(cardInfor.length()-6,cardInfor.length()));
		params.put("userId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFlag, isShow, "正在更新...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		ToastUtil.show(ForgetPswdActivity.this, entry.getRepMsg());
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				ForgetPswdActivity.this.finish();
			}
		}, 1000);
	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(ForgetPswdActivity.this, strMsg);

	}
}
