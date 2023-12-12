package com.pony.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.community.LoginActivity;
import com.community.MyApplyActivity;
import com.community.R;
import com.community.SoftMessageActivity;
import com.community.UserActivity;
import com.pony.base.BaseFragment;

/**
 * 
 * @author WangXuan
 * 
 */
public class FinanceFragment extends BaseFragment {

	// 获取view
	private View rootView;
	private RelativeLayout mtvUser;
	private RelativeLayout mrlFabu;
	private RelativeLayout mrlCollect;
	private RelativeLayout mtvSoft;
	
	private RelativeLayout mrlCommunity;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_content, null);

		return rootView;
	}

	/**
	 * 获取控件
	 */
	@Override
	public void initWidget() {
		
		mrlCommunity = (RelativeLayout) rootView.findViewById(R.id.mrlCommunity);
		mtvSoft = (RelativeLayout) rootView.findViewById(R.id.mtvSoft);
		mrlFabu = (RelativeLayout) rootView.findViewById(R.id.mrlFabu);
		mrlCollect = (RelativeLayout) rootView.findViewById(R.id.mrlCollect);
		mtvUser = (RelativeLayout) rootView.findViewById(R.id.mtvUser);
		mrlFabu.setOnClickListener(this);
		mtvUser.setOnClickListener(this);
		mtvSoft.setOnClickListener(this);
		mrlCollect.setOnClickListener(this);
		mrlCommunity.setOnClickListener(this);
		
	}

	/**
	 * 处理点击事件
	 */
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
	
		case R.id.mrlCollect:
			Intent mrlReview = new Intent(getActivity(), MyApplyActivity.class);
			getActivity().startActivity(mrlReview);
			break;
			

		case R.id.mtvSoft:
			Intent mtvSoft = new Intent(getActivity(), SoftMessageActivity.class);
			getActivity().startActivity(mtvSoft);
			break;

		case R.id.mtvUser:
			Intent mtvUser = new Intent(getActivity(), UserActivity.class);
			getActivity().startActivity(mtvUser);
			break;

		case R.id.mExit:
			Intent intent = new Intent(getActivity(), LoginActivity.class);
			startActivity(intent);
			getActivity().finish();
			break;
		default:
			break;
		}

	}

	/**
	 * 处理数据
	 */
	@Override
	public void initData() {

	}

	@Override
	public void onResume() {
		super.onResume();
		initWidget();
		initData();
	}
}
