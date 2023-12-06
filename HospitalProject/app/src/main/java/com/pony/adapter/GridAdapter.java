package com.pony.adapter;

import java.util.List;
import java.util.Map;

import android.content.Context;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.community.R;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.pony.model.ViewHolder;
import com.pony.util.ImageLoaderOptions;
import com.pony.util.ToastUtil;

public class GridAdapter extends BaseAdapter {

	private Context mContext;
	private List<Map<String, Object>> mData;
	// private SdcardImageLoader mSdcardImageLoader;
	Handler mHandler = new Handler();

	private int mCount = 0;
	private int mSelectedCount = 0;
	private int mMaxCount;

	public GridAdapter(Context context, List<Map<String, Object>> data, int count, int maxCount) {
		mContext = context;
		mData = data;
		mSelectedCount = count;
		mCount = count;
		mMaxCount = maxCount;
		// mSdcardImageLoader = new SdcardImageLoader(context, getImageWidth(),
		// getImageWidth());
	}

	@Override
	public int getCount() {
		return mData.size() + 1;
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position + 1;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = LayoutInflater.from(mContext).inflate(R.layout.gridview_item, null);

			View lin_camera = ViewHolder.get(convertView, R.id.lin_camera);
			View imageItem = ViewHolder.get(convertView, R.id.img_item);
			ViewGroup.LayoutParams layoutParams = lin_camera.getLayoutParams();

			layoutParams.width = getImageWidth();
			layoutParams.height = getImageWidth();
			lin_camera.setLayoutParams(layoutParams);
			imageItem.setLayoutParams(layoutParams);
		}

		View lin_camera = ViewHolder.get(convertView, R.id.lin_camera);

		final ImageView imageSelector = ViewHolder.get(convertView, R.id.img_selector);

		final ImageView imageItem = ViewHolder.get(convertView, R.id.img_item);

		// 初始化
		final Animation alphaAnimation = new AlphaAnimation(1f, 0.5f);
		// 设置动画时间

		alphaAnimation.setDuration(500);
		alphaAnimation.setFillAfter(true);

		final Animation alphaAnimation2 = new AlphaAnimation(0.5f, 1f);
		// 设置动画时间

		alphaAnimation2.setDuration(500);
		alphaAnimation2.setFillAfter(true);
		if (position == 0) {
			lin_camera.setVisibility(View.VISIBLE);
			imageSelector.setVisibility(View.GONE);
			imageItem.setVisibility(View.GONE);
		} else {
			lin_camera.setVisibility(View.GONE);
			imageSelector.setVisibility(View.VISIBLE);
			imageItem.setVisibility(View.VISIBLE);

			// mSdcardImageLoader.downLoad(mData.get(position -
			// 1).get("path").toString(), imageItem, R.drawable.tmp);
			ImageLoader.getInstance().displayImage("file://" + (String) mData.get(position - 1).get("path"), imageItem,
					ImageLoaderOptions.getCommonOption(R.drawable.tmp, R.drawable.tmp, R.drawable.tmp)

			);
			if ((Boolean) mData.get(position - 1).get("isSelected")) {
				imageSelector.setImageResource(R.drawable.checkbox_selecter_icon);
			} else {
				imageSelector.setImageResource(R.drawable.checkbox_default_icon);
			}
			// if ((Boolean) mData.get(position - 1).get("isSelected")) {
			// imageSelector.setImageResource(R.drawable.img_selector_icon);
			// } else {
			// imageSelector.setImageResource(R.drawable.img_no_selector_icon);
			// }

			// 选择标识
			imageSelector.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					if ((Boolean) mData.get(position - 1).get("isSelected")) {
						if (mSelectedCount <= 0) {
							return;
						}
						imageSelector.setImageResource(R.drawable.checkbox_default_icon);
						// imageSelector.setImageResource(R.drawable.img_no_selector_icon);
						imageItem.startAnimation(alphaAnimation2);

						mData.get(position - 1).put("isSelected", false);
						mSelectedCount--;
					} else {
						if (mSelectedCount >= mMaxCount) {
							ToastUtil.ShowCentre(
									mContext,
									mContext.getString(R.string.the_more_select) + (mMaxCount - mCount)
											+ mContext.getString(R.string.select_page_text));
							return;
						}
						imageItem.startAnimation(alphaAnimation);
						imageSelector.setImageResource(R.drawable.checkbox_selecter_icon);
						// imageSelector.setImageResource(R.drawable.img_selector_icon);
						mData.get(position - 1).put("isSelected", true);
						mSelectedCount++;
					}
				}
			});
		}

		return convertView;
	}

	private int getImageWidth() {
		DisplayMetrics metric = mContext.getResources().getDisplayMetrics();
		final float scale = mContext.getResources().getDisplayMetrics().density;
		int dev = (int) (8 * scale + 0.5f);
		return (metric.widthPixels - dev) / 3; // 屏幕宽度（像素）
	}

}
