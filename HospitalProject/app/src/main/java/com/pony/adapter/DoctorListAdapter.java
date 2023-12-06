package com.pony.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.pony.model.DortorModel;
import com.pony.view.RoundedCornerImageView;

import java.util.List;

public class DoctorListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<DortorModel> list_result;
	private int posIndex;
	private Context mContext;

	public DoctorListAdapter(Context context, List<DortorModel> list_result) {
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
			convertView = inflater.inflate(R.layout.item_message_doctor, null);
			holder = new ViewHolder();
			holder.message_name = (TextView) convertView.findViewById(R.id.message_name);
			holder.messageage = (TextView) convertView.findViewById(R.id.messageage);

			holder.message_image = (RoundedCornerImageView) convertView.findViewById(R.id.message_image);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			holder.message_name.setText(list_result.get(position).getDoctorName());
			holder.messageage.setText("类型："+list_result.get(position).getDoctorLevel());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return convertView;

	}

	private class ViewHolder {
		private TextView message_name;
		private TextView messageage;
		private RoundedCornerImageView message_image;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
