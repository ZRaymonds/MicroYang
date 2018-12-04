package com.app.microyang.view.fragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;

import com.app.microyang.R;
import com.app.microyang.base.BaseFragment;
import com.app.microyang.bean.NewsBean;
import com.app.microyang.view.adapter.NewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {

    @BindView(R.id.main_fragment_list)
    ListView main_fragment_list;

    private List<NewsBean.DataBean> dataBeans = new ArrayList<>();

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity,R.layout.fragment_home_head,null);
        View header = LayoutInflater.from(mActivity).inflate(R.layout.fragment_home_head,main_fragment_list,false);
        main_fragment_list.addHeaderView(header);
        NewsAdapter adapter = new NewsAdapter(dataBeans,mActivity);
        main_fragment_list.setAdapter(adapter);
        return view;
    }

    @Override
    protected void initData() {

    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_home_head;
    }
}
