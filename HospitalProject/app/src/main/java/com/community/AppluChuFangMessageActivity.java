package com.community;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.pony.adapter.ChoiceTimeListAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.db.ShowObservable;
import com.pony.model.ApplyModel;
import com.pony.model.DiagnosisModel;
import com.pony.model.DortorModel;
import com.pony.model.JiJianModel;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.ToastUtil;
import com.pony.view.DialogMsg;
import com.pony.view.ListviewForScrollView;
import com.squareup.picasso.Picasso;

import net.tsz.afinal.http.AjaxParams;

public class AppluChuFangMessageActivity extends BaseActivity {
    // title
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private TextView mIvStu;

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chufangmsg);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {
        mIvStu = (TextView) findViewById(R.id.mIvStu);
        mIvStu.setOnClickListener(this);
        mIvStu.setText("就诊评价");
        tv1 = (TextView) findViewById(R.id.tv1);
        tv2 = (TextView) findViewById(R.id.tv2);
        tv3 = (TextView) findViewById(R.id.tv3);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("诊断详情");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                AppluChuFangMessageActivity.this.finish();
                break;
            case R.id.mIvStu:
                Intent mbtnreview = new Intent(this, ArticleMessageActivity.class);
                mbtnreview.putExtra("msg",diagnosisModel.getDoctorMsg());
                mbtnreview.putExtra("flag","ok");
                startActivity(mbtnreview);
                break;

        }
    }


    @Override
    public void initData() {

        queryChuFangPhone(false);
    }


    private void queryChuFangPhone(boolean isShow) {
        ApplyModel  applyModel = (ApplyModel)this.getIntent().getSerializableExtra("msg");
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "queryChuFangPhone");
        params.put("applyId", applyModel.getApplyId());
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在参与...");
    }
    DiagnosisModel diagnosisModel;

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:

                diagnosisModel= mGson.fromJson(entry.getData(), DiagnosisModel.class);
                tv1.setText(diagnosisModel.getDiagnosisInfor());
                tv2.setText(diagnosisModel.getDiagnosisMethods());
                tv3.setText(diagnosisModel.getDiagnosisDrug());
                break;

        }


    }


}
