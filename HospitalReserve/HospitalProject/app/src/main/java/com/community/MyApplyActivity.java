package com.community;

import java.util.ArrayList;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnLongClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;
import com.pony.adapter.OrderDoctorAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.db.MemberUserUtils;
import com.pony.listener.IntersetListner;
import com.pony.model.ApplyModel;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.ToastUtil;
import com.pony.view.DialogMsg;


public class MyApplyActivity extends BaseActivity implements IntersetListner {

    // 标题
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    private TextView mIvStu;
    private ListView mListMessage;
    private List<ApplyModel> list_result = new ArrayList<ApplyModel>();
    private String state;
    private LinearLayout mllNomessage;
    private DialogMsg dialogMsg;
    private int posNumber;
    OrderDoctorAdapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_im);
        initWidget();
        initData();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mIvBack:
                finish();
                break;
            case R.id.mIvStu:
                break;

        }
    }

    @Override
    public void initWidget() {
        dialogMsg = new DialogMsg(this);
        mIvStu = (TextView) findViewById(R.id.mIvStu);
        mIvStu.setText("添加");
        mIvStu.setVisibility(View.GONE);
        mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
        mListMessage = (ListView) findViewById(R.id.mListMessage);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        state = this.getIntent().getStringExtra("state");
        mTvTitle.setText("挂号记录");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mIvStu.setOnClickListener(this);
    }
    UserModel userModel;
    @Override
    public void initData() {
        userModel  = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
        MessageAction(true);
        mListMessage.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

                Intent intent = new Intent(MyApplyActivity.this, AppluChuFangMessageActivity.class);
                intent.putExtra("msg",list_result.get(pos));
                MyApplyActivity.this.startActivity(intent);


            }
        });
        mListMessage.setOnLongClickListener(new OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
    }

    private void MessageAction(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listMesageBorrow");
        params.put("applyUserId", MemberUserUtils.getUid(this));
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
    }

    private void deleteMessage(boolean isShow, ApplyModel applyModel) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "deleteApplayMessage");
        params.put("applyId", applyModel.getApplyId());
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
    }


    private void updateCZ(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "updatePayCZ");
        params.put("umoney", (Double.valueOf(userModel.getUmoney()) + Double.valueOf(list_result.get(posIndex).getDoctorMsg().getDoctorMoney())) + "");
        params.put("userId", MemberUserUtils.getUid(this));
        httpPost(Consts.URL + Consts.APP.RegisterAction, params, Consts.actionId.resultFive, isShow, "正在更新...");
    }

    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<ApplyModel>>() {
                        }.getType());
                        categoryAdapter = new OrderDoctorAdapter(MyApplyActivity.this, list_result, MyApplyActivity.this);
                        mListMessage.setAdapter(categoryAdapter);
                    } else {
                        mllNomessage.setVisibility(View.VISIBLE);
                    }
                }
                break;
            case Consts.actionId.resultCode:
                list_result.remove(posIndex);
                categoryAdapter.notifyDataSetChanged();
                updateCZ(false);

                break;

            case Consts.actionId.resultFive:
                userModel.setUmoney((Double.valueOf(userModel.getUmoney()) + Double.valueOf(list_result.get(posIndex).getDoctorMsg().getDoctorMoney())) + "");
                MemberUserUtils.putBean(this, "user_messgae", userModel);
                ToastUtil.show(MyApplyActivity.this, entry.getRepMsg());
                ToastUtil.show(MyApplyActivity.this, entry.getRepMsg());
                break;
        }

    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(MyApplyActivity.this, strMsg);

    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    private int posIndex;

    @Override
    public void setDelete(int pos, ApplyModel intersetModel) {
        posIndex = pos;
        deleteMessage(false, intersetModel);
    }

}
