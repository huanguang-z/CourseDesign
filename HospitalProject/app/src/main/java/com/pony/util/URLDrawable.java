package com.pony.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

public class URLDrawable extends BitmapDrawable {

	protected Drawable drawable;
	public Bitmap bitmap;

	@Override
	public void draw(Canvas canvas) {

		if (drawable != null) {
			drawable.draw(canvas);
		}

		if (bitmap != null) {
			canvas.drawBitmap(bitmap, 0, 0, getPaint());
		}
	}
}
