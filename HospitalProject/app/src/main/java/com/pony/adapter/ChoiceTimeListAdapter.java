package com.pony.adapter;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
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
public class ChoiceTimeListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private String[] list_result;
	private int posIndex=-1;
	private Context mContext;
	private String mtimeInfor;
	public ChoiceTimeListAdapter(Context context, String[] list_result,String timeInfor) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
		this.mtimeInfor = timeInfor;
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
			convertView = inflater.inflate(R.layout.item_time_index, null);
			holder = new ViewHolder();
			holder.departName = (TextView) convertView.findViewById(R.id.departName);
			holder.mllbg = (LinearLayout) convertView.findViewById(R.id.mllbg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		if(posIndex==position){
			holder.departName.setText(mtimeInfor+" "+list_result[position]+" (已选择)");
		}else{
			holder.departName.setText(mtimeInfor+" "+list_result[position]);
		}


		return convertView;

	}

	private class ViewHolder {
		private TextView departName;
		private LinearLayout mllbg;
	}

	public void setPos(int pos) {
		posIndex = pos;
		Log.i("pony_log","posIndex:"+posIndex);
	}

}
