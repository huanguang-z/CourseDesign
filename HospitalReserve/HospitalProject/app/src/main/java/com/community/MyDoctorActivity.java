package com.community;

import java.util.ArrayList;
import java.util.List;

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
import com.pony.adapter.DoctorAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.model.DeportmentModel;
import com.pony.model.DortorModel;
import com.pony.model.ResponseEntry;

/**
 * 类别
 * 
 * @author wangxuan
 * 
 *         2016-12-26
 */
public class MyDoctorActivity extends BaseActivity {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mIvStu;
	private ListView mListMessage;
	private List<DortorModel> list_result = new ArrayList<DortorModel>();
	private String state;
	private LinearLayout mllNomessage;
	
	
	private DeportmentModel msgMessage;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_im);
		initWidget();
		initData();
	}

	@Override
	protected void onResume() {
		super.onResume();
	
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mivCreateMessage:
			break;
		}
	}

	@Override
	public void initWidget() {
		msgMessage = (DeportmentModel) this.getIntent().getSerializableExtra("msg");
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvStu.setText("添加");
		mIvStu.setVisibility(View.GONE);
		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (ListView) findViewById(R.id.mListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		state = this.getIntent().getStringExtra("state");

		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
		
		
	}

	@Override
	public void initData() {

		mTvTitle.setText("查看医生");

		MessageAction(true);
		mListMessage.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {
				Intent intent = new Intent(MyDoctorActivity.this, DoctorMessageActivity.class);
				intent.putExtra("msg", list_result.get(pos));
				startActivity(intent);
			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listMesagePhoneDoctor");
		params.put("departmentId", msgMessage.getDepartmentId());
		httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}


	DoctorAdapter memAdapter;

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<DortorModel>>() {
					}.getType());
					memAdapter = new DoctorAdapter(MyDoctorActivity.this, list_result);
					mListMessage.setAdapter(memAdapter);
				} else {
					mllNomessage.setVisibility(View.VISIBLE);
				}
			}
			break;
		default:
			break;
		}

	}




}
