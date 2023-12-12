package com.pony.fragment;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.community.CreateCommunityActivity;
import com.community.DoctorMessageActivity;
import com.community.MyDoctorActivity;
import com.community.R;
import com.google.gson.reflect.TypeToken;
import com.pony.adapter.DepartListAdapter;
import com.pony.adapter.DoctorListAdapter;
import com.pony.adapter.MyPlanAdapter;
import com.pony.adapter.NewsAdapter;
import com.pony.adapter.TypeListAdapter;
import com.pony.base.BaseFragment;
import com.pony.config.Consts;
import com.pony.model.DeportmentModel;
import com.pony.model.DortorModel;
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;


public class DepartFragment extends BaseFragment {
	// 获取view
	private View rootView;

	// 获取控件
	private ListView mDepartListMessage,mDoctorListMessage,mTypeListMessage,mTimeListMessage;
	private List<DeportmentModel> list_result = new ArrayList<DeportmentModel>();

	private List<DortorModel> list_doctor = new ArrayList<DortorModel>();
	private List<DortorModel> list_search = new ArrayList<DortorModel>();
	NewsAdapter noticeAdapter;
	private CheckBox cbleixing;
	private CheckBox cbtime;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_depart, null);
		initWidget();
		initData();
		return rootView;
	}

	@Override
	public void initWidget() {

		cbleixing = (CheckBox) rootView.findViewById(R.id.cbleixing);
		cbtime = (CheckBox) rootView.findViewById(R.id.cbtime);

		mTimeListMessage = (ListView) rootView.findViewById(R.id.mTimeListMessage);
		mTypeListMessage = (ListView) rootView.findViewById(R.id.mTypeListMessage);
		mDepartListMessage = (ListView) rootView.findViewById(R.id.mDepartListMessage);
		mDoctorListMessage = (ListView) rootView.findViewById(R.id.mDoctorListMessage);



	}

	@Override
	public void onClick(View v) {

	}


	private int posDoctor;
	DoctorListAdapter doctorListAdapter;
	@Override
	public void initData() {
		listPhoneMessage(true);

		mDepartListMessage.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
				posDoctor = pos;

				for(int i=0;i<list_result.size();i++){
                    DeportmentModel deportmentModel = list_result.get(i);
					if(i==pos){
						deportmentModel.setDepartmentChoice(true);
					}else{
						deportmentModel.setDepartmentChoice(false);
					}
					list_result.set(i,deportmentModel);
				}

				memAdapter.notifyDataSetChanged();

				list_doctor = list_result.get(pos).getDoctorList();
				doctorListAdapter = new DoctorListAdapter(getActivity(),list_doctor);
				mDoctorListMessage.setAdapter(doctorListAdapter);
			}
		});


		mDoctorListMessage.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
				Intent intent = new Intent(getActivity(), DoctorMessageActivity.class);
				intent.putExtra("msg",list_result.get(posDoctor).getDoctorList().get(i));
				getActivity().startActivity(intent);
			}
		});

		cbleixing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

				TypeListAdapter typeListAdapter = new TypeListAdapter(getActivity(),Consts.typeArr);
				mTypeListMessage.setAdapter(typeListAdapter);


				if(b){
					mTypeListMessage.setVisibility(View.VISIBLE);
				}else{
					mTypeListMessage.setVisibility(View.GONE);
				}



			}
		});




		mTypeListMessage.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
				mTypeListMessage.setVisibility(View.GONE);
				cbleixing.setChecked(false);


				list_search.clear();
				for(int i=0;i<list_doctor.size();i++){


					Log.i("pony_log",list_doctor.get(i).getDoctorLevel()+""+Consts.typeArr[pos]);
					if(list_doctor.get(i).getDoctorLevel().equals(Consts.typeArr[pos])){
						list_search.add(list_doctor.get(i));
					}
				}


				doctorListAdapter = new DoctorListAdapter(getActivity(),list_search);
				mDoctorListMessage.setAdapter(doctorListAdapter);


			}
		});



	}

	private void listPhoneMessage(boolean isShow) {
		AjaxParams params = new AjaxParams();
		params.put("action_flag", "listMesageAllPhoneDepartment");
		httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
	}
	DepartListAdapter memAdapter;
	@Override
	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		super.callBackSuccess(entry, actionId);

		switch (actionId) {
		case Consts.actionId.resultFlag:
			if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {
				String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);

				if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
					list_result.clear();
					list_result = mGson.fromJson(entry.getData(), new TypeToken<List<DeportmentModel>>() {
					}.getType());
					memAdapter= new DepartListAdapter(getActivity(), list_result);
					mDepartListMessage.setAdapter(memAdapter);
				} else {
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



}
