package com.pony.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.community.R;
import com.pony.config.Consts;
import com.pony.listener.DeleteListner;
import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private List<String> list_result;
	private int posIndex;
	private Context mContext;
	private DeleteListner mDeleteListner;

	public ImageAdapter(Context context, List<String> list_result, DeleteListner deleteListner) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
		this.mDeleteListner = deleteListner;
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
			convertView = inflater.inflate(R.layout.image_item, null);
			holder = new ViewHolder();
			holder.message_image = (ImageView) convertView.findViewById(R.id.message_image);
			holder.mivDelete = (ImageView) convertView.findViewById(R.id.mivDelete);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		try {
			Picasso.with(mContext).load(Consts.URL_IMAGE + list_result.get(position)).placeholder(R.drawable.default_drawable_show_pictrue)
					.into(holder.message_image);
			holder.mivDelete.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					mDeleteListner.setDelete(position, list_result.get(position));
				}
			});
		} catch (Exception e) {
		}

		return convertView;

	}

	private class ViewHolder {
		private ImageView message_image;
		private ImageView mivDelete;

	}

	public void setPos(int pos) {
		posIndex = pos;
	}

}
