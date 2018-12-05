package com.app.microyang.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.app.microyang.R;
import com.app.microyang.base.BaseFragment;
import com.app.microyang.bean.NewsBean;
import com.app.microyang.presenter.NewsPresenter;
import com.app.microyang.utils.ToastUtil;
import com.app.microyang.view.INewsView;
import com.app.microyang.view.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment implements INewsView {

    @BindView(R.id.list)
    ListView main_fragment_list;

    @BindView(R.id.pb)
    ProgressBar pb;

    private NewsPresenter newsPresenter;

    private List<NewsBean.DataBean> dataBeans = new ArrayList<>();

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity, R.layout.fragment_home, null);
        newsPresenter = new NewsPresenter(this);
        View header = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home_head, main_fragment_list, false);
        main_fragment_list.addHeaderView(header);
        NewsAdapter adapter = new NewsAdapter(dataBeans,mActivity);
        main_fragment_list.setAdapter(adapter);
        return view;
    }

    @Override
    protected void initData() {
        newsPresenter.getNews();
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home;
    }

    @Override
    public void showLoading() {
        pb.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pb.setVisibility(View.GONE);
    }

    @Override
    public void initData(NewsBean dataBeans) {
        newsPresenter.getNews();
    }

    @Override
    public void showErrorMsg(Exception e) {
        ToastUtil.show(mActivity,e.getMessage());
    }
}
