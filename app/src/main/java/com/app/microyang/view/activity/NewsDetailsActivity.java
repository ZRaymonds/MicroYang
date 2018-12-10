package com.app.microyang.view.activity;

import android.os.Bundle;
import android.webkit.WebView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;

import butterknife.BindView;

public class NewsDetailsActivity extends BaseActivity {

    @BindView(R.id.wv_details)
    WebView wvDetails;

    @Override
    protected void initData(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        String url = getIntent().getStringExtra("url");
        wvDetails.loadUrl(url);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_news_details;
    }
}
