package com.app.microyang.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.tv_haveAccount)
    TextView tv_haveAccount;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick(R.id.tv_haveAccount)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_haveAccount:
                startActivity(LoginActivity.class);
                overridePendingTransition(R.anim.right_out, R.anim.left_in);
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }
}
