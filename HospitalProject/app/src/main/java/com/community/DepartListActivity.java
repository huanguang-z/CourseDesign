package com.community;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.DepartListAdapter;
import com.pony.adapter.DoctorListAdapter;
import com.pony.adapter.NewsAdapter;
import com.pony.adapter.OrderDoctorAdapter;
import com.pony.adapter.TypeListAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.listener.IntersetListner;
import com.pony.model.ApplyModel;
import com.pony.model.DeportmentModel;
import com.pony.model.DortorModel;
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;
import com.pony.view.DialogMsg;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;


public class DepartListActivity extends BaseActivity{

	// 标题
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;


	// 获取控件
	private ListView mDepartListMessage,mDoctorListMessage,mTypeListMessage,mTimeListMessage;
	private List<DeportmentModel> list_result = new ArrayList<DeportmentModel>();
	private List<DortorModel> list_doctor = new ArrayList<DortorModel>();
	private List<DortorModel> list_search = new ArrayList<DortorModel>();
	private CheckBox cbleixing;
	private CheckBox cbtime;
	private int choicePos=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_departlist);
		initWidget();
		initData();
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
		cbleixing = (CheckBox) findViewById(R.id.cbleixing);
		cbtime = (CheckBox) findViewById(R.id.cbtime);

		mTimeListMessage = (ListView) findViewById(R.id.mTimeListMessage);
		mTypeListMessage = (ListView) findViewById(R.id.mTypeListMessage);
		mDepartListMessage = (ListView) findViewById(R.id.mDepartListMessage);
		mDoctorListMessage = (ListView) findViewById(R.id.mDoctorListMessage);

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("科室信息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
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
				doctorListAdapter = new DoctorListAdapter(DepartListActivity.this,list_doctor);
				mDoctorListMessage.setAdapter(doctorListAdapter);
			}
		});


		mDoctorListMessage.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

				if(choicePos==0){
					Intent intent = new Intent(DepartListActivity.this, DoctorMessageActivity.class);
					intent.putExtra("msg",list_doctor.get(i));
					intent.putExtra("timeInfor",DepartListActivity.this.getIntent().getStringExtra("timeInfor"));
					DepartListActivity.this.startActivity(intent);
				}else{
					Intent intent = new Intent(DepartListActivity.this, DoctorMessageActivity.class);
					intent.putExtra("msg",list_search.get(i));
					intent.putExtra("timeInfor",DepartListActivity.this.getIntent().getStringExtra("timeInfor"));
					DepartListActivity.this.startActivity(intent);
				}


			}
		});

		cbleixing.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

				TypeListAdapter typeListAdapter = new TypeListAdapter(DepartListActivity.this,Consts.typeArr);
				mTypeListMessage.setAdapter(typeListAdapter);


				if(b){
					mTypeListMessage.setVisibility(View.VISIBLE);
				}else{
					mTypeListMessage.setVisibility(View.GONE);
				}
			}
		});


		cbtime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

				TypeListAdapter typeListAdapter = new TypeListAdapter(DepartListActivity.this,Consts.timeArr);
				mTimeListMessage.setAdapter(typeListAdapter);

				if(b){
					mTimeListMessage.setVisibility(View.VISIBLE);
				}else{
					mTimeListMessage.setVisibility(View.GONE);
				}
			}
		});


		mTypeListMessage.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
				mTypeListMessage.setVisibility(View.GONE);

				cbleixing.setChecked(false);
				choicePos = pos;

				if(pos==0){
					doctorListAdapter = new DoctorListAdapter(DepartListActivity.this,list_doctor);
					mDoctorListMessage.setAdapter(doctorListAdapter);
				}else{
					list_search.clear();
					for(int i=0;i<list_doctor.size();i++){


						Log.i("pony_log",list_doctor.get(i).getDoctorLevel()+""+Consts.typeArr[pos]);
						if(list_doctor.get(i).getDoctorLevel().equals(Consts.typeArr[pos])){
							list_search.add(list_doctor.get(i));
						}
					}

					doctorListAdapter = new DoctorListAdapter(DepartListActivity.this,list_search);
					mDoctorListMessage.setAdapter(doctorListAdapter);
				}



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
						memAdapter= new DepartListAdapter(DepartListActivity.this, list_result);
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
		ToastUtil.show(DepartListActivity.this, strMsg);

	}



}
