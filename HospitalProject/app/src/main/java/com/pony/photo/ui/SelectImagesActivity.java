package com.pony.photo.ui;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.MediaStore.Images;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.community.R;
import com.pony.adapter.GridAdapter;
import com.pony.base.BaseActivity;

public class SelectImagesActivity extends BaseActivity {

	private GridView mImgsGridview;
	private GridAdapter mAdapter;
	private List<Map<String, Object>> mListData;
	private Intent mIntent;
	private Uri mOutPutFileUri;
	private File mCameraFile;
	private int mImageCount;

	// title
	private TextView mTvTitle;
	// 返回
	private ImageView mIvBack;
	// 介绍
	private TextView mIvStu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_select_images);
		initWidget();
		initData();
		initGridView();
	}



	private void initGridView() {
		mListData = getSD();
		mAdapter = new GridAdapter(this, mListData, mImageCount, 9);
		mImgsGridview.setAdapter(mAdapter);
	}

	// 去拍照
	private void goCameraActivity() {
		Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		// dailyyoga_camera文件夹
		File parentFile = new File(Environment.getExternalStorageDirectory().toString() + "/dailyyoga_camera");
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		mCameraFile = new File(parentFile + "/" + System.currentTimeMillis() + ".jpg");
		mOutPutFileUri = Uri.fromFile(mCameraFile);
		intent.putExtra(MediaStore.EXTRA_OUTPUT, mOutPutFileUri);
		startActivityForResult(intent, 2);
	}

	// 获取图片路径地址
	private List<Map<String, Object>> getSD() {

		// /* 设定目前所在路径 */
		List<Map<String, Object>> it = new ArrayList<Map<String, Object>>();

		ContentResolver cr = getContentResolver();
		String[] columns = { Images.Media._ID, Images.Media.DATA, Images.Media.BUCKET_ID, Images.Media.BUCKET_DISPLAY_NAME };
		String selection = "0==0) GROUP BY (" + Images.Media.BUCKET_ID;
		String sortOrder = Images.Media.DATE_MODIFIED + " desc";
		Cursor cur = cr.query(Images.Media.EXTERNAL_CONTENT_URI, columns, null, null, sortOrder);
		if (cur.moveToFirst()) {

			int image_id_column = cur.getColumnIndex(Images.Media.DATA);

			do {
				// Get the field values

				String image_path = cur.getString(image_id_column);

				Map<String, Object> itemMap = new HashMap<String, Object>();

				if (!isEmpty(image_path) && isImageFile(image_path)) {
					itemMap.put("path", image_path);
					itemMap.put("isSelected", false);
					it.add(itemMap);
				}
				Log.d("image_path", "image_path=" + image_path);

			} while (cur.moveToNext());
		}

		cur.close();

		return it;
	}

	// 判段文件是否图片文件
	private boolean isImageFile(String fName) {

		String end = fName.substring(fName.lastIndexOf(".") + 1, fName.length()).toLowerCase(Locale.US);
		/* 按扩展名的类型决定MimeType */
		return (!fName.contains("~tmp") && !fName.contains("tou")) && (end.equals("jpg") || end.equals("png") || end.equals("jpeg"));

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {

		// 结束页面
		case R.id.mIvBack:
			SelectImagesActivity.this.finish();
			break;
		case R.id.mIvStu:
			ArrayList<CharSequence> imgs = new ArrayList<CharSequence>();
			for (int i = 0; i < mListData.size(); i++) {
				if ((Boolean) mListData.get(i).get("isSelected")) {
					imgs.add(mListData.get(i).get("path").toString());
				}
			}
			mIntent.putExtra("action", 1); // 相册的照片
			mIntent.putCharSequenceArrayListExtra("images", imgs);
			setResult(1, mIntent);
			finish();
			break;

		}
	}

	@Override
	public void initWidget() {
		mIvStu = (TextView) findViewById(R.id.mIvStu);
		mIvBack = (ImageView) findViewById(R.id.mIvBack);
		mTvTitle = (TextView) findViewById(R.id.mTvTitle);
		mTvTitle.setText("选择照片");
		mIvStu.setText("确定");
		mIvBack.setVisibility(View.VISIBLE);
		mIvStu.setVisibility(View.VISIBLE);
		mIvBack.setOnClickListener(this);
		mIvStu.setOnClickListener(this);
		
		mIntent = getIntent();
		mImageCount = mIntent.getIntExtra("image_count", 0);
		mImgsGridview = (GridView) findViewById(R.id.selects_gridview);
	}

	@Override
	public void initData() {
		// item监听
		mImgsGridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				if (position == 0) {
					goCameraActivity();
				} else {
					Intent intentPicture = new Intent(SelectImagesActivity.this, ShowPictureActivity.class);
					intentPicture.putExtra("piction_path", mListData.get(position - 1).get("path").toString());
					intentPicture.putExtra("from", ShowPictureActivity.FROM_DEFAULT);
					Log.d("piction_path", "piction_path=" + mListData.get(position - 1).get("path").toString());
					startActivity(intentPicture);
				}
			}

		});
	}

	/**
	 * 判断字符串是否为空.
	 * 
	 * @param s
	 * @return
	 */
	public static boolean isEmpty(String s) {
		try {
			if (s == null || s.equals("null") || s.trim().length() == 0) {
				return true;
			}
			return false;
		} catch (Exception e) {
			return false;
		}
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {

			if (requestCode == 2) {
				Log.d("camera", "" + (data == null));
				if (mCameraFile == null || !mCameraFile.exists()) {
					return;
				}
				mIntent.putExtra("action", 0); // 拍摄的照片
				mIntent.putExtra("camera_path", mCameraFile.getAbsolutePath());

				Log.d("camera_path", mCameraFile.getAbsolutePath());

				setResult(1, mIntent);
				finish();
			}
		}
	}
}
