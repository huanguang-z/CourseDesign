package com.pony.photo;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.community.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.squareup.picasso.Picasso;

public class PictureSlideFragment extends BasicTrackFragment implements PhotoViewAttacher.OnViewTapListener, View.OnLongClickListener {

	private View mContentView;
	private String mUrl;

	private PhotoView bannerImage;
	private Bitmap mCacheBitmap;
	private int index;
	private int size;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.fragment_picture_slide_title, container, false);
		mUrl = getArguments().getString("url");
		index = getArguments().getInt("index");
		size = getArguments().getInt("size");
		return mContentView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (this.getActivity() != null) {
			init();
		}
	}

	TextView titleView;

	private void init() {
		bannerImage = (PhotoView) mContentView.findViewById(R.id.page_image);
		LinearLayout topic_detail_title = (LinearLayout) mContentView.findViewById(R.id.show_title_msg);

		titleView = (TextView) topic_detail_title.findViewById(R.id.titleName);
		titleView.setText((index + 1) + "/" + size);
		
	
		

		if (mUrl != null) {
			bannerImage.setOnViewTapListener(this);
			bannerImage.setOnLongClickListener(this);
			
			bannerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
			Picasso.with(getActivity()).load(mUrl).placeholder(R.drawable.default_drawable_show_pictrue)
			.error(R.drawable.default_drawable_show_pictrue).into(bannerImage);

		}
	}

	/**
	 * 单击事件
	 */
	@Override
	public void onViewTap(View view, float x, float y) {
//		getActivity().finish();
	}

	/**
	 * 长按事件
	 */
	@Override
	public boolean onLongClick(View v) {
		if (this.getActivity() != null && mCacheBitmap != null) {
		}
		return false;
	}


}
