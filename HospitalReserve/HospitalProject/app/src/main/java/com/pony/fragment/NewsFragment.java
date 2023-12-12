package com.pony.fragment;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.community.CreateCommunityActivity;
import com.community.NewsMessageActivity;
import com.community.R;
import com.google.gson.reflect.TypeToken;
import com.pony.adapter.JpushAdapter;
import com.pony.adapter.NewsAdapter;
import com.pony.base.BaseFragment;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.listener.NewsObservable;
import com.pony.model.NewsModel;
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;


public class NewsFragment extends BaseFragment implements Observer{
	// 获取view
	private View rootView;
	
	// 获取控件
	private ListView mListMessage;
	View convertView;
	private LinearLayout mllNomessage;
	private List<NewsModel> list_result = new ArrayList<NewsModel>();
	NewsAdapter noticeAdapter;
	private ImageView mivCreateMessage;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_newsadd, null);
		initWidget();
		initData();
		return rootView;
	}

	@Override
	public void initWidget() {
		mllNomessage = (LinearLayout) rootView.findViewById(R.id.mllNomessage);
		mListMessage = (ListView) rootView.findViewById(R.id.mListMessage);
		mivCreateMessage = (ImageView) rootView.findViewById(R.id.mivCreateMessage);
		mivCreateMessage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				
				Intent intent = new Intent(getActivity(), CreateCommunityActivity.class);
				getActivity().startActivity(intent);
			}
		});
	}

	@Override
	public void onClick(View v) {
	
	}

	@Override
	public void initData() {
		listPhoneMessage(true);
	
	}
	private void listPhoneMessage(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listMessagePhoneNews");
		params.put("newsUserId",MemberUserUtils.getUid(getActivity()));
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
					list_result.clear();
//					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<NewsModel>>() {
//					}.getType());
//					JpushAdapter	categoryAdapter= new JpushAdapter(getActivity(), list_result);
//					mListMessage.setAdapter(categoryAdapter);
//					mllNomessage.setVisibility(View.GONE);
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
		ToastUtil.show(getActivity(), strMsg);

	}

	@Override
	public void onResume() {
		super.onResume();
		NewsObservable.getInstance().addObserver(NewsFragment.this);
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		NewsObservable.getInstance().deleteObserver(NewsFragment.this);
	}
	private int posDelete;


	@Override
	public void update(Observable arg0, Object arg1) {
		listPhoneMessage(false);
	}

}
