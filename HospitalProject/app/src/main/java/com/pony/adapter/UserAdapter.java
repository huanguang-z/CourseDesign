package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;

import com.community.R;
import com.pony.listener.CheckListner;
import com.pony.model.ReviewModel;

public class UserAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<ReviewModel> list_result;
	private int posIndex = -1;
	private Context mContext;
	private CheckListner mCheckListner;
	public UserAdapter(Context context, List<ReviewModel> list_result) {
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
			convertView = inflater.inflate(R.layout.reviewuser_item, null);
			holder = new ViewHolder();
			holder.mrbMsg = (CheckBox) convertView.findViewById(R.id.mrbMsg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		// try {
		holder.mrbMsg.setText(list_result.get(position).getrUserName());

		if (posIndex == position) {
			holder.mrbMsg.setChecked(true);
		} else {
			holder.mrbMsg.setChecked(false);
		}
		// } catch (Exception e) {
		// // TODO: handle exception
		// }

		return convertView;

	}

	private class ViewHolder {
		private CheckBox mrbMsg;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
