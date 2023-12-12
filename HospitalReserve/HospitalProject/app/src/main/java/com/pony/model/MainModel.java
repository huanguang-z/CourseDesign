package com.pony.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2020/11/16.
 */

public class MainModel implements Serializable {

    private List<NoticeModel> listNews;
    private List<BannerModel> listBanner;

    public List<NoticeModel> getListNews() {
        return listNews;
    }

    public void setListNews(List<NoticeModel> listNews) {
        this.listNews = listNews;
    }

    public List<BannerModel> getListBanner() {
        return listBanner;
    }

    public void setListBanner(List<BannerModel> listBanner) {
        this.listBanner = listBanner;
    }
}
