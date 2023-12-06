package com.community;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.CategoryAdapter;
import com.pony.adapter.PractitionersAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.listener.NewsObservable;
import com.pony.model.CCModel;
import com.pony.model.CategoryModel;
import com.pony.model.ResponseEntry;
import com.pony.time.JudgeDate;
import com.pony.time.MyAlertDialog;
import com.pony.time.ScreenInfo;
import com.pony.time.WheelMain;
import com.pony.util.ToastUtil;
import com.pony.view.DialogListMsg;
import com.pony.view.DialogMsg;

/**
 * 用户注册
 * 
 * @author wangxuan
 * 
 *         2016-12-16
 */
public class CreateCommunityActivity extends BaseActivity {

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnAdd;
	private EditText metname;
	private EditText metMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_creat_shetuan);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {

		metMessage = (EditText) findViewById(R.id.metMessage);
		metname = (EditText) findViewById(R.id.metName);

		mbtnAdd = (Button) findViewById(R.id.mSubmit);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("添加病史记录");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnAdd.setOnClickListener(this);
		metname.setText("吃药史");
		metMessage.setText("之前吃过感冒药,没有住院过！");

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mIvBack:
			CreateCommunityActivity.this.finish();
			break;
		case R.id.mSubmit:
			JobAction(true);
			break;

		}
	}

	@Override
	public void initData() {

	}

	private void JobAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addMessage");
		params.put("newsName", metname.getText().toString());
		params.put("newsMessage", metMessage.getText().toString());
		params.put("newsUserId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在提交...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			ToastUtil.show(CreateCommunityActivity.this, entry.getRepMsg());
			NewsObservable.getInstance().notifyStepChange("ok");
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					CreateCommunityActivity.this.finish();
				}
			}, 1000);
			break;
		}

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(CreateCommunityActivity.this, strMsg);

	}

}
