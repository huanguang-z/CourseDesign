package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.R;
import com.pony.model.JiJianModel;


public class SendAddressAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<JiJianModel> list_result;
	private int posIndex;
	private Context mContext;

	public SendAddressAdapter(Context context, List<JiJianModel> list_result) {
		inflater = LayoutInflater.from(context);
		this.mContext = context;
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
			convertView = inflater.inflate(R.layout.item_address, null);
			holder = new ViewHolder();
			holder.mtv1 = (TextView) convertView.findViewById(R.id.mtv1);
			holder.mtv2 = (TextView) convertView.findViewById(R.id.mtv2);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		try {
			holder.mtv1.setText(list_result.get(position).getJijianName()+"-"+list_result.get(position).getJijianPhone());
			holder.mtv2.setText("关系："+list_result.get(position).getJijianAddresse());
			
		} catch (Exception e) {
			// TODO: handle exception
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView mtv1;
		private TextView mtv2;
		
		
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
