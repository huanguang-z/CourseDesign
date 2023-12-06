package com.community;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.RechargeAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.model.RechargeModel;
import com.pony.model.ResponseEntry;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class MoneyListActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mIvStu;
	private ListView mListMessage;
	private List<RechargeModel> list_result = new ArrayList<RechargeModel>();
	private LinearLayout mllNomessage;
	RechargeAdapter orderListAdapter;
	private Button mivCreateMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_chongzhi);

	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;
		}
	}

	@Override
	public void initWidget() {


		mivCreateMessage = (Button) findViewById(R.id.mivCreateMessage);
		mivCreateMessage.setOnClickListener(this);
		mivCreateMessage.setVisibility(View.VISIBLE);

		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvStu.setText("添加");
		mIvStu.setVisibility(View.GONE);
		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (ListView) findViewById(R.id.mListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("充值记录");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
	}

	@Override
	public void initData() {
		MessageAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
			}
		});


		mivCreateMessage.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(MoneyListActivity.this, MoneyRechargeActivity.class);
				MoneyListActivity.this.startActivity(intent);
			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listCZPhone");
		params.put("rechargeUserId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
			case Consts.actionId.resultFlag:
				if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

					String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
					if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
						list_result = mGson.fromJson(entry.getData(), new TypeToken<List<RechargeModel>>() {
						}.getType());
						orderListAdapter = new RechargeAdapter(MoneyListActivity.this, list_result);
						mListMessage.setAdapter(orderListAdapter);
						mllNomessage.setVisibility(View.GONE);
					} else {
						mllNomessage.setVisibility(View.VISIBLE);
					}
				}
				break;
			default:
				break;
		}

	}

	@Override
	protected void onResume() {
		super.onResume();
		initWidget();
		initData();
	}
}
