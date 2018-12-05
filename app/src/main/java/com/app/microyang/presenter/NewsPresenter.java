package com.app.microyang.presenter;

import com.app.microyang.bean.NewsBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.INewsModel;
import com.app.microyang.model.impl.NewsModelImpl;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.view.INewsView;

import java.util.ArrayList;
import java.util.List;

public class NewsPresenter {

    private INewsView iNewsView;
    private INewsModel iNewsModel;

    private List<NewsBean.DataBean> dataBeans = new ArrayList<>();

    public NewsPresenter(INewsView iNewsView) {
        this.iNewsView = iNewsView;
        iNewsModel = new NewsModelImpl();
    }

    public void getNews() {
        iNewsView.showLoading();
        iNewsModel.News(new OnNetListener<NewsBean>() {
            @Override
            public void onSuccess(NewsBean newsBean) {
                iNewsView.initData(newsBean);
                dataBeans = newsBean.getData();
                for (int i = 0; i < dataBeans.size(); i++) {
                    String title = dataBeans.get(i).getTitle();
                    String content = dataBeans.get(i).getContent();
                    String imageUrl = dataBeans.get(i).getUrl();
                    LogUtil.d("NEWS",title);
                    LogUtil.d("NEWS",content);
                    LogUtil.d("NEWS",imageUrl);
                }
                iNewsView.hideLoading();
            }

            @Override
            public void onFailed(Exception e) {
                iNewsView.showErrorMsg(e);
                iNewsView.hideLoading();
            }
        });
    }

}
