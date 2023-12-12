package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.R;
import com.pony.model.RechargeModel;



public class RechargeAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<RechargeModel> list_result;
	private int posIndex;
	private Context mContext;

	public RechargeAdapter(Context context, List<RechargeModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_money_index, null);
			holder = new ViewHolder();
			holder.jobTitle = (TextView) convertView.findViewById(R.id.jobTitle);
			holder.jobMsg = (TextView) convertView.findViewById(R.id.jobMsg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.jobTitle.setText(list_result.get(position).getRechargeMoney()+"元");
		holder.jobMsg.setText("充值时间："+list_result.get(position).getRechargeTime());
		return convertView;

	}

	private class ViewHolder {
		private TextView jobMsg;
		private TextView jobTitle;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
