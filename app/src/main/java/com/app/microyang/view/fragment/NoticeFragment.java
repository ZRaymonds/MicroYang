package com.app.microyang.view.fragment;

import android.os.Bundle;
import android.view.View;

import com.app.microyang.R;
import com.app.microyang.base.BaseFragment;

public class NoticeFragment extends BaseFragment {

    @Override
    protected View initView(Bundle savedInstanceState) {
        View view = View.inflate(mActivity,R.layout.fragment_notice,null);
        return view;
    }

    @Override
    public int getLayoutResId() {
        return R.layout.fragment_notice;
    }
}
