package com.pony.photo.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.community.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.pony.base.BaseActivity;
import com.pony.photo.PhotoView;
import com.pony.util.ImageLoaderOptions;
import com.pony.photo.PhotoViewAttacher;
public class ShowPictureActivity extends BaseActivity implements PhotoViewAttacher.OnViewTapListener, View.OnLongClickListener {

	private Intent mIntent;
	private Bitmap mCacheBitmap;
	private String mUrl;
	public final static int FROM_AVATAR = 1;
	public final static int FROM_DEFAULT = 0;
	private RelativeLayout mLayout;
	private PhotoView showPicture;
	public DisplayImageOptions roudOptions;
	public DisplayImageOptions options;
	public ImageLoader imageLoader;
	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_picture);
		initWidget();
		initData();
		initImageLoader(this);
	}

	@Override
	public boolean onLongClick(View v) {
		if (mCacheBitmap != null) {
			// initDialog();
		}
		return false;
	}

	@Override
	public void onViewTap(View view, float x, float y) {
		finish();
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.mIvBack:
			finish();
			break;
		}

	}

	@Override
	public void initWidget() {

		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("照片信息");
		mIvBack.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);

	}

	@Override
	public void initData() {

		mIntent = getIntent();
		// 设置图片
		showPicture = (PhotoView) findViewById(R.id.show_picture);
		mUrl = mIntent.getStringExtra("piction_path");
		int from = mIntent.getIntExtra("from", FROM_DEFAULT);
		showPicture.setOnLongClickListener(this);
		showPicture.setOnViewTapListener(this);

		String url = mUrl;
		if (mUrl != null && mUrl.contains("http")) {
			url = mUrl;
		} else {
			url = "file://" + mUrl;
		}
		if (from == FROM_AVATAR) {
			ImageLoader.getInstance().displayImage(url, showPicture, ImageLoaderOptions.getAvatorOption(), new ImageLoadingListener() {
				@Override
				public void onLoadingStarted(String arg0, View arg1) {
				}

				@Override
				public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				}

				@Override
				public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
					mCacheBitmap = arg2;
					if (arg2 != null && arg2.getWidth() > 0 && arg2.getHeight() > 0) {
						showPicture.setImageBitmap(arg2);
					}
				}

				@Override
				public void onLoadingCancelled(String arg0, View arg1) {
				}
			});
		} else {
			ImageLoader.getInstance().displayImage(url, showPicture, options, new ImageLoadingListener() {
				@Override
				public void onLoadingStarted(String arg0, View arg1) {
				}

				@Override
				public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
				}

				@Override
				public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
					mCacheBitmap = arg2;
					if (arg2 != null && arg2.getWidth() > 0 && arg2.getHeight() > 0) {
						showPicture.setImageBitmap(arg2);
					}
				}

				@Override
				public void onLoadingCancelled(String arg0, View arg1) {
				}
			});
		}

	}

	private void initImageLoader(Context context) {
		if (imageLoader == null) {
			imageLoader = ImageLoader.getInstance();
			imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		}
		if (roudOptions == null) {
			roudOptions = ImageLoaderOptions.getDefaultAvatorOption(90);
		}
		if (options == null) {
			options = ImageLoaderOptions.getDefaultNoMemOption();
		}
	}

	// public void saveImage(Bitmap mBitmap) {
	// try {
	// ContentResolver cr = this.getContentResolver();
	// String url = MediaStore.Images.Media.insertImage(cr, mBitmap, "1.jpg",
	// "");
	// ToastUtil.ShowCentre(ShowPictureActivity.this, "保存成功");
	// ShowPictureActivity.this.sendBroadcast(new
	// Intent(Intent.ACTION_MEDIA_MOUNTED, Uri.parse("file://"
	// + Environment.getExternalStorageDirectory())));
	// } catch (Exception e) {
	//
	// e.printStackTrace();
	//
	// }
	// }

}
