package com.community;

import net.tsz.afinal.http.AjaxParams;

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
import com.pony.model.DortorModel;
import com.pony.model.JiJianModel;
import com.pony.model.ResponseEntry;
import com.pony.model.UserModel;
import com.pony.util.ToastUtil;
import com.pony.view.DialogMsg;
import com.pony.view.ListviewForScrollView;
import com.squareup.picasso.Picasso;

public class DoctorMessageActivity extends BaseActivity {
    // title
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    private TextView mtvtitle;

    DortorModel noticeModel;

    private TextView departmentHosName;
    private TextView doctorName;
    private TextView doctorSex;
    private TextView doctorYear;
    private TextView departmentName;
    private TextView doctorMessage;


    private TextView doctorMoney;
    private TextView doctorLevel;


    private Button mbtnPay, mbtnreview;
    private TextView mtvShopPrice;

    private int choiceTime = 1;
    private DialogMsg dialogMsg;
    private ImageView mivUserImage;
    private ListviewForScrollView mListMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctornewsmsg);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {
        dialogMsg = new DialogMsg(this);
        dialogMsg.Set_Msg("预约成功，可在个人中心查看自己的预约信息");

        dialogMsg.submit_ok().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DoctorMessageActivity.this.finish();
                dialogMsg.Close();
            }
        });
        dialogMsg.submit_no().setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                DoctorMessageActivity.this.finish();
                dialogMsg.Close();
            }
        });

        mbtnPay = (Button) findViewById(R.id.mbtnPay);
        mbtnPay.setOnClickListener(this);
        mivUserImage = (ImageView) findViewById(R.id.mivUserImage);
        departmentHosName = (TextView) findViewById(R.id.departmentHosName);
        doctorName = (TextView) findViewById(R.id.doctorName);
        doctorSex = (TextView) findViewById(R.id.doctorSex);
        doctorYear = (TextView) findViewById(R.id.doctorYear);
        departmentName = (TextView) findViewById(R.id.departmentName);
        doctorMessage = (TextView) findViewById(R.id.doctorMessage);

        mtvShopPrice = (TextView) findViewById(R.id.mtvShopPrice);
        mtvtitle = (TextView) findViewById(R.id.mtvtitle);


        doctorMoney = (TextView) findViewById(R.id.doctorMoney);
        doctorLevel = (TextView) findViewById(R.id.doctorLevel);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("医生详情");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);
        mtvShopPrice.setOnClickListener(this);

        mbtnreview = (Button) findViewById(R.id.mbtnreview);
        mbtnreview.setOnClickListener(this);

        mListMessage = (ListviewForScrollView) findViewById(R.id.mListMessage);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                DoctorMessageActivity.this.finish();
                break;

            case R.id.mbtnPay:

                if(Double.valueOf(noticeModel.getDoctorMoney())<=Double.valueOf(userModel.getUmoney())){
                    addMessage(true);
                }else{
                    ToastUtil.show(this, "您的账户余额不足，请先充值！");
                }


                break;
            case R.id.mbtnreview:
                Intent mbtnreview = new Intent(this, ArticleMessageActivity.class);
                mbtnreview.putExtra("msg",noticeModel);
                mbtnreview.putExtra("flag","no");
                startActivity(mbtnreview);

                break;
            case R.id.mtvShopPrice:
                Intent intent = new Intent(this, AddressChoiceActivity.class);
                this.startActivityForResult(intent, 0);
                break;


        }
    }
    UserModel userModel;
    @Override
    public void initData() {
        userModel  = (UserModel) MemberUserUtils.getBean(this, "user_messgae");
        noticeModel = (DortorModel) this.getIntent().getSerializableExtra("msg");

        departmentHosName = (TextView) findViewById(R.id.departmentHosName);
        doctorName = (TextView) findViewById(R.id.doctorName);
        doctorSex = (TextView) findViewById(R.id.doctorSex);
        doctorYear = (TextView) findViewById(R.id.doctorYear);
        departmentName = (TextView) findViewById(R.id.departmentName);
        doctorMessage = (TextView) findViewById(R.id.doctorMessage);

        departmentHosName.setText(noticeModel.getDepartmentHosName());
        // 书名，出版社，种类
        doctorName.setText("名字：" + noticeModel.getDoctorName());
        doctorSex.setText("性别：" + noticeModel.getDoctorSex());

        doctorYear.setText("年限：" + noticeModel.getDoctorYear());
        departmentName.setText("科室：" + noticeModel.getDepartmentName());

        doctorMessage.setText("        " + noticeModel.getDoctorMessage());

        doctorMoney.setText("挂号费：" + noticeModel.getDoctorMoney()+"元/位");
        doctorLevel.setText("类型：" + noticeModel.getDoctorLevel());



        if (!TextUtils.isEmpty(noticeModel.getDoctorImage())) {
            Picasso.with(this).load(Consts.URL_IMAGE + noticeModel.getDoctorImage()).placeholder(R.drawable.default_drawable_show_pictrue)
                    .into(mivUserImage);
        }

        choiceTimeListAdapter  = new ChoiceTimeListAdapter(this,Consts.timeChoiceArr,this.getIntent().getStringExtra("timeInfor"));
        mListMessage.setAdapter(choiceTimeListAdapter);

        mListMessage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                posIndex = i;
                choiceTimeListAdapter.setPos(i);
                choiceTimeListAdapter.notifyDataSetChanged();
            }
        });
    }

    private int posIndex;
    ChoiceTimeListAdapter choiceTimeListAdapter;

    private void addMessage(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "addBorrow");
        params.put("applyUserId", MemberUserUtils.getUid(this));
        params.put("applyUserName", jianModel.getJijianName()+","+jianModel.getJijianPhone());
        params.put("applyMessageId", noticeModel.getDoctorId());
        params.put("applyMessageName", noticeModel.getDoctorName());
        params.put("applyChoiceTime", this.getIntent().getStringExtra("timeInfor")+" "+Consts.timeChoiceArr[posIndex]);
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在参与...");
    }


    private void updateCZ(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "updatePayCZ");
        params.put("umoney",(Double.valueOf(userModel.getUmoney())-Double.valueOf(noticeModel.getDoctorMoney()))+"");
        params.put("userId", MemberUserUtils.getUid(this));
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultCode, isShow, "正在更新...");
    }



    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultCode:
                userModel.setUmoney((Double.valueOf(userModel.getUmoney())-Double.valueOf(noticeModel.getDoctorMoney()))+"");
                MemberUserUtils.putBean(this, "user_messgae", userModel);
                dialogMsg.Show();

                break;
            case Consts.actionId.resultFlag:
                updateCZ(false);
                ShowObservable.getInstance().notifyStepChange("ok");


                break;
        }


    }

    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(DoctorMessageActivity.this, strMsg);

    }
    private JiJianModel jianModel;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 0) {
            jianModel = (JiJianModel) data.getSerializableExtra("msg");
            mtvShopPrice.setText("就诊人信息："+jianModel.getJijianName()+","+jianModel.getJijianPhone());
        }
    }
}
