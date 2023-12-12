package com.community;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.base.BaseActivity;
import com.pony.model.NewsModel;
import com.pony.model.NoticeModel;

public class NewsMessageActivity extends BaseActivity {
	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 查询按钮
	private TextView newsName;
	private TextView mtvcontent;
	private TextView newsTime;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_newsmsg);
		initWidget();
		initData();
	}

	@Override
	public void initWidget() {
		
		newsName = (TextView) findViewById(R.id.newsName);
		mtvcontent = (TextView) findViewById(R.id.mtvcontent);
		newsTime = (TextView) findViewById(R.id.newsTime);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("详情");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		

	}

	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.mIvBack:
			NewsMessageActivity.this.finish();
			break;
		}
	}

	@Override
	public void initData() {


		NoticeModel	noticeModel =(NoticeModel) this.getIntent().getSerializableExtra("msg");
		newsName.setText(noticeModel.getNewsTitle());
		mtvcontent.setText("        "+noticeModel.getNewsContent());
		newsTime.setText(noticeModel.getNewsTime());
	}

	



}
