package com.pony.photo;

import java.io.IOException;
import java.util.Date;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.pony.util.ImageUtil;
import com.pony.view.DialogMsg;
import com.squareup.picasso.Picasso;

public class PictureSlideDeleteFragment extends BasicTrackFragment implements View.OnClickListener, View.OnLongClickListener {

	private View mContentView;
	private String mUrl;
	private PhotoView bannerImage;
	private int index;
	private int size;
	private DialogMsg dialogMsg;
	private Bitmap bitmap;
	private String datetime;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		mContentView = inflater.inflate(R.layout.fragment_picture_delete_slide_title, container, false);
		mUrl = getArguments().getString("url");
		index = getArguments().getInt("index");
		size = getArguments().getInt("size");
		getPhotoLocation(mUrl);
		return mContentView;
	}

	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (this.getActivity() != null) {
			init();
		}
	}

	ImageView goBackView;
	TextView titleView;
	TextView delete;

	public long getBitmapsize(Bitmap bitmapMsg) {
		// Pre HC-MR1
		return bitmapMsg.getRowBytes() * bitmapMsg.getHeight();

	}

	private void init() {
		dialogMsg = new DialogMsg(getActivity());
		dialogMsg.Set_Msg("保存此图片到本地相册吗？");
		bannerImage = (PhotoView) mContentView.findViewById(R.id.page_image);
		if (mUrl != null) {

//			bitmap = BitmapFactory.decodeFile(mUrl);

			// double width;
			// double height;

			// if (bitmap.getWidth() < 1000) {
			// bannerImage.setImageBitmap(bitmap);
			// } else {
//			bannerImage.setImageBitmap(bitmap);
			// }
			Picasso.with(getActivity()).load(mUrl).placeholder(R.drawable.default_drawable_show_pictrue)
			.into(bannerImage);
			//
			bannerImage.setScaleType(ImageView.ScaleType.FIT_CENTER);
		}

		// 此方法为每隔固定时间会发起一次定位请求，为了减少电量消耗或网络流量消耗，
		// 注意设置合适的定位时间的间隔（最小间隔支持为2000ms），并且在合适时间调用stopLocation()方法来取消定位请求
		// 在定位结束后，在合适的生命周期调用onDestroy()方法
		// 在单次定位情况下，定位无论成功与否，都无需调用stopLocation()方法移除请求，定位sdk内部会移除
		// 启动定位
		// 初始化定位

		dialogMsg = new DialogMsg(getActivity());
		dialogMsg.Set_Msg("保存此图片到本地相册吗？");
		bannerImage = (PhotoView) mContentView.findViewById(R.id.page_image);

		bannerImage.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				// TODO Auto-generated method stub
				dialogMsg.Show();
				return false;
			}
		});

		dialogMsg.submit_no().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				dialogMsg.Close();
			}
		});

		dialogMsg.submit_ok().setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				ImageUtil.shoot(getActivity(), new Date() + ".jpg", bitmap);
				dialogMsg.Close();
			}
		});
	}

	public static Bitmap loadResBitmap(String path, int scalSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = scalSize;
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		return bmp;
	}

	public String getPhotoLocation(String imagePath) {
		Log.i("TAG", "getPhotoLocation==" + imagePath);
		float output1 = 0;
		float output2 = 0;

		try {
			ExifInterface exifInterface = new ExifInterface(imagePath);
			datetime = exifInterface.getAttribute(ExifInterface.TAG_DATETIME);// 拍摄时间
			String deviceName = exifInterface.getAttribute(ExifInterface.TAG_MAKE);// 设备品牌
			String deviceModel = exifInterface.getAttribute(ExifInterface.TAG_MODEL); // 设备型号
			String latValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE);
			String lngValue = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE);
			String latRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LATITUDE_REF);
			String lngRef = exifInterface.getAttribute(ExifInterface.TAG_GPS_LONGITUDE_REF);
			if (latValue != null && latRef != null && lngValue != null && lngRef != null) {
				output1 = convertRationalLatLonToFloat(latValue, latRef);
				output2 = convertRationalLatLonToFloat(lngValue, lngRef);
			}

			Log.i("pony_log", "手机型号：" + deviceName + "," + deviceModel);
			Log.i("pony_log", "拍摄时间：" + datetime);
			Log.i("pony_log", "拍摄位置：" + output1 + ";" + output2);

			// setDiffColor(phoneTV, "手机型号：" + deviceName + "," + deviceModel);
			// setDiffColor(latlngTV, "经纬度：" + output1 + ";" + output2);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return output1 + "-" + output2;
	}

	private static float convertRationalLatLonToFloat(String rationalString, String ref) {

		String[] parts = rationalString.split(",");

		String[] pair;
		pair = parts[0].split("/");
		double degrees = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());

		pair = parts[1].split("/");
		double minutes = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());

		pair = parts[2].split("/");
		double seconds = Double.parseDouble(pair[0].trim()) / Double.parseDouble(pair[1].trim());

		double result = degrees + (minutes / 60.0) + (seconds / 3600.0);
		if ((ref.equals("S") || ref.equals("W"))) {
			return (float) -result;
		}
		return (float) result;
	}

	private DisplayImageOptions mDetailOption;

	public DisplayImageOptions getOption() {
		if (mDetailOption == null) {
			mDetailOption = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_drawable_show_pictrue)
			// 设置图片在下载期间显示的图片
					.showImageOnLoading(R.drawable.default_drawable_show_pictrue).showImageForEmptyUri(R.drawable.default_drawable_show_pictrue)// 设置图片Uri为空或是错误的时候显示的图片
					.showImageOnFail(R.drawable.default_drawable_show_pictrue)// 设置图片加载/解码过程中错误时候显示的图片
					// .cacheInMemory(true)// 是否緩存都內存中
					.cacheOnDisc(true)// 是否緩存到sd卡上
					.build();
		}
		return mDetailOption;
	}

	@Override
	public void onClick(View v) {
	}

	@Override
	public boolean onLongClick(View v) {
		if (bannerImage != null) {
			// initDialog();
			dialogMsg.Show();
		}
		return false;
	}

}
