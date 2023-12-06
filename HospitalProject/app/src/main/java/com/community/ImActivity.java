package com.community;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.JobAdapter;
import com.pony.adapter.LookListAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.listview.XListView;
import com.pony.listview.XListView.IXListViewListener;
import com.pony.model.CategoryModel;
import com.pony.model.JobModel;
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;

/**
 * 类别
 * 
 * @author wangxuan
 * 
 *         2016-12-26
 */
public class ImActivity extends BaseActivity implements IXListViewListener {

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	private TextView mIvStu;
	private XListView mListMessage;
	private String state;
	private LinearLayout mllNomessage;
	List<JobModel> list_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_im);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		case R.id.mIvStu:
			break;

		}
	}

	@Override
	public void initWidget() {
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvStu.setText("添加");
		mIvStu.setVisibility(View.GONE);
		mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
		mListMessage = (XListView) findViewById(R.id.mListMessage);

		mListMessage.setPullRefreshEnable(true);// 开启下拉刷新
		mListMessage.setPullLoadEnable(true);// 开启上滑加载更多
		mListMessage.setXListViewListener(this);
		mListMessage.setFooterDividersEnabled(true);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		state = this.getIntent().getStringExtra("state");
		mTvTitle.setText("发布历史记录");
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
				// Intent intent = new Intent(ImActivity.this,
				// ApplyUserListActivity.class);
				// intent.putExtra("msg", list_result.get(pos-1));
				// ImActivity.this.startActivity(intent);
			}
		});
	}

	private void MessageAction(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listMessage");
		params.put("jUserId", MemberUserUtils.getUid(this));
		httpPost(Consts.URL + Consts.APP.JobAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}

	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					mListMessage.stopRefresh();
					mllNomessage.setVisibility(View.GONE);
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<JobModel>>() {
					}.getType());
					JobAdapter imAdapter = new JobAdapter(ImActivity.this, list_result);
					mListMessage.setAdapter(imAdapter);
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
	protected void callBackAllFailure(String strMsg, int actionId) {
		super.callBackAllFailure(strMsg, actionId);
		ToastUtil.show(ImActivity.this, strMsg);

	}

	@Override
	protected void onResume() {
		super.onResume();
		initWidget();
		initData();
	}

	@Override
	public void onRefresh() {
		MessageAction(false);
	}

	@Override
	public void onLoadMore() {
		new Handler().postDelayed(new Runnable() {

			@Override
			public void run() {
				mListMessage.stopLoadMore();
			}
		}, 1000);
	}

}
