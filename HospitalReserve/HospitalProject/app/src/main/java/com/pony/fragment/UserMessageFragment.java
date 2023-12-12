package com.pony.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.community.AddressListActivity;
import com.community.LoginActivity;
import com.community.MoneyListActivity;
import com.community.MyApplyActivity;
import com.community.R;
import com.community.SoftMessageActivity;
import com.community.UserActivity;
import com.pony.base.BaseFragment;

public class UserMessageFragment extends BaseFragment {

    // 获取view
    private View rootView;
    private RelativeLayout mtvUser;
    private RelativeLayout mrlIm;
    private RelativeLayout mrlApply;
    private RelativeLayout mtvSoft;
    private RelativeLayout mtvnoScore;
    private RelativeLayout mtvnojiuzhen;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_content_person, null);
        initWidget();
        initData();
        return rootView;
    }

    /**
     * 获取控件
     */
    @Override
    public void initWidget() {

        mtvnoScore = (RelativeLayout) rootView.findViewById(R.id.mtvnoScore);
        mtvSoft = (RelativeLayout) rootView.findViewById(R.id.mtvSoft);
        mrlApply = (RelativeLayout) rootView.findViewById(R.id.mrlApply);
        mtvUser = (RelativeLayout) rootView.findViewById(R.id.mtvUser);
        mrlIm = (RelativeLayout) rootView.findViewById(R.id.mrlIm);

        mtvUser.setOnClickListener(this);
        mrlIm.setOnClickListener(this);
        mrlApply.setOnClickListener(this);
        mtvSoft.setOnClickListener(this);
        mtvnoScore.setOnClickListener(this);

        mtvnojiuzhen = (RelativeLayout) rootView.findViewById(R.id.mtvnojiuzhen);
        mtvnojiuzhen.setOnClickListener(this);


    }

    /**
     * 处理点击事件
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.mtvnojiuzhen:
                Intent mtvnojiuzhen = new Intent(getActivity(), AddressListActivity.class);
                getActivity().startActivity(mtvnojiuzhen);
                break;

            case R.id.mrlIm:
                Intent mrlIm = new Intent(getActivity(), MyApplyActivity.class);
                getActivity().startActivity(mrlIm);
                break;
            case R.id.mtvSoft:
                Intent mtvSoft = new Intent(getActivity(), SoftMessageActivity.class);
                getActivity().startActivity(mtvSoft);
                break;


            case R.id.mtvUser:
                Intent mtvUser = new Intent(getActivity(), UserActivity.class);
                getActivity().startActivity(mtvUser);
                break;

            case R.id.mtvnoScore:
                Intent intent = new Intent(getActivity(), MoneyListActivity.class);
                startActivity(intent);
                break;


            default:
                break;
        }

    }

    /**
     * 处理数据
     */
    @Override
    public void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();

    }
}
