package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.R;
import com.pony.model.ApplyModel;
import com.pony.model.OrderModel;

public class OrderAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ApplyModel> list_result;
	private int posIndex;
	private Context mContext;

	public OrderAdapter(Context context, List<ApplyModel> list_result) {
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
			convertView = inflater.inflate(R.layout.borrow_itemmsg, null);
			holder = new ViewHolder();
			holder.mtvtitle = (TextView) convertView.findViewById(R.id.mtvtitle);
			holder.courseEnd = (TextView) convertView.findViewById(R.id.courseEnd);
			holder.courseStart = (TextView) convertView.findViewById(R.id.courseStart);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mtvtitle.setText(list_result.get(position).getApplyMessageName());
		holder.courseEnd.setText(list_result.get(position).getApplyTime());
		return convertView;

	}

	private class ViewHolder {
		private TextView mtvtitle;
		private TextView courseEnd;
		private TextView courseStart;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
