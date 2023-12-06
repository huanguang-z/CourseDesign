package com.pony.adapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.community.R;
import com.pony.config.Consts;
import com.pony.model.ViewHolder;
import com.squareup.picasso.Picasso;

public class NoticeImageAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	private String[] list_result;
	private Context mContext;

	public NoticeImageAdapter(Context context,String[] list_result) {
		mContext = context;
		inflater = LayoutInflater.from(context);
		this.list_result = list_result;
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
		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.weiboimage_item, null);
			View lin_camera = ViewHolder.get(convertView, R.id.lin_camera);
			View imageItem = ViewHolder.get(convertView, R.id.img_item);
			ViewGroup.LayoutParams layoutParams = lin_camera.getLayoutParams();

			layoutParams.width = getImageWidth();
			layoutParams.height = getImageWidth();
			imageItem.setLayoutParams(layoutParams);
		}

		View lin_camera = ViewHolder.get(convertView, R.id.lin_camera);
		final ImageView imageSelector = ViewHolder.get(convertView, R.id.img_selector);
		final ImageView imageItem = ViewHolder.get(convertView, R.id.img_item);

		 Picasso.with(mContext).load(Consts.URL_IMAGE+list_result[position]).placeholder(R.drawable.default_drawable_show_pictrue)
		  .into(imageItem);
		return convertView;
	}

	private int getImageWidth() {
		DisplayMetrics metric = mContext.getResources().getDisplayMetrics();
		final float scale = mContext.getResources().getDisplayMetrics().density;
		int dev = (int) (8 * scale + 0.5f);
		return (metric.widthPixels - dev) / 4; // 屏幕宽度（像素）
	}

}
