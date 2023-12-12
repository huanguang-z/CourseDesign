package com.pony.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.net.Uri;

/**
 * 图片工具类
 * 
 */
public class ImageUtil {

	/***
	 * 图片的缩放方法
	 * 
	 * @param bgimage
	 *            ：源图片资源
	 * @param newWidth
	 *            ：缩放后宽度
	 * @param newHeight
	 *            ：缩放后高度
	 * @return
	 */
	public static Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
		// 获取这个图片的宽和高
		float width = bgimage.getWidth();
		float height = bgimage.getHeight();
		// 创建操作图片用的matrix对象
		Matrix matrix = new Matrix();
		// 计算宽高缩放率
		float scaleWidth = ((float) newWidth) / width;
		float scaleHeight = ((float) newHeight) / height;
		// 缩放图片动作
		matrix.postScale(scaleWidth, scaleHeight);
		Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width, (int) height, matrix, true);
		return bitmap;
	}

	/**
	 * dip转pix
	 * 
	 * @param dp
	 * @return
	 */
	public static int dp2px(Context context, float dp) {
		float scale = context.getResources().getDisplayMetrics().density;
		return (int) (dp * scale + 0.5f);
	}

	public static Bitmap createWaterMaskRightTop(Context context, Bitmap src, Bitmap watermark, int paddingRight, int paddingTop) {
		return createWaterMaskBitmap(src, watermark, src.getWidth() - watermark.getWidth() - dp2px(context, paddingRight), dp2px(context, paddingTop));
	}

	private static Bitmap createWaterMaskBitmap(Bitmap src, Bitmap watermark, int paddingLeft, int paddingTop) {
		if (src == null) {
			return null;
		}
		int width = src.getWidth();
		int height = src.getHeight();
		// 创建一个bitmap
		Bitmap newb = Bitmap.createBitmap(width, height, Config.ARGB_8888);// 创建一个新的和SRC长度宽度一样的位图
		// 将该图片作为画布
		Canvas canvas = new Canvas(newb);
		// 在画布 0，0坐标上开始绘制原始图片
		canvas.drawBitmap(src, 0, 0, null);
		// 在画布上绘制水印图片
		canvas.drawBitmap(watermark, paddingLeft, paddingTop, null);
		// 保存
		canvas.save(Canvas.ALL_SAVE_FLAG);
		// 存储
		canvas.restore();
		return newb;
	}

	public static File shoot(Activity a, String name, Bitmap bitmap) {
		String sdcardPathDir = android.os.Environment.getExternalStorageDirectory().getPath() + "/PonyImage/";
		File file = new File(sdcardPathDir);
		if (!file.exists()) {
			file.mkdir();
		}
		try {
			savePic(a, bitmap, sdcardPathDir + name);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return file;
	}

	// 保存到sdcard
	private static void savePic(Activity a, Bitmap b, String strFileName) throws IOException {
		FileOutputStream fos = null;
		fos = new FileOutputStream(strFileName);
		if (null != fos) {
			b.compress(Bitmap.CompressFormat.PNG, 90, fos);
			fos.flush();
			fos.close();
			// 更新图库
			File file = new File(strFileName);
			updateImgPath(a, file, strFileName);
		}
	}

	public static void updateImgPath(Activity a, File file, String name) {
		Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
		Uri uri = Uri.fromFile(file);
		intent.setData(uri);
		a.sendBroadcast(intent);
	}


	public static Bitmap loadResBitmap(String path, int scalSize) {
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = false;
		options.inSampleSize = scalSize;
		Bitmap bmp = BitmapFactory.decodeFile(path, options);
		return bmp;
	}
}