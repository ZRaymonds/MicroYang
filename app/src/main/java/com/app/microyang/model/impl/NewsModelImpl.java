package com.app.microyang.model.impl;

import com.app.microyang.bean.NewsBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.INewsModel;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.RetrofitHelper;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class NewsModelImpl implements INewsModel {

    @Override
    public void News(final OnNetListener<NewsBean> onNetListener) {
        ServerApi serverApi = RetrofitHelper.getServerNews();
        serverApi.getNews()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<NewsBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailed((Exception) e);
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        onNetListener.onSuccess(newsBean);
                        LogUtil.d("NEWS",newsBean.toString());
                    }
                });
    }
}
