package com.app.microyang.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
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

    private List<NewsBean.DataBean> dataBeans;

    private NewsBean.DataBean newsBeans;

    private MyViewAdapter adapter;

    private LinearLayoutManager linearLayoutManager;

    private boolean hasNext = false;

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        return view;
    }


    @Override
    protected void initData() {
        refreshLayout.setRefreshHeader(new PhoenixHeader(mActivity));
        refreshLayout.setRefreshFooter(new BallPulseFooter(mActivity).setSpinnerStyle(SpinnerStyle.Scale));
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
                        LogUtil.e("error",e.getMessage());
                    }

                    @Override
                    public void onNext(NewsBean newsBean) {
                        dataBeans = newsBean.getData();
                        hasNext = newsBean.isHasNext();
                        if (hasNext = newsBean.isHasNext() == true) {
                            sendHasNext(hasNext);
                        }
                        for (int i = 0; i < dataBeans.size(); i++) {
                            String title = dataBeans.get(i).getTitle();
                            String publishDate = dataBeans.get(i).getPublishDateStr();
                            List<String> imageUrl = dataBeans.get(i).getImageUrls();
                            newsBeans = new NewsBean.DataBean(title, publishDate, imageUrl);
                            dataBeans.add(newsBeans);
                            adapter = new MyViewAdapter(mActivity, dataBeans);
                            linearLayoutManager = new LinearLayoutManager(mActivity);
                            LayoutInflater layoutInflater = getLayoutInflater();
                            View view = layoutInflater.inflate(R.layout.fragment_home_head, null);
                            adapter.addHeaderView(view);
                            main_fragment_list.setHasFixedSize(true);
                            main_fragment_list.setLayoutManager(linearLayoutManager);
                            adapter.setHasStableIds(true);
                            main_fragment_list.setAdapter(adapter);
                            initListener();
                            LogUtil.d("NEWS", title);
                            LogUtil.d("NEWS", publishDate);
                            LogUtil.d("NEWS", imageUrl.get(0));
                        }
                    }
                });
    }

    private void sendHasNext(boolean hasNext) {
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(final RefreshLayout refreshLayout) {
                adapter.refresh(dataBeans);
                refreshLayout.setEnableRefresh(true);
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshLayout) {
                if (dataBeans.size()<20){
                    adapter.add(dataBeans);
                    refreshLayout.setEnableLoadMore(true);
                    refreshLayout.finishLoadMore(2000);
                }else {
                    refreshLayout.setEnableLoadMore(false);
                    ToastUtil.show(mActivity,"-end-");
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
