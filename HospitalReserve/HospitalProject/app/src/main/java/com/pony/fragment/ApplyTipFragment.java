package com.pony.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.community.DepartListActivity;
import com.community.R;
import com.pony.adapter.TypeAdapter;
import com.pony.base.BaseFragment;
import com.pony.view.DialogListMsg;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class ApplyTipFragment extends BaseFragment {

    private View rootView;
    private ImageView todayInfor, applyInfor;


    private List<String> mlistData = new ArrayList<String>();
    private DialogListMsg dialogListMsg;
    private TypeAdapter listaAdapter;

    private int posIndex = 0;
    ArrayList<String> DaysList = new ArrayList<String>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_apply_tip, null);
        initWidget();
        initData();
        return rootView;
    }

    @Override
    public void initWidget() {
        todayInfor = (ImageView) rootView.findViewById(R.id.todayInfor);
        applyInfor = (ImageView) rootView.findViewById(R.id.applyInfor);

        todayInfor.setOnClickListener(this);
        applyInfor.setOnClickListener(this);
    }

    @Override
    public void initData() {



        dialogListMsg = new DialogListMsg(getActivity());
        dialogListMsg.setTitle().setText("请选择日期");

        listaAdapter = new TypeAdapter(getActivity());

        DaysList = getTimeInfor(8);
        DaysList.remove(0);
        listaAdapter.setData(DaysList);
        dialogListMsg.show_listview().setAdapter(listaAdapter);
        listaAdapter.notifyDataSetChanged();

        dialogListMsg.show_listview().setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int pos, long arg3) {

                Intent intent = new Intent(getActivity(), DepartListActivity.class);
                intent.putExtra("timeInfor",DaysList.get(pos));
                startActivity(intent);


                dialogListMsg.Close();
                posIndex = pos;
            }
        });

        dialogListMsg.submit_no().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogListMsg.Close();
            }
        });

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.todayInfor:

                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Intent intent = new Intent(getActivity(), DepartListActivity.class);
                intent.putExtra("timeInfor",df.format(new Date()));
                startActivity(intent);
                break;
            case R.id.applyInfor:
                dialogListMsg.Show();
                break;
        }

    }
    public static ArrayList<String> getTimeInfor(int intervals) {
        ArrayList<String> pastDaysList = new ArrayList<String>();
        ArrayList<String> fetureDaysList = new ArrayList<String>();
        for (int i = 0; i < intervals; i++) {
            pastDaysList.add(getPastDate(i));
            fetureDaysList.add(getFetureDate(i));
        }
        return fetureDaysList;
    }

    /**
     * 获取过去第几天的日期
     *
     * @param past
     * @return
     */
    public static String getPastDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) - past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

    /**
     * 获取未来 第 past 天的日期
     *
     * @param past
     * @return
     */
    public static String getFetureDate(int past) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, calendar.get(Calendar.DAY_OF_YEAR) + past);
        Date today = calendar.getTime();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String result = format.format(today);
        return result;
    }

}
