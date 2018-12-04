package com.app.microyang.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.presenter.LoginPresenter;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.RSAUtil;
import com.app.microyang.utils.ToastUtil;
import com.app.microyang.utils.VerifyUtil;
import com.app.microyang.view.ILoginView;
import com.app.microyang.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements ILoginView {

    @BindView(R.id.tv_newAccount)
    TextView tv_newAccount;

    @BindView(R.id.btn_login)
    Button btn_login;

    @BindView(R.id.et_login_studentID)
    EditText et_login_studentID;

    @BindView(R.id.et_login_password)
    EditText et_login_password;

    private Context mContext;

    private LoadingDialog loadingDialog;

    private LoginPresenter loginPresenter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mContext = this;
        loginPresenter = new LoginPresenter(this);
        initLoadDialog();
    }

    @OnClick({R.id.tv_newAccount, R.id.btn_login})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_newAccount:
                startActivity(RegisterActivity.class);
                overridePendingTransition(R.anim.right_in, R.anim.left_out);
                break;
            case R.id.btn_login:
                if (VerifyUtil.isConnect(mContext)) {
                    if (getLgUsername().isEmpty()) {
                        ToastUtil.show(this, "用户名不能为空");
                    } else if (getLgPassword().isEmpty()) {
                        ToastUtil.show(this, "密码不能为空");
                    } else {
                        loginPresenter.login();
                    }
                } else {
                    ToastUtil.show(this, "请检查网络设置");
                }
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_login;
    }

    @Override
    public void showLoginSuccess(int code, String msg) {
        if (code == 200) {
            ToastUtil.show(this, msg);
            startActivity(MainActivity.class);
        } else {
            ToastUtil.show(this, msg);
            LogUtil.d("TAG", msg);
        }
    }

    @Override
    public void showLoginError(Exception e) {
        ToastUtil.show(this, e.getMessage());
        LogUtil.d("TAG", e.getMessage());
    }

    @Override
    public String getLgUsername() {
        return et_login_studentID.getText().toString();
    }

    @Override
    public String getLgPassword() {
        return RSAUtil.base64Encrypted(et_login_password.getText().toString());
    }

    @Override
    public void showLoading() {
        loadingDialog.show();
    }

    @Override
    public void hideLoading() {
        loadingDialog.dismiss();
    }

    /**
     * 初始化 LoadDialog
     */
    public void initLoadDialog() {
        loadingDialog = new LoadingDialog(mContext, R.style.loading_dialog);
        // 不能自己取消
        loadingDialog.setCancelable(false);
        loadingDialog.initDialog("登陆中");
    }

}
