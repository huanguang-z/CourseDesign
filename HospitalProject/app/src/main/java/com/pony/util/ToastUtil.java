package com.pony.util;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;


/**
 * 
 * @author WangXuan
 *
 * 2016-11-7
 */

public class ToastUtil {

	public static void show(Context context, String info) {
		Toast.makeText(context, info, 300).show();
	}

	public static void show(Context context, int info) {
		Toast.makeText(context, info, 300).show();
	}

	public static void ShowToast(Context context, String info) {
		Toast.makeText(context, info, Toast.LENGTH_LONG).show();
	}

	public static void ShowCentre(Context context, String info) {
		Toast toast = Toast.makeText(context, info, Toast.LENGTH_LONG);
		toast.setGravity(Gravity.CENTER, 0, 0);
		toast.show();
	}

}
