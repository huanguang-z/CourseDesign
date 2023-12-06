package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.pony.listener.RemoveListner;
import com.pony.model.CategoryModel;

public class CategoryAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<CategoryModel> list_result ;
	private int posIndex;
	public CategoryAdapter(Context context, List<CategoryModel>  list_result) {
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
			convertView = inflater.inflate(R.layout.item_category, null);
			holder = new ViewHolder();
			holder.message_name = (TextView) convertView.findViewById(R.id.message_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
//		try {
			holder.message_name.setText(list_result.get(position).getcName());
//		} catch (Exception e) {
//		}
		
		
		return convertView;

	}

	private class ViewHolder {
		private TextView message_name;
	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
