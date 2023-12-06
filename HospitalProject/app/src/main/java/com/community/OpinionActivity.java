package com.community;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.CategoryAdapter;
import com.pony.adapter.ReplyAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.db.ReviewObservable;
import com.pony.model.CategoryModel;
import com.pony.model.DortorModel;
import com.pony.model.ResponseEntry;
import com.pony.model.ReviewBean;
import com.pony.model.ReviewModel;
import com.pony.util.LoadingDialog;
import com.pony.util.ToastUtil;
import com.pony.view.ListviewForScrollView;

public class OpinionActivity extends BaseActivity {
	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private Button mbtnUpdate;
	private EditText metName;
	DortorModel noticeModel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_opinion);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {
		metName = (EditText) findViewById(R.id.metName);
		mbtnUpdate = (Button) findViewById(R.id.mbtnUpdate);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("医生评价");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mbtnUpdate.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mIvBack:
			OpinionActivity.this.finish();
			break;
		case R.id.mbtnUpdate:
			if (TextUtils.isEmpty(metName.getText().toString())) {
				ToastUtil.ShowCentre(OpinionActivity.this, "请输入留言内容");
				return;
			}
			addReview(true,metName.getText().toString());
			break;
		}
	}

	@Override
	public void initData() {
		noticeModel = (DortorModel) this.getIntent().getSerializableExtra("msg");
	}



	private void addReview(boolean isShow, String sendMessage) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "addReview");
		params.put("reviewUserId", MemberUserUtils.getUid(this));
		params.put("reviewUserName", MemberUserUtils.getName(this));
		params.put("reviewMessageId", noticeModel.getDoctorId() + "");
		params.put("reviewContent", sendMessage);
		httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultCode, isShow, "正在提交...");
	}


	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);
		
		switch (actionId) {
		case Consts.actionId.resultCode:
			ReviewObservable.getInstance().notifyStepChange("ok");
			ToastUtil.show(OpinionActivity.this, entry.getRepMsg());
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					OpinionActivity.this.finish();
				}
			}, 1000);

			break;
			

		}
		

	}

	@Override
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(OpinionActivity.this, strMsg);

	}

}
