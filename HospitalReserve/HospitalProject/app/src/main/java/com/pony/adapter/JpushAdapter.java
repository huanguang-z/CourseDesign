package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.R;
import com.pony.model.NewsModel;

public class JpushAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<NewsModel> list_result ;
	private int posIndex;
	public JpushAdapter(Context context, List<NewsModel>  list_result) {
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
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.item_message_jpush, null);
			holder = new ViewHolder();
			holder.teacherName = (TextView) convertView.findViewById(R.id.teacherName);
			holder.teacherMessage = (TextView) convertView.findViewById(R.id.teacherMessage);
			holder.metTime = (TextView) convertView.findViewById(R.id.metTime);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		try {
			holder.teacherMessage.setText("        "+list_result.get(position).getNewsMessage());
			holder.teacherName.setText(list_result.get(position).getNewsName());
			holder.metTime.setText("添加时间："+list_result.get(position).getNewsTime());
		} catch (Exception e) {
		}
		
		
		return convertView;

	}

	private class ViewHolder {
		private TextView teacherName;
		private TextView teacherMessage;
		private TextView metTime;
		
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
