package com.pony.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.community.ImActivity;
import com.community.LoginActivity;
import com.community.R;
import com.community.UserActivity;
import com.pony.base.BaseFragment;

/**
 * @author WangXuan
 *         <p>
 *         2016-11-7
 */
public class PersonFragment extends BaseFragment {

    // 获取view
    private View rootView;
    private RelativeLayout mtvUser;
    private RelativeLayout mrlIm;
    private TextView mtvMaxMoney;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(
                R.layout.fragment_content, null);

        return rootView;
    }

    /**
     * 获取控件
     */
    @Override
    public void initWidget() {

        mtvUser = (RelativeLayout) rootView.findViewById(R.id.mtvUser);
        mrlIm = (RelativeLayout) rootView.findViewById(R.id.mrlIm);

        mtvUser.setOnClickListener(this);
        mrlIm.setOnClickListener(this);
    }

    /**
     * 处理点击事件
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mrlIm:
                Intent mrlCatesr = new Intent(getActivity(), ImActivity.class);
                getActivity().startActivity(mrlCatesr);
                break;

            case R.id.mtvUser:
                Intent mtvUser = new Intent(getActivity(), UserActivity.class);
                getActivity().startActivity(mtvUser);
                break;

            case R.id.mExit:
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
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
        initWidget();
        initData();
    }
}
