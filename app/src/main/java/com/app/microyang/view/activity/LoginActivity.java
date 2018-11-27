package com.app.microyang.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.view.fragment.HomeFragment;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.tv_newAccount)
    TextView tv_newAccount;

    @BindView(R.id.btn_login)
    Button btn_login;

    @Override
    protected void initData(Bundle savedInstanceState) {

    }

    @OnClick({R.id.tv_newAccount,R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_newAccount:
                startActivity(RegisterActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.btn_login:
//                startActivity(MainActivity.class);
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }
}
