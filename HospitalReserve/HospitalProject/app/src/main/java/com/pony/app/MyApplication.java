package com.pony.app;

import java.util.Map;

import android.app.Application;
import android.database.sqlite.SQLiteDatabase;
import cn.jpush.android.api.JPushInterface;

import com.amap.api.navi.AMapNavi;
import com.community.R;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.pony.db.DBHelper;
import com.pony.model.UserModel;

/**
 * 
 * @author 自定义Application
 * 
 */
public class MyApplication extends Application {

	// 用于存放倒计时时间
	public static Map<String, Long> map;
	// 当前用户的Sec账户信息实体
	public UserModel mUser = null;
	private static MyApplication INSTANCE = null;
	public static SQLiteDatabase db;
	public static boolean messageFlag = false;

	@Override
	public void onCreate() {
		super.onCreate();

		configImageLoader();
		AMapNavi.setApiKey(this, "10191beabc59f5a7beba0005492cb819");

		INSTANCE = this;
		DBHelper dbHelper = new DBHelper(getApplicationContext(), DBHelper.DB_NAME);
		// 在执行了getWritableDatabase的时候才会创建数据库
		db = dbHelper.getWritableDatabase();
		
		JPushInterface.setDebugMode(true);
		JPushInterface.init(this);
		
	}

	public static synchronized MyApplication getInstance() {
		return INSTANCE;
	}

	public void setUser(UserModel user) {
		this.mUser = user;
	}

	public UserModel getUser() {
		return this.mUser;
	}

	/**
	 * 配置ImageLoder
	 */
	private void configImageLoader() {
		// 初始化ImageLoader
		@SuppressWarnings("deprecation")
		DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.default_drawable_show_pictrue) // 设置图片下载期间显示的图片
				.showImageForEmptyUri(R.drawable.default_drawable_show_pictrue) // 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.default_drawable_show_pictrue) // 设置图片加载或解码过程中发生错误显示的图片
				.cacheInMemory(true) // 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
				// .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
				.build(); // 创建配置过得DisplayImageOption对象

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
				.threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}
}
