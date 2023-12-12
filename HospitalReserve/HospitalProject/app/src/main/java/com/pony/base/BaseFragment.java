package com.pony.base;

import net.tsz.afinal.FinalBitmap;
import net.tsz.afinal.FinalHttp;
import net.tsz.afinal.http.AjaxCallBack;
import net.tsz.afinal.http.AjaxParams;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import com.community.R;
import com.google.gson.Gson;
import com.pony.config.Consts;
import com.pony.model.ResponseEntry;
import com.pony.util.LoadingDialog;
import com.pony.util.NetManager;
import com.pony.util.TipsToast;

public abstract class BaseFragment extends Fragment implements OnClickListener {

	private Context mContext;
	public static TipsToast tipsToast;
	public LoadingDialog mdialog;

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

	private static FinalBitmap fb;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
//		setWindowStatus();
		fb = FinalBitmap.create(getActivity());
		fb.configBitmapLoadThreadSize(3);// 定义线程数量
		fb.configDiskCachePath(getActivity().getApplicationContext().getFilesDir()
				.toString());// 设置缓存目录；
		fb.configDiskCacheSize(1024 * 1024 * 50);// 设置缓存大小
		fb.configLoadingImage(R.drawable.icon_local_music);
		fb.configLoadfailImage(R.drawable.icon_local_music);
	}


	public void showTips(String type, int time) {
		if (tipsToast != null) {
			if (Build.VERSION.SDK_INT < Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
				tipsToast.cancel();
			}
		} else {
			tipsToast = TipsToast.makeText(getActivity().getApplication().getBaseContext(),
					type, TipsToast.LENGTH_SHORT);
		}
		tipsToast.show();
		tipsToast.setText(type);

	}



	NetManager netManager = new NetManager(getActivity());
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
	public void httpPost(String url, AjaxParams params, final int actionId,
			boolean isShow, String lodingType) {
		if (null != params)
			Log.i("pony_log", "请求的参数信息是：" + params.getParamString());
		if (!new NetManager(getActivity()).isOpenNetwork()) {
			callBackAllFailure("网络未连接", actionId);
			return;
		}
		if (isShow) {
			mdialog = new LoadingDialog(getActivity(), lodingType);
			if(!getActivity().isFinishing()){ mdialog.show(); } 
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
	protected void callBackFailure(Throwable t, int errorNo, String strMsg,
			int actionId) {
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
	 * 
	 * @Title DisplayImage 图片加载
	 * @param url
	 *            图片地址
	 * @param v
	 *            控件
	 */
	protected static void DisplayImage(String url, ImageView v) {
		fb.display(v, url);
	}
	
	
	
    
}
