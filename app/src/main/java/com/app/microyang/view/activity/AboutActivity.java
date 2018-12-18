package com.app.microyang.view.activity;

import android.os.Bundle;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.widget.TopBar;

import butterknife.BindView;

public class AboutActivity extends BaseActivity implements TopBar.topbarClickListner {

    @BindView(R.id.tb_about)
    TopBar tbAbout;

    @Override
    protected void initData(Bundle savedInstanceState) {
        tbAbout.setButtonBackground(R.drawable.icon_back, 0);
        tbAbout.setOnTopbarClickListener(this);
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_about;
    }

    @Override
    public void leftClick() {
        finish();
    }

    @Override
    public void rightClick() {

    }
}
