package com.pony.base;

import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.community.R;
import com.google.gson.Gson;
import com.pony.config.Consts;
import com.pony.model.ResponseEntry;
import com.pony.util.LoadingDialog;
import com.pony.util.NetManager;
import com.pony.util.SystemBarTintManager;
import com.pony.util.TipsToast;


public abstract class BaseActivity extends Activity implements OnClickListener {

	private Context mContext;
	public static TipsToast tipsToast;

	private static final int ACTIVITY_RESUME = 0;
	private static final int ACTIVITY_STOP = 1;
	private static final int ACTIVITY_PAUSE = 2;
	private static final int ACTIVITY_DESTROY = 3;

	public int activityState;

	/**
	 * findviewbyid
	 */
	public abstract void initWidget();

	/**
	 * findviewbyid
	 */
	public abstract void initData();
	private LinearLayout mllTop;
	public LoadingDialog mdialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initTiltBar();
	}

	public void showTips(String type, int time) {
		if (tipsToast != null) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				tipsToast.cancel();
			}
		} else {
			tipsToast = TipsToast.makeText(getApplication().getBaseContext(), type, TipsToast.LENGTH_SHORT);
		}
		tipsToast.show();
		tipsToast.setText(type);

	}

	@Override
	protected void onStart() {
		super.onStart();
	}

	@Override
	protected void onResume() {
		super.onResume();
		activityState = ACTIVITY_RESUME;
	}

	@Override
	protected void onStop() {
		super.onResume();
		activityState = ACTIVITY_STOP;
	}

	@Override
	protected void onPause() {
		super.onPause();
		activityState = ACTIVITY_PAUSE;
	}

	@Override
	protected void onRestart() {
		super.onRestart();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		activityState = ACTIVITY_DESTROY;
	}

	NetManager netManager = new NetManager(this);
	// ///////////////网络访问操作///////////////////////////////////
	/**
	 * FinalHttp
	 */
	public FinalHttp fh = new FinalHttp();
	public Gson mGson = new Gson();

	/**
	 * @Description: TODO 发送网络请求
	 * @param url
	 * @param params
	 * @param actionId
	 */
	public void httpPost(String url, AjaxParams params, final int actionId, boolean isShow, String lodingType) {
		if (null != params)
			Log.i("pony_log", "请求的参数信息是：" + params.getParamString());
		if (!new NetManager(getApplication().getBaseContext()).isOpenNetwork()) {
			callBackAllFailure("网络未连接", actionId);
			return;
		}
		if (isShow) {
			mdialog = new LoadingDialog(this, lodingType);
			if (!isFinishing()) {
				mdialog.show();
			}
		}
		fh.configTimeout(Consts.TIME_OUT);
		fh.post(url, params, new AjaxCallBack<String>() {
			@Override
			public void onSuccess(String response) {
				// TODO 网络访问成功
				super.onSuccess(response);
				if (null != mdialog && mdialog.isShowing())
					mdialog.dismiss();
				if (null == response || "".equals(response)) {
					callBackAllFailure("网络错误", actionId);
					return;
				}

				Log.i("pony_log", "返回的数据信息是：" + response);
				try {
					JSONObject jo = new JSONObject(response);
					ResponseEntry entry = new ResponseEntry();
					entry.setRepCode(jo.optString("repCode"));
					entry.setRepMsg(jo.optString("repMsg"));
					if (jo.optString("repCode").equals("666")) {
						entry.setData(jo.optString("data"));
					}
					callBackSuccessed(entry, actionId);
					

				} catch (JSONException e) {
					e.printStackTrace();
				}

			}

			public void onLoading(long count, long current) {
				// TODO 网络访问中
				super.onLoading(count, current);
				callBackLoading(count, current, actionId);
			}

			public void onFailure(Throwable t, int errorNo, String strMsg) {
				// TODO 网络访问失败
				super.onFailure(t, errorNo, strMsg);
				if (null != mdialog && mdialog.isShowing())
					mdialog.dismiss();
				callBackFailure(t, errorNo, strMsg, actionId);
			}
		});
	}

	protected void callBackSuccess(ResponseEntry entry, int actionId) {
		// 传递到子类
	}

	/**
	 * success
	 * 
	 * @param strMsg
	 * @param actionId
	 */
	protected void callBackSuccessed(ResponseEntry entry, int actionId) {
		Log.i("pony_log", "返回状态是：" + entry.getRepCode());
		if (entry.getRepCode().equals(ResponseEntry.NO)) {
			// 请求失败
			callBackAllFailure(entry.getRepMsg(), actionId);
			return;
		} else if (entry.getRepCode().equals("111")) {
			callBackAllFailure(entry.getRepMsg(), actionId);
			return;
		} else {
			callBackSuccess(entry, actionId);
			return;
		}
	}

	/**
	 * @Description: TODO 网络访问中
	 * @param count
	 * @param current
	 */
	protected void callBackLoading(long count, long current, int actionId) {
	}

	/**
	 * @Description: TODO 网络访问失败
	 * @param t
	 * @param errorNo
	 * @param strMsg
	 */
	protected void callBackFailure(Throwable t, int errorNo, String strMsg, int actionId) {
		// callBackAllFailure(strMsg, actionId);
		callBackAllFailure("网络访问失败", actionId);
	}

	/**
	 * @Description: TODO 无网络
	 * @param t
	 * @param errorNo
	 * @param strMsg
	 */
	protected void callBackAllFailure(String strMsg, int actionId) {
	}


	/**
	 * titlebar变颜色
	 */
	public void initTiltBar() {
		try {
			if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
				setTranslucentStatus(true);
			}
			SystemBarTintManager tintManager = new SystemBarTintManager(this);
			tintManager.setStatusBarTintEnabled(true);
			// 使用颜色资源
			tintManager.setStatusBarTintResource(R.color.main_color);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	// // 设置状态栏
	// private void setWindowStatus() {
	// if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
	// // 透明状态栏
	// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
	// // 透明导航栏
	// getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
	// // 设置状态栏颜色
	// getWindow().setBackgroundDrawableResource(R.color.main_color);
	// }
	// }
}
