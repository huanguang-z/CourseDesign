package com.pony.view;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.community.R;

public class DialogListMsg {

	private Context context;
	private View loadingView;
	private Dialog loading;
	private ListView list_add_msg_zoom;
	private Button submit_no;
	private TextView mTvtitle;

	public DialogListMsg(Context context) {
		super();
		this.context = context;

		loadingView = LayoutInflater.from(context).inflate(R.layout.dialog_list_show, null);
		InitData();
	}

	private void InitData() {
		loading = new Dialog(context, R.style.Dialog_image);
		loading.setContentView(loadingView);
		loading.setCanceledOnTouchOutside(true);
		loading.getWindow().setGravity(Gravity.FILL);
	}

	public TextView setTitle() {
		return (TextView) loadingView.findViewById(R.id.mTvtitle);
	}
	
	
	public ListView show_listview() {
		return (ListView) loadingView.findViewById(R.id.list_add_msg_zoom);
	}

	public Button submit_no() {
		return (Button) loadingView.findViewById(R.id.submit_no);
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
