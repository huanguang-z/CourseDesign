package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.pony.config.Consts;
import com.pony.model.DortorModel;
import com.squareup.picasso.Picasso;

public class DoctorAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<DortorModel> list_result;
	private int posIndex;
	private Context mContext;

	public DoctorAdapter(Context context, List<DortorModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_message_notice, null);
			holder = new ViewHolder();
			holder.teacherName = (TextView) convertView.findViewById(R.id.teacherName);
			holder.teacherAge = (TextView) convertView.findViewById(R.id.teacherAge);

			holder.teacherMessage = (TextView) convertView.findViewById(R.id.teacherMessage);
			holder.mivUserImage = (ImageView) convertView.findViewById(R.id.mivUserImage);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.teacherName.setText(list_result.get(position).getDoctorName());
			holder.teacherAge.setText("性别：" + list_result.get(position).getDoctorSex());
			holder.teacherMessage.setText("        " + list_result.get(position).getDoctorMessage());
			
			
			if(list_result.get(position).getDoctorSex().equals("男")){
				holder.mivUserImage.setImageResource(R.drawable.doctor_6);
			}else{
				holder.mivUserImage.setImageResource(R.drawable.doctor_4);
			}

		} catch (Exception e) {
			// TODO: handle exception
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView teacherName;
		private TextView teacherAge;
		private TextView teacherMessage;
		private ImageView mivUserImage;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
