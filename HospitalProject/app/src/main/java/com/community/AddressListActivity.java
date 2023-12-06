package com.community;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.SendAddressAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.AddressObservable;
import com.pony.db.MemberUserUtils;
import com.pony.model.JiJianModel;
import com.pony.model.ResponseEntry;


public class AddressListActivity extends BaseActivity implements Observer{

	// 鏍囬
	private TextView mTvTitle;
	// 杩斿洖
	private ImageView mIvBack;
	private ListView mListMessage;
	private List<JiJianModel> list_result = new ArrayList<JiJianModel>();
	private String state;
	private LinearLayout mllNomessage;
	private ImageView mivCreateMessage;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_im_create);
		initWidget();
		initData();
	}


	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.mIvBack:
				finish();
				break;
			case R.id.mivCreateMessage:
				Intent intent = new Intent(this, CreatJiJianActivity.class);
				startActivity(intent);
				break;
		}
	}

	@Override
	public void initWidget() {

		mivCreateMessage = (ImageView) findViewById(R.id.mivCreateMessage);
		mivCreateMessage.setOnClickListener(this);
		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (ListView) findViewById(R.id.mListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("就诊人管理");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
	}

	@Override
	public void initData() {
		MessageAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listJiJianPCMessage");
		params.put("jijianUserId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "姝ｅ湪鍔犺浇...");
	}

	SendAddressAdapter listAdapter;
	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
			case Consts.actionId.resultFlag:
				if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

					String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
					if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
						list_result.clear();
						list_result = mGson.fromJson(entry.getData(), new TypeToken<List<JiJianModel>>() {
						}.getType());
						listAdapter= new SendAddressAdapter(AddressListActivity.this, list_result);
						mListMessage.setAdapter(listAdapter);
						mllNomessage.setVisibility(View.GONE);
					} else {
						mllNomessage.setVisibility(View.VISIBLE);
					}
				}
				break;


		}

	}

	@Override
	public void onResume() {
		super.onResume();
		AddressObservable.getInstance().addObserver(this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		AddressObservable.getInstance().deleteObserver(this);

	}

	@Override
	public void update(Observable arg0, Object arg1) {
		MessageAction(false);
	}

}
