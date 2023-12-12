package com.pony.adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pony.app.MyApplication;
import com.pony.model.Instructor;
import com.pony.util.ImageLoaderOptions;


/**
 * 处理发表话题图片的适配器
 */

public class InstructorAdapter extends BaseAdapter {

	private ArrayList<Instructor> list;
	private Context context;
	private LayoutInflater mInflater;
	DisplayImageOptions roudOptions;

	public InstructorAdapter(Context context, ArrayList<Instructor> map) {
		this.context = context;
		this.list = map;
		this.roudOptions = ImageLoaderOptions.getDefaultNoMemOption();
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		return list.size();
	}
	public ArrayList<Instructor> geList() {
		return list;
	}

	@Override
	public Instructor getItem(int location) {
		return list.get(location);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}


	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		CollectHolder holder;
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.adapter_instructor_list,
					null);
			holder = new CollectHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (CollectHolder) convertView.getTag();
		}
		fillData(holder, position);
		return convertView;
	}

	@SuppressLint("NewApi")
	private void fillData(final CollectHolder holder, final int position) {
		final Instructor boutique = (Instructor) getItem(position);
		if (boutique.getInstructor_id().equals("10000")) {
//			holder.yulequan_boutique_icon.setImageDrawable(context
//					.getResources().getDrawable(R.drawable.shape_see_more));

			holder.yulequan_boutique_icon.setImageResource(R.drawable.shape_see_more);
			holder.see_more.setText(context.getString(R.string.see_more));
			holder.see_more.setVisibility(View.VISIBLE);
		} else {
			int width = dip2px(MyApplication.getInstance(), 72);
//			ImageLoader.getInstance().displayImage(,
//					holder.yulequan_boutique_icon, roudOptions);
//			Uri uIconuri = Uri.parse(getSpeicalImageBywh(boutique.getLogo(),width,width));
////			holder.yulequan_boutique_icon.setImageURI(uIconuri);
//			initDraweeController(holder.yulequan_boutique_icon, uIconuri);

			holder.see_more.setVisibility(View.GONE);
		}
	}


	public static int dip2px(Context context, float dipValue) {
		final float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dipValue * scale + 0.5f);
	}
	class CollectHolder {
		ImageView yulequan_boutique_icon;
		TextView see_more;

		public CollectHolder(View root) {
			this.yulequan_boutique_icon = (ImageView) root
					.findViewById(R.id.yulequan_boutique_icon);
			this.see_more = (TextView) root.findViewById(R.id.see_more);

		}
	}

	/**
	 * 根据图片的大小宽高加载图片
	 * 
	 * @param str
	 * @param width
	 * @param heigth
	 * @param quality
	 * @return
	 */
	public static String getSpeicalImageBywh(String str, int width, int height) {
		String specialImage = str;
		try {
			// if (specialImage.contains("http://st.dailyyoga.com.cn/")
			// || specialImage.contains("http://st1.dailyyoga.com.cn/")) {
			// int index = specialImage.lastIndexOf(".");
			// String preStr = specialImage.substring(0, index);
			// String sufStr = specialImage.substring(index,
			// specialImage.length());// 后缀
			// specialImage = preStr + "_" + width + "-" + heigth + sufStr;
			// }
			if (specialImage.contains("http://st")) {
				int index = specialImage.lastIndexOf(".");
				String preStr = specialImage.substring(0, index);
				String sufStr = specialImage.substring(index,
						specialImage.length());// 后缀
				specialImage = preStr + "_" + width + "-" + height + sufStr;
			} else if (specialImage.contains("qiniucdn")) {
				// 原图：
				// http://7xo9qb.com2.z0.glb.qiniucdn.com/ee/03/ee03f13487ee8af8a1fe3274e0ffc4a5.jpeg
				// 等比缩放：
				// http://7xo9qb.com2.z0.glb.qiniucdn.com/ee/03/ee03f13487ee8af8a1fe3274e0ffc4a5.jpeg?imageView2/2/w/300/h/300
				// 居中裁剪：http://7xo9qb.com2.z0.glb.qiniucdn.com/ee/03/ee03f13487ee8af8a1fe3274e0ffc4a5.jpeg?imageView2/1/w/300/h/300
				String sufStr = "?imageView2/1/w/" + width + "/h/" + height;
				specialImage = specialImage + sufStr;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return specialImage;
	}
}
