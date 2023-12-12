package com.community;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.google.gson.reflect.TypeToken;
import com.pony.adapter.DepartSearchListAdapter;
import com.pony.base.BaseActivity;
import com.pony.config.Consts;
import com.pony.model.DeportmentModel;
import com.pony.model.ResponseEntry;
import com.pony.util.ToastUtil;

import net.tsz.afinal.http.AjaxParams;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends BaseActivity {

    // title
    private TextView mTvTitle;
    // 返回
    private ImageView mIvBack;
    // 查询按钮
    private EditText metName;
    private ListView mListMessage;
    private LinearLayout mllNomessage;
    private TextView mtvKeShiSearch,mtvJIbingSearch;
    List<DeportmentModel> list_result = new ArrayList<DeportmentModel>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initWidget();
        initData();
    }

    @Override
    public void initWidget() {

        mtvKeShiSearch = (TextView) findViewById(R.id.mtvKeShiSearch);
        mtvJIbingSearch = (TextView) findViewById(R.id.mtvJIbingSearch);
        mtvKeShiSearch.setOnClickListener(this);
        mtvJIbingSearch.setOnClickListener(this);

        mllNomessage = (LinearLayout) findViewById(R.id.mllNomessage);
        mListMessage = (ListView) findViewById(R.id.mListMessage);
        metName = (EditText) findViewById(R.id.metName);

        mIvBack = (ImageView) findViewById(R.id.mIvBack);
        mTvTitle = (TextView) findViewById(R.id.mTvTitle);
        mTvTitle.setText("搜索");
        mIvBack.setVisibility(View.VISIBLE);
        mIvBack.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mIvBack:
                SearchActivity.this.finish();
                break;
            case R.id.mtvKeShiSearch:

                if (TextUtils.isEmpty(metName.getText().toString())) {
                    ToastUtil.ShowCentre(SearchActivity.this, "请输入搜索信息");
                    return;
                }
                listSearchMessage(false,metName.getText().toString(),"1");
                break;
            case R.id.mtvJIbingSearch:

                if (TextUtils.isEmpty(metName.getText().toString())) {
                    ToastUtil.ShowCentre(SearchActivity.this, "请输入搜索信息");
                    return;
                }
                listSearchMessage(false,metName.getText().toString(),"2");
                break;
        }
    }

    @Override
    public void initData() {
        metName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                if(editable.toString().length()==0){
                    list_result.clear();
                    campusAdapter.notifyDataSetChanged();
                }
            }
        });
    }

    private void listSearchMessage(boolean isShow, String searchMsg,String typeSearch) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listSearchMessage");
        params.put("searchMessage", searchMsg);
        params.put("typeSearch",typeSearch);
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
    }


    DepartSearchListAdapter campusAdapter;
    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultCode:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {

                        list_result.clear();
                        list_result = mGson.fromJson(entry.getData(), new TypeToken<List<DeportmentModel>>() {
                        }.getType());

                        campusAdapter = new DepartSearchListAdapter(SearchActivity.this, list_result);
                        mListMessage.setAdapter(campusAdapter);
                    } else {
                        ToastUtil.ShowCentre(SearchActivity.this, "搜索信息不存在");
                    }
                }else{
                    ToastUtil.ShowCentre(SearchActivity.this, "搜索信息不存在");
                }

                break;

        }

    }




}
