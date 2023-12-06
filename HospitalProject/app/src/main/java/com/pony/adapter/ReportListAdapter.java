package com.pony.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.R;

public class ReportListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private String[] list_result;
	private int posIndex;

	public ReportListAdapter(Context context, String[] list_result) {
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
	}

	@Override
	public int getCount() {
		return list_result.length;
	}

	@Override
	public Object getItem(int position) {
		return list_result[position];
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.report_item, null);
			holder = new ViewHolder();
			holder.mTvTitle = (TextView) convertView.findViewById(R.id.mTvTitle);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.mTvTitle.setText(list_result[position]);
		
//		if(posIndex==position){
//			holder.title.setTextColor(Color.parseColor("#ff0000"));
//		}else{
//			holder.title.setTextColor(Color.parseColor("#333333"));
//		}
		return convertView;

	}

	private class ViewHolder {
		private TextView mTvTitle;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
