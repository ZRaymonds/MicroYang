package com.app.microyang.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_newAccount)
    TextView tv_newAccount;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.tv_newAccount)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_newAccount:
                startActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }
}
