package com.pony.view;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class RotatedImage extends ImageView{
	private final static String tag = "RotatedImage";
	private final static int FRAME_DURATION = 1000/60;
	private float degree = 0;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			rotate();
		};
	};
	public RotatedImage(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	public RotatedImage(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public RotatedImage(Context context) {
		super(context);
	}
	private boolean mAnimation = false;
	private void rotate(){
//		Logger.d(tag,"rotate  " + degree);
		if(!mAnimation)return;
		degree += 15;
		invalidate();
		mHandler.sendMessageDelayed(mHandler.obtainMessage(), FRAME_DURATION);
	}
	public void start(){
		if(!mAnimation){
			mAnimation = true;
			rotate();
		}
	}
	public void stop(){
		mAnimation = false;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		canvas.save();
		if(degree > 360){
			degree = 0;
		}
		canvas.rotate(degree, ((float)getMeasuredWidth())/2, ((float)getMeasuredHeight())/2);
		super.onDraw(canvas);
		canvas.restore();
	}

}
