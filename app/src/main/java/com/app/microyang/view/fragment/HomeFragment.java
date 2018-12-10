package com.app.microyang.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;

import com.app.microyang.R;
import com.app.microyang.base.BaseFragment;
import com.app.microyang.bean.NewsBean;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.RetrofitHelper;
import com.app.microyang.utils.ToastUtil;
import com.app.microyang.view.activity.NewsDetailsActivity;
import com.app.microyang.view.adapter.MyViewAdapter;
import com.scwang.smartrefresh.header.PhoenixHeader;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;
import com.scwang.smartrefresh.layout.footer.BallPulseFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.list)
    RecyclerView main_fragment_list;

    @BindView(R.id.refreshLayout)
    RefreshLayout refreshLayout;

    private List<NewsBean.DataBean> dataBeans = new ArrayList<>();

    private NewsBean.DataBean newsBeans;

    private MyViewAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        return view;
    }


    @Override
    protected void initData() {
        dataBeans = new ArrayList<>();
        refreshLayout.setRefreshHeader(new PhoenixHeader(mActivity));
        refreshLayout.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.FixedBehind));
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {

            }
        });
    }

    @Override
    protected void initEvent() {
        newsBeans = null;
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

                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        dataBeans = newsBean.getData();
                        for (int i = 0; i < dataBeans.size(); i++) {
                            String title = dataBeans.get(i).getTitle();
                            String publishDate = dataBeans.get(i).getPublishDateStr();
//                            String imageUrl = dataBeans.get(i).getImageUrls().get(0);
                            List<String> imageUrl = dataBeans.get(i).getImageUrls();
                            LogUtil.d("NEWS", title);
                            LogUtil.d("NEWS", publishDate);
                            LogUtil.d("NEWS", imageUrl.get(0));
                            newsBeans = new NewsBean.DataBean(title, publishDate, imageUrl);
                            dataBeans.add(newsBeans);
                            adapter = new MyViewAdapter(mActivity, dataBeans);
                            linearLayoutManager = new LinearLayoutManager(mActivity);
                            LayoutInflater layoutInflater = getLayoutInflater();
                            View view = layoutInflater.inflate(R.layout.fragment_home_head, null);
                            adapter.addHeaderView(view);
                            main_fragment_list.setHasFixedSize(true);
                            main_fragment_list.setLayoutManager(linearLayoutManager);
                            main_fragment_list.setAdapter(adapter);
                            initListener();
                        }
                    }
                });
    }

    private void initListener() {
        if (adapter != null) {
            adapter.setListener(new MyViewAdapter.OnItemClickListener() {
                @Override
                public void onClick(View view, int position) {
                    String detailsUrl = dataBeans.get(position).getUrl();
                    startActivity(new Intent(mActivity, NewsDetailsActivity.class)
                            .putExtra("url", detailsUrl));
                }
            });
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

}
