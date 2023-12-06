package com.pony.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.community.R;

public class DialogMsg {

	private Context context;
	private View loadingView;
	private Dialog loading;
	private Button submit_ok;
	private Button submit_no;
	private TextView tishi_msg;

	public DialogMsg(Context context) {
		super();
		this.context = context;
		loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_show, null);
		InitData();
	}

	private void InitData() {
		loading = new Dialog(context, R.style.Dialog_image);
		loading.setContentView(loadingView);
		loading.setCanceledOnTouchOutside(true);
		loading.getWindow().setGravity(Gravity.FILL);
	}

	public Button submit_ok() {
		return (Button) loadingView.findViewById(R.id.submit_ok);
	}

	public Button submit_no() {
		return (Button) loadingView.findViewById(R.id.submit_no);
	}
	
	public void Set_Msg(String msg) {
		tishi_msg = (TextView) loadingView.findViewById(R.id.tishi_msg);
		tishi_msg.setText(msg + "");
	}


	public void Show() {

		if (loading != null) {
			loading.show();
		}
	}

	public void Close() {

		if (loading != null) {
			loading.dismiss();
		}
	}
}
