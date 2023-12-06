package com.pony.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.community.R;
import com.pony.model.DeportmentModel;

import java.util.List;


/**
 * 消息适配器
 */
public class DepartSearchListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<DeportmentModel> list_result;
	private int posIndex;
	private Context mContext;

	public DepartSearchListAdapter(Context context, List<DeportmentModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_departsearch_index, null);
			holder = new ViewHolder();
			holder.departName = (TextView) convertView.findViewById(R.id.departName);
			holder.departInfor = (TextView) convertView.findViewById(R.id.departInfor);
			holder.mllbg = (LinearLayout) convertView.findViewById(R.id.mllbg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.departName.setText(list_result.get(position).getDepartmentName());
		holder.departInfor.setText("疾病："+list_result.get(position).getDepartmentInfor());
		if(list_result.get(position).isDepartmentChoice()){
			holder.mllbg.setBackgroundColor(Color.GRAY);
		}else{
			holder.mllbg.setBackgroundColor(Color.WHITE);
		}
		return convertView;

	}

	private class ViewHolder {
		private TextView departName;
		private TextView departInfor;

		private LinearLayout mllbg;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
