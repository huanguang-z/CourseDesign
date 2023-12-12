package com.pony.fragment;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

import com.community.R;
import com.pony.adapter.FragmentAdapter;

public class MainFragment extends Fragment{
	private int screenWidth,screenHeight;
	private List<Fragment> list = new ArrayList<Fragment>();  
	private ViewPager vPager;
	private FragmentAdapter adapter;
	private TextView tvSelected,tvSubscribe;
	private View viewIndicator;
	private int currentIndex=0;
	
    @Override
    public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState) {
    		getScreenSize(getActivity());
    		
	    	View rootView = inflater.inflate(R.layout.fragment_main_choice, null);
	    	/**
		* 初始化三个Fragment  并且填充到ViewPager
		*/
		vPager = (ViewPager) rootView.findViewById(R.id.viewpager_home);
		IndexFragment imFriendFragment=new IndexFragment();
		NewsFragment imMessageFragment=new NewsFragment();
		list.add(imFriendFragment);
		list.add(imMessageFragment);
	
		adapter = new FragmentAdapter(getChildFragmentManager(),list);
		vPager.setAdapter(adapter);
		vPager.setOffscreenPageLimit(1);
		vPager.setCurrentItem(0);
		vPager.setOnPageChangeListener(pageChangeListener);
		
		tvSelected=(TextView) rootView.findViewById(R.id.tv_selected);
		tvSubscribe=(TextView) rootView.findViewById(R.id.tv_subscribe);
		
		tvSelected.setOnClickListener(clickListener);
		tvSubscribe.setOnClickListener(clickListener);
		
		tvSelected.setSelected(true);
		
		viewIndicator=rootView.findViewById(R.id.view_indicator);
		
		initCursorPosition();
		
	    	return rootView;
    }
    private int currentSelectId;
    private OnClickListener clickListener=new OnClickListener() {
		@Override
		public void onClick(View v){
			if(currentSelectId!=v.getId()){//防止重复点击
				switch (v.getId()) {
				case R.id.tv_selected:
					vPager.setCurrentItem(0);
					break;
				case R.id.tv_subscribe:
					vPager.setCurrentItem(1);
					break;
				}
				
				currentSelectId=v.getId();
			}

		}
	};
    
    private void initCursorPosition(){
		LayoutParams layoutParams=viewIndicator.getLayoutParams();
		layoutParams.width=screenWidth/2;
		viewIndicator.setLayoutParams(layoutParams);
		
		TranslateAnimation animation = new TranslateAnimation(-screenWidth/2,0,0,0);
		animation.setFillAfter(true);
		viewIndicator.startAnimation(animation);
    }
    
    private OnPageChangeListener pageChangeListener=new OnPageChangeListener() {
		@Override
		public void onPageSelected(int index){
			translateAnimation(index);//移动指示器
			changeTextColor(index);//改变文字颜色
			currentIndex=index;//设置当前选中
		}
		
		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {}
		
		@Override
		public void onPageScrollStateChanged(int arg0) {}
	};
	
	/**
	 * 改变标题栏字体颜色
	 * @param index
	 */
	private void changeTextColor(int index){
		tvSelected.setSelected(false);
		tvSubscribe.setSelected(false);
		
		switch (index) {
		case 0:
			tvSelected.setSelected(true);
			break;
		case 1:
			tvSubscribe.setSelected(true);
			break;
		}
	}
	
	/**
	 * 移动标题栏点点点...
	 * @param index
	 */
	private void translateAnimation(int index){
		TranslateAnimation animation = null;
		switch (index){
		case 0://订阅->精选
			animation=new TranslateAnimation((screenWidth/2),0,0,0);
			break;
		case 1://
			if(0==currentIndex){//精选->订阅
				animation=new TranslateAnimation(0,screenWidth/2,0,0);
			}
			break;
		}
		animation.setFillAfter(true);
		animation.setDuration(300);
		viewIndicator.startAnimation(animation);
	}
	
	// 获取屏幕宽高
	private void getScreenSize(Activity context) {
		DisplayMetrics dm = new DisplayMetrics();
		context.getWindowManager().getDefaultDisplay().getMetrics(dm);
		screenWidth = dm.widthPixels;
		screenHeight = dm.heightPixels;
	}
}
