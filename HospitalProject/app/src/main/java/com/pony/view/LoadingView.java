package com.pony.view;


import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.community.R;
public class LoadingView extends ImageView {
	
	private Animation mRotateAnimation;

	public LoadingView(Context context) {
		this(context, null);
	}

	public LoadingView(Context context, AttributeSet attrs) {
		super(context, attrs);
		mRotateAnimation = AnimationUtils.loadAnimation(context, R.anim.rotate_repeat);
		setImageResource(R.drawable.load_state_0);
	}

	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		if(mRotateAnimation != null){
			this.clearAnimation();
			mRotateAnimation.cancel();
		}
	}

	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		if(mRotateAnimation != null){
			mRotateAnimation.reset();
			startAnimation(mRotateAnimation);
		}
	}
	
	@Override
	public void setVisibility(int visibility) {
		super.setVisibility(visibility);
		if(View.VISIBLE == visibility){

			if(mRotateAnimation != null){
				mRotateAnimation.reset();
				startAnimation(mRotateAnimation);
			}
		}else{

			if(mRotateAnimation != null){
				this.clearAnimation();
				mRotateAnimation.cancel();
			}
		}
	}
}