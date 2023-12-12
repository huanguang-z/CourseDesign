package com.pony.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.community.R;

public class DialogExameMsg {

	private Context context;
	private View loadingView;
	private Dialog loading;
	private Button submit_ok;
	private Button submit_no;
	
	private TextView mtvScore;
	private TextView mtvResultState;
	private TextView mtvResult;

	public DialogExameMsg(Context context) {
		super();
		this.context = context;
		loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_exame_show, null);
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
	
	
	public void setScore(String msg) {
		mtvScore = (TextView) loadingView.findViewById(R.id.mtvScore);
		mtvScore.setText(msg + "");
	}

	
	public void setResultState(String msg) {
		mtvResultState = (TextView) loadingView.findViewById(R.id.mtvResultState);
		mtvResultState.setText(msg + "");
	}
	
	
	public void setResult(String msg) {
		mtvResult = (TextView) loadingView.findViewById(R.id.mtvResult);
		mtvResult.setText(msg + "");
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
