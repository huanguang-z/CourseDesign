package com.pony.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import net.tsz.afinal.http.AjaxParams;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.community.DepartListActivity;
import com.community.DoctorMessageActivity;
import com.community.NewsMessageActivity;
import com.community.R;
import com.community.SearchActivity;
import com.google.gson.reflect.TypeToken;
import com.pony.adapter.DepartSearchListAdapter;
import com.pony.adapter.NoticeAdapter;
import com.pony.banner.CycleViewPager;
import com.pony.banner.CycleViewPager.ImageCycleViewListener;
import com.pony.banner.utils.ViewFactory;
import com.pony.base.BaseFragment;
import com.pony.config.Consts;
import com.pony.model.BannerModel;
import com.pony.model.DeportmentModel;
import com.pony.model.MainModel;
import com.pony.model.NoticeModel;
import com.pony.model.ResponseEntry;
import com.pony.model.SelectImageItem;
import com.pony.util.ToastUtil;
import com.pony.view.GridviewForScrollView;
import com.pony.view.ListviewForScrollView;


public class IndexFragment extends BaseFragment {
    // »ñÈ¡view
    private View rootView;
    // »ñÈ¡¿Ø¼þ
    private ListView mListSearchMessage;
    private ListviewForScrollView mListMessage;
    private ListviewForScrollView mListRankMessage;

    private CycleViewPager cycleViewPager;
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<SelectImageItem> infos = new ArrayList<SelectImageItem>();
    private LinearLayout mllBuJu;
    private List<NoticeModel> listMsg = new ArrayList<NoticeModel>();
    private ScrollView mslview;
    private GridviewForScrollView mHotSoft;

    private TextView mtvKeShiSearch,mtvJIbingSearch;
    private EditText metName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_music, null);
        initWidget();
        initData();
        return rootView;
    }

    @Override
    public void initWidget() {

        metName = (EditText) rootView.findViewById(R.id.metName);
        mtvKeShiSearch = (TextView)  rootView.findViewById(R.id.mtvKeShiSearch);
        mtvJIbingSearch = (TextView)  rootView.findViewById(R.id.mtvJIbingSearch);
        mtvKeShiSearch.setOnClickListener(this);
        mtvJIbingSearch.setOnClickListener(this);


        metName.setFocusable(true);
        metName.setOnClickListener(this);
        mHotSoft = (GridviewForScrollView) rootView.findViewById(R.id.mHotSoft);

        mslview = (ScrollView) rootView.findViewById(R.id.mslview);
        mllBuJu = (LinearLayout) rootView.findViewById(R.id.mllBuJu);
        cycleViewPager = (CycleViewPager) getActivity().getFragmentManager().findFragmentById(R.id.fragment_soft_image);
        mListMessage = (ListviewForScrollView) rootView.findViewById(R.id.mListMessage);
        mListSearchMessage = (ListView) rootView.findViewById(R.id.mListSearchMessage);

        mListMessage.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(getActivity(), NewsMessageActivity.class);
                intent.putExtra("msg", list_result.get(position));
                getActivity().startActivity(intent);

            }
        });
        //
        mHotSoft.setOnItemClickListener(new OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {


            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.mtvKeShiSearch:

                if (TextUtils.isEmpty(metName.getText().toString())) {
                    ToastUtil.ShowCentre(getActivity(), "请输入搜索信息");
                    return;
                }
                listSearchMessage(false,metName.getText().toString(),"1");
                break;
            case R.id.mtvJIbingSearch:

                if (TextUtils.isEmpty(metName.getText().toString())) {
                    ToastUtil.ShowCentre(getActivity(), "请输入搜索信息");
                    return;
                }
                listSearchMessage(false,metName.getText().toString(),"2");
                break;
        }
    }

    @Override
    public void initData() {

        listMessagePhoneType(true);
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
                    mListSearchMessage.setVisibility(View.GONE);
                }else{

                }
            }
        });

//        MusicHotAdapter musicHotAdapter = new MusicHotAdapter(getActivity(), Consts.typeInforArray);
//        mHotSoft.setAdapter(musicHotAdapter);

    }


    private void listMessagePhoneType(boolean isShow) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listMessagePhoneNews");
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultFlag, isShow, "正在加载...");
    }

    private void listSearchMessage(boolean isShow, String searchMsg,String typeSearch) {
        AjaxParams params = new AjaxParams();
        params.put("action_flag", "listSearchMessage");
        params.put("searchMessage", searchMsg);
        params.put("typeSearch",typeSearch);
        httpPost(Consts.URL + Consts.APP.HospitalAction, params, Consts.actionId.resultCode, isShow, "正在加载...");
    }

    private List<NoticeModel> list_result = new ArrayList<NoticeModel>();
    private List<BannerModel> list_banner = new ArrayList<BannerModel>();
    List<DeportmentModel> list_resultSearch = new ArrayList<DeportmentModel>();
    DepartSearchListAdapter campusAdapter;
    @Override
    protected void callBackSuccess(ResponseEntry entry, int actionId) {
        super.callBackSuccess(entry, actionId);

        switch (actionId) {
            case Consts.actionId.resultCode:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {

                        list_resultSearch.clear();
                        list_resultSearch = mGson.fromJson(entry.getData(), new TypeToken<List<DeportmentModel>>() {
                        }.getType());

                        campusAdapter = new DepartSearchListAdapter(getActivity(), list_resultSearch);
                        mListSearchMessage.setAdapter(campusAdapter);
                        mListSearchMessage.setVisibility(View.VISIBLE);
                    } else {
                        ToastUtil.ShowCentre(getActivity(), "搜索信息不存在");
                    }
                }else{
                    ToastUtil.ShowCentre(getActivity(), "搜索信息不存在");
                }

                break;
            case Consts.actionId.resultFlag:
                if (null != entry.getData() && !TextUtils.isEmpty(entry.getData())) {

                    String jsonMsg = entry.getData().substring(1, entry.getData().length() - 1);
                    if (null != jsonMsg && !TextUtils.isEmpty(jsonMsg)) {

                        MainModel  mainModel = mGson.fromJson(entry.getData(),MainModel.class);


                        list_result =mainModel.getListNews();
                        list_banner = mainModel.getListBanner();



                        infos.clear();
                        views.clear();
                        for (int i = 0; i < list_banner.size(); i++) {
                            SelectImageItem info = new SelectImageItem();
                            info.setUrl(Consts.URL_IMAGE+list_banner.get(i).getBannerImg());
                            info.setSid(i);
                            infos.add(info);
                        }

                        views.add(ViewFactory.getImageView(getActivity(), infos.get(infos.size() - 1).getUrl()));
                        for (int i = 0; i < infos.size(); i++) {
                            views.add(ViewFactory.getImageView(getActivity(), infos.get(i).getUrl()));
                        }
                        views.add(ViewFactory.getImageView(getActivity(), infos.get(0).getUrl()));

                        cycleViewPager.setCycle(true);

                        cycleViewPager.setData(views, infos, mAdCycleViewListener);
                        cycleViewPager.setWheel(true);

                        cycleViewPager.setTime(2000);
                        cycleViewPager.setIndicatorCenter();


                        NoticeAdapter memAdapter = new NoticeAdapter(getActivity(), list_result);
                        mListMessage.setAdapter(memAdapter);

                    } else {
                    }
                }
                break;

            default:
                break;
        }

    }


    @Override
    protected void callBackAllFailure(String strMsg, int actionId) {
        super.callBackAllFailure(strMsg, actionId);
        ToastUtil.show(getActivity(), strMsg);

    }

    private ImageCycleViewListener mAdCycleViewListener = new ImageCycleViewListener() {

        @Override
        public void onImageClick(SelectImageItem info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Intent intent = new Intent(getActivity(), DoctorMessageActivity.class);
                intent.putExtra("msg",list_banner.get(position).getDoctorMsg());
                intent.putExtra("timeInfor",df.format(new Date()));
                getActivity().startActivity(intent);

            }
        }

    };


}
