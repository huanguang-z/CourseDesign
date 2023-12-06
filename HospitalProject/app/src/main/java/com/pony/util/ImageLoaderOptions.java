package com.pony.util;

import java.util.HashMap;

import com.community.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

public class ImageLoaderOptions {

	private static DisplayImageOptions mAvatorOption;

	public static DisplayImageOptions getAvatorOption() {
		if (mAvatorOption == null) {
			mAvatorOption = new DisplayImageOptions.Builder()
					.showImageOnFail(R.drawable.user_defult_icon)
					.showImageOnLoading(R.drawable.user_defult_icon)
					.showImageForEmptyUri(R.drawable.user_defult_icon)
					.displayer(new RoundedBitmapDisplayer(120))
					.cacheInMemory(true).cacheOnDisk(true).build();

		}
		return mAvatorOption;
	}

	

	private static DisplayImageOptions mMessageAvatarOption;

	public static DisplayImageOptions getMessageAvatorOption() {
		if (mMessageAvatarOption == null) {
			mMessageAvatarOption = new DisplayImageOptions.Builder()
					.showImageOnFail(R.drawable.user_defult_icon)
					.showImageOnLoading(R.drawable.user_defult_icon)
					.showImageForEmptyUri(R.drawable.user_defult_icon)
					.cacheInMemory(true).cacheOnDisk(true).build();

		}
		return mMessageAvatarOption;
	}

	
	private static HashMap<String, DisplayImageOptions> OptionCache = new HashMap<String, DisplayImageOptions>();

	public static DisplayImageOptions getCommonOption(int failid, int loadid,
			int emptyid) {

		DisplayImageOptions commonOption = OptionCache.get(getKey(failid,
				loadid, emptyid));
		if (commonOption == null) {

			commonOption = new DisplayImageOptions.Builder()
					.showImageOnFail(failid).showImageOnLoading(loadid)
					.cacheOnDisk(true).showImageForEmptyUri(emptyid)
					.cacheInMemory(true).build();
			OptionCache.put(getKey(failid, loadid, emptyid), commonOption);
		}
		return commonOption;
	}

	private static HashMap<String, DisplayImageOptions> NoMemOptionCache = new HashMap<String, DisplayImageOptions>();

	public static DisplayImageOptions getCommonNomemOption(int failid,
			int loadid, int emptyid) {

		DisplayImageOptions commonOption = NoMemOptionCache.get(getKey(failid,
				loadid, emptyid));
		if (commonOption == null) {

			commonOption = new DisplayImageOptions.Builder()
					.showImageOnFail(failid).showImageOnLoading(loadid)
					.cacheOnDisk(true).showImageForEmptyUri(emptyid)
					.cacheInMemory(false).build();
			NoMemOptionCache.put(getKey(failid, loadid, emptyid), commonOption);
		}
		return commonOption;
	}

	private static String getKey(int failid, int loadid, int emptyid) {
		return new StringBuffer().append(failid).append(loadid).append(emptyid)
				.toString();
	}

	public static DisplayImageOptions getDiskCacheOption(int failid,
			int loadid, int emptyid) {
		DisplayImageOptions diskCacheOption = OptionCache.get(getKey(failid,
				loadid, emptyid));
		if (diskCacheOption == null) {
			diskCacheOption = new DisplayImageOptions.Builder()
					.showImageOnFail(failid).showImageOnLoading(loadid)
					.showImageForEmptyUri(emptyid).cacheOnDisk(true).build();
			OptionCache.put(getKey(failid, loadid, emptyid), diskCacheOption);
		}
		return diskCacheOption;
	}

	private static DisplayImageOptions defaultOption;

	public static DisplayImageOptions getDefaultOption() {
		if (defaultOption == null) {
			defaultOption = new DisplayImageOptions.Builder()
					.showImageOnFail(R.drawable.default_drawable)
					.showImageOnLoading(R.drawable.default_drawable)
					.cacheInMemory(true)
					.showImageForEmptyUri(R.drawable.default_drawable)
					.cacheOnDisk(true).build();

		}
		return defaultOption;
	}
	
	private static DisplayImageOptions defaultOptionwithoutMem;

	public static DisplayImageOptions getDefaultNoMemOption() {
		if (defaultOptionwithoutMem == null) {
			defaultOptionwithoutMem = new DisplayImageOptions.Builder()
					.showImageOnFail(R.drawable.default_drawable)
					.showImageOnLoading(R.drawable.default_drawable)
					.cacheInMemory(false)
					.showImageForEmptyUri(R.drawable.default_drawable)
					.cacheOnDisk(true).build();

		}
		return defaultOption;
	}


	public static DisplayImageOptions getDefaultAvatorOption(int rounded) {
		DisplayImageOptions defaultAvatorOption = NoMemOptionCache.get(R.drawable.user_defult_icon+""+R.drawable.user_defult_icon+""+R.drawable.user_defult_icon+""+rounded);
		if (defaultAvatorOption == null) {
			defaultAvatorOption = new DisplayImageOptions.Builder()
					.showImageOnFail(R.drawable.user_defult_icon)
					.showImageOnLoading(R.drawable.user_defult_icon)
					.showImageForEmptyUri(R.drawable.user_defult_icon)
					.displayer(new RoundedBitmapDisplayer(rounded))
					.cacheInMemory(true).cacheOnDisk(true).build();
			NoMemOptionCache.put(R.drawable.user_defult_icon+""+R.drawable.user_defult_icon+""+R.drawable.user_defult_icon+""+rounded,defaultAvatorOption);
		}
		return defaultAvatorOption;
	}

}
