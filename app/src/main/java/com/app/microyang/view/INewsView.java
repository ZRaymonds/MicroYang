package com.app.microyang.view;

import com.app.microyang.bean.NewsBean;


public interface INewsView {

    void showLoading();

    void hideLoading();

    void initData(NewsBean dataBeans);

    void showErrorMsg(Exception e);

}
