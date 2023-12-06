package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.community.R;
import com.pony.model.SignModel;

public class SignTeacherListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<SignModel> list_result;
	private int posIndex;
	private Context mContext;

	public SignTeacherListAdapter(Context context, List<SignModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_signmessage, null);
			holder = new ViewHolder();
			holder.message_name = (TextView) convertView.findViewById(R.id.message_name);
			holder.messageCourse = (TextView) convertView.findViewById(R.id.messageCourse);
			holder.message_state = (TextView) convertView.findViewById(R.id.message_state);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.message_name.setText(list_result.get(position).getsStuName());
			holder.messageCourse.setText("签到科目：" + list_result.get(position).getsCourseName());
			holder.message_state.setText(list_result.get(position).getsTime());

			// if(!TextUtils.isEmpty(list_result.get(position).getmReMessage())){
			// holder.messageCourse.setText(list_result.get(position).getmReMessage());
			// }else{
			//
			// }
		} catch (Exception e) {
			// TODO: handle exception
		}

		// if(posIndex==position){
		// holder.title.setTextColor(Color.parseColor("#ff0000"));
		// }else{
		// holder.title.setTextColor(Color.parseColor("#333333"));
		// }
		return convertView;

	}

	private class ViewHolder {
		private TextView message_name;
		private TextView messageCourse;
		private TextView message_state;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
