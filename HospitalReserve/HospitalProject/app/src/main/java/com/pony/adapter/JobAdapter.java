package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.pony.model.JobModel;

public class JobAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<JobModel> list_result;
	private int posIndex;
	private Context mContext;

	public JobAdapter(Context context, List<JobModel> list_result) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
	}

	@Override
	public int getCount() {
		return list_result.size();
	}

	@Override
	public Object getItem(int position) {
		return list_result.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.look_item, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			holder.mtvTotaMoney = (TextView) convertView.findViewById(R.id.mtvTotaMoney);
			holder.mtvaddress = (TextView) convertView.findViewById(R.id.mtvaddress);
			holder.mtvTime = (TextView) convertView.findViewById(R.id.mtvTime);
			holder.mtvState = (TextView) convertView.findViewById(R.id.mtvState);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.mTvTitle.setText(list_result.get(position).getjName());
			holder.mtvTotaMoney.setText("职位工资:  " + list_result.get(position).getjMoney() + "元");
			holder.mtvaddress.setText("工作地点:  " + list_result.get(position).getjAddress());
			holder.mtvTime.setText("发布时间:  " + list_result.get(position).getjTime());
			holder.mtvState.setVisibility(View.GONE);
//			if (list_result.get(position).getOstate().equals("0")
//					|| TextUtils.isEmpty(list_result.get(position).getOstate())) {
//				holder.mtvState.setText("可申请");
//			} else {
//				holder.mtvState.setText("已申请");
//			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle;
		private TextView mtvTotaMoney;
		private TextView mtvaddress;
		private TextView mtvTime;
		private TextView mtvState;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
