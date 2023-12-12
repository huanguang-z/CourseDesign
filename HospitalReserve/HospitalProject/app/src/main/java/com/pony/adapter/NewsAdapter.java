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

public class NewsAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<NewsModel> list_result;
	private int posIndex;
	private Context mContext;

	public NewsAdapter(Context context, List<NewsModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_message_course, null);
			holder = new ViewHolder();
			holder.newsShowTitle = (TextView) convertView.findViewById(R.id.newsShowTitle);
			holder.newsShowTime = (TextView) convertView.findViewById(R.id.newsShowTime);
			holder.newsShowMessage = (TextView) convertView.findViewById(R.id.newsShowMessage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.newsShowTitle.setText(list_result.get(position).getNewsName());
			holder.newsShowTime.setText(list_result.get(position).getNewsType());
			holder.newsShowMessage.setText("发布时间："+list_result.get(position).getNewsTime());
			// if(!TextUtils.isEmpty(list_result.get(position).getmReMessage())){
			// holder.messageCourse.setText(list_result.get(position).getmReMessage());
			// }else{
			// }
		} catch (Exception e) {
			// TODO: handle exception
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView newsShowTitle;
		private TextView newsShowTime;
		private TextView newsShowMessage;
		
		
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
