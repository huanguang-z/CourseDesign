package com.community;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.google.gson.reflect.TypeToken;
import com.pony.adapter.ReplyAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.ReviewObservable;
import com.pony.model.DortorModel;
import com.pony.model.ResponseEntry;
import com.pony.model.ReviewBean;
import com.pony.view.ListviewForScrollView;
import com.pony.view.RoundedCornerImageView;
import com.squareup.picasso.Picasso;

import net.tsz.afinal.http.AjaxParams;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;


public class ArticleMessageActivity extends BaseActivity implements Observer{
    // title
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    DortorModel noticeModel;

    private RoundedCornerImageView mivShop;
    private TextView inforTitle,mTvMoney,mtvTime;
    private ImageView mivCreateMessage;
    private ListviewForScrollView mListReviewMessage;
    private Button btnGuanZhu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_article_msg);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {

        btnGuanZhu = (Button) findViewById(R.id.btnGuanZhu);
        btnGuanZhu.setOnClickListener(this);
        mListReviewMessage = (ListviewForScrollView) findViewById(R.id.mListReviewMessage);
        inforTitle = (TextView) findViewById(R.id.inforTitle);
        mTvMoney = (TextView) findViewById(R.id.mTvMoney);
        mtvTime = (TextView) findViewById(R.id.mtvTime);
        mivCreateMessage = (ImageView) findViewById(R.id.mivCreateMessage);
        mivCreateMessage.setOnClickListener(this);
        mivCreateMessage.setVisibility(View.VISIBLE);

        mivShop = (RoundedCornerImageView) findViewById(R.id.mivShop);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                ArticleMessageActivity.this.finish();
                break;

            case R.id.mivCreateMessage:
                Intent intent = new Intent(this, OpinionActivity.class);
                intent.putExtra("msg",noticeModel);
                startActivity(intent);
                break;
        }
    }

    @Override
    public void initData() {

        noticeModel = (DortorModel) this.getIntent().getSerializableExtra("msg");


        if(this.getIntent().getStringExtra("flag").equals("ok")){
            mivCreateMessage.setVisibility(View.VISIBLE);
        }else{
            mivCreateMessage.setVisibility(View.GONE);
        }

        inforTitle.setText(noticeModel.getDoctorName());
        mtvTime.setText("级别："+noticeModel.getDoctorLevel());

        if (!TextUtils.isEmpty(noticeModel.getDoctorImage())) {
            Picasso.with(this).load(Consts.URL_IMAGE + noticeModel.getDoctorImage()).placeholder(R.drawable.default_drawable_show_pictrue)
                    .into(mivShop);
        }

        ListNoticesMessage(false);
    }


    private void ListNoticesMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "reviewListMessage");
        params.put("reviewMessageId", noticeModel.getDoctorId() + "");
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在提交...");
    }


    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {

            case Consts.actionId.resultCode:
                ListNoticesMessage(false);
                break;
            case Consts.actionId.resultFlag:
                List<ReviewBean> list_result = mGson.fromJson(entry.getData(), new TypeToken<List<ReviewBean>>() {
                }.getType());
                ReplyAdapter replyAdapter = new ReplyAdapter(this, list_result);
                mListReviewMessage.setAdapter(replyAdapter);
                break;

        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        ReviewObservable.getInstance().addObserver(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReviewObservable.getInstance().deleteObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        ListNoticesMessage(false);
    }
}
