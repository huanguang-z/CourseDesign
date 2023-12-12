package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.pony.config.Consts;
import com.pony.listener.IntersetListner;
import com.pony.model.ApplyModel;
import com.pony.view.RoundedCornerImageView;
import com.squareup.picasso.Picasso;

public class OrderDoctorAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ApplyModel> list_result;
	private int posIndex;
	private Context mContext;
	IntersetListner mIntersetListner;
	public OrderDoctorAdapter(Context context, List<ApplyModel> list_result,IntersetListner intersetListner) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
		this.mIntersetListner = intersetListner;
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
			convertView = inflater.inflate(R.layout.item_message_apply, null);
			holder = new ViewHolder();
			holder.teacherName = (TextView) convertView.findViewById(R.id.teacherName);
			holder.teacherAge = (TextView) convertView.findViewById(R.id.teacherAge);
			holder.teacherInfor = (TextView) convertView.findViewById(R.id.teacherInfor);
			holder.message_image = (RoundedCornerImageView) convertView.findViewById(R.id.message_image);
			holder.mivAdd = (ImageView) convertView.findViewById(R.id.mivAdd);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
			holder.teacherName.setText(list_result.get(position).getApplyMessageName());
			holder.teacherInfor.setText("就诊人员：" + list_result.get(position).getApplyUserName());
			holder.teacherAge.setText("预约时间：" + list_result.get(position).getApplyChoiceTime());


			holder.mivAdd.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mIntersetListner.setDelete(position, list_result.get(position));
				}
			});



		return convertView;

	}

	private class ViewHolder {
		private ImageView mivAdd;
		private TextView teacherInfor;
		private TextView teacherName;
		private TextView teacherAge;
		private TextView teacherMessage;
		private RoundedCornerImageView message_image;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
