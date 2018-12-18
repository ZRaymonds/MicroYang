package com.app.microyang.view.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.microyang.R;
import com.app.microyang.base.BaseActivity;
import com.app.microyang.presenter.RegisterPresenter;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.ToastUtil;
import com.app.microyang.utils.VerifyUtil;
import com.app.microyang.view.IRegisterView;
import com.app.microyang.widget.LoadingDialog;

import butterknife.BindView;
import butterknife.OnClick;


public class RegisterActivity extends BaseActivity implements IRegisterView {

    @BindView(R.id.tv_haveAccount)
    TextView tv_haveAccount;

    @BindView(R.id.et_register_username)
    EditText et_register_username;

    @BindView(R.id.et_register_studentID)
    EditText et_register_studentID;

    @BindView(R.id.et_register_sex)
    EditText et_register_sex;

    @BindView(R.id.et_register_school)
    EditText et_register_school;

    @BindView(R.id.et_register_department)
    EditText et_register_department;

    @BindView(R.id.et_register_class)
    EditText et_register_class;

    @BindView(R.id.et_register_password)
    EditText et_register_password;

    @BindView(R.id.btn_register)
    Button btn_register;

    private Context mContext;

    private LoadingDialog loadingDialog;

    private RegisterPresenter registerPresenter;

    @Override
    protected void initData(Bundle savedInstanceState) {
        mContext = this;
        registerPresenter = new RegisterPresenter(this);
        initLoadDialog();
    }

    @OnClick({R.id.tv_haveAccount, R.id.btn_register})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_haveAccount:
                startActivity(LoginActivity.class);
                overridePendingTransition(R.anim.right_out, R.anim.left_in);
                break;
            case R.id.btn_register:
                if (VerifyUtil.isConnect(mContext)) {
                    if (getRgUsername().isEmpty()) {
                        ToastUtil.show(this, "用户名不能为空");
                    } else if (getRgStudentId().isEmpty()) {
                        ToastUtil.show(this, "学号不能为空");
                    } else if (getRgSex().isEmpty()) {
                        ToastUtil.show(this, "性别不能为空");
                    } else if (!getRgSex().equals("男") && !getRgSex().equals("女")) {
                        ToastUtil.show(this, "性别只能填男或女");
                    } else if (getRgSchool().isEmpty()) {
                        ToastUtil.show(this, "学校不能为空");
                    } else if (getRgSystemType().isEmpty()) {
                        ToastUtil.show(this, "系别不能为空");
                    } else if (getRgClasses().isEmpty()) {
                        ToastUtil.show(this, "班级不能为空");
                    } else if (getRgPassword().isEmpty()) {
                        ToastUtil.show(this, "密码不能为空");
                    } else {
                        registerPresenter.register();
                    }
                } else {
                    ToastUtil.show(this, "请检查网络设置");
                }
                break;
        }
    }

    @Override
    public int getLayoutResId() {
        return R.layout.activity_register;
    }

    @Override
    public void showRegisterSuccess(int code, String msg) {
        if (code == 200) {
            ToastUtil.show(this, msg);
            startActivity(LoginActivity.class);
        } else {
            ToastUtil.show(this, msg);
        }
    }

    @Override
    public void showRegisterError(Exception e) {
        ToastUtil.show(this, e.getMessage());
        LogUtil.e("TAG", e.getMessage());
    }

    @Override
    public String getRgUsername() {
        return et_register_username.getText().toString();
    }

    @Override
    public String getRgStudentId() {
        return et_register_studentID.getText().toString();
    }

    @Override
    public String getRgSex() {
        return et_register_sex.getText().toString();
    }

    @Override
    public String getRgSchool() {
        return et_register_school.getText().toString();
    }

    @Override
    public String getRgSystemType() {
        return et_register_department.getText().toString();
    }

    @Override
    public String getRgClasses() {
        return et_register_class.getText().toString();
    }

    @Override
    public String getRgPassword() {
        return et_register_password.getText().toString();
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
        loadingDialog.initDialog("注册中");
    }
}
