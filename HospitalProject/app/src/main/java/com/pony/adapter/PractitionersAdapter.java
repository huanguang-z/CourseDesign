package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.R;
import com.pony.model.CCModel;
import com.pony.model.CategoryModel;

public class PractitionersAdapter extends BaseAdapter {
	private Context mContext;
	private List<CCModel> list_result;

	public PractitionersAdapter(Context mContext) {
		super();
		this.mContext = mContext;
	}

	public PractitionersAdapter(Context mContext, List<CCModel> list_msg) {
		super();
		this.mContext = mContext;
		this.list_result = list_msg;
		notifyDataSetChanged();
	}

	public List<CCModel> setData(List<CCModel> list_str) {
		return list_result = list_str;

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
		ViewHolder vh = null;

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.add_item, null);
			vh = new ViewHolder();
			vh.zoom_msg = (TextView) convertView.findViewById(R.id.zoom_msg);

			convertView.setTag(vh);

		} else {
			vh = (ViewHolder) convertView.getTag();
		}

		vh.zoom_msg.setText(list_result.get(position).getCcName());
		return convertView;
	}

	class ViewHolder {
		TextView zoom_msg;
	}
}