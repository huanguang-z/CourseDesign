package com.pony.photo;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.community.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.pony.util.ImageLoaderOptions;
import com.pony.util.NetManager;

public class BasicTrackFragment extends Fragment {
	public Context context;

	public void onStart() {
		System.setProperty("java.net.preferIPv6Addresses", "false");
		context = getActivity().getApplicationContext();
		initImageLoader(context);
		super.onStart();
	}

	public boolean checkNet() {
		NetManager manager = new NetManager(context);
		if (!manager.isOpenNetwork()) {
			return false;
		}
		return true;
	}

	private static int mDeviceSate = -1;

	public boolean is600dp() {

		if (mDeviceSate == -1) {
			mDeviceSate = getResources().getBoolean(R.bool.isSw600) ? 1 : 0;
		}
		return mDeviceSate == 1;
	}

	public void onDestroy() {
		System.gc();
		super.onDestroy();
	}
	

	public DisplayImageOptions roudOptions;// 默认圆图
	public DisplayImageOptions options;// 默认方图
	public DisplayImageOptions programoptions;// 默认计划图
	public ImageLoader imageLoader = ImageLoader.getInstance();

	private void initImageLoader(Context context) {
		if (imageLoader == null) {
			imageLoader = ImageLoader.getInstance();
			imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		}
		if (roudOptions == null) {
			roudOptions = ImageLoaderOptions.getAvatorOption();
		}
		if (options == null) {
			options = ImageLoaderOptions.getDefaultNoMemOption();
		}
		if (programoptions == null) {
			programoptions = ImageLoaderOptions.getDefaultNoMemOption();
		}
	}
}
