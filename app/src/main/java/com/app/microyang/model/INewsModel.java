package com.app.microyang.model;

import com.app.microyang.bean.NewsBean;
import com.app.microyang.callBack.OnNetListener;

public interface INewsModel {

    void News(final OnNetListener<NewsBean> onNetListener);

}
