package com.app.microyang.presenter;

import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.ILoginModel;
import com.app.microyang.model.impl.LoginModelImpl;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.view.ILoginView;

public class LoginPresenter {

    private ILoginView iLoginView;

    private ILoginModel iLoginModel;

    public LoginPresenter(ILoginView iLoginView) {
        this.iLoginView = iLoginView;
        iLoginModel = new LoginModelImpl();
    }

    public void login() {
        iLoginView.showLoading();
        iLoginModel.getLogin(iLoginView.getLgUsername(), iLoginView.getLgPassword(), new OnNetListener<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                int code = userBean.getCode();
                String msg = userBean.getMsg();
                LogUtil.d("TAG", userBean.getAuthc_token());
                iLoginView.showLoginSuccess(code, msg);
                iLoginView.hideLoading();
            }

            @Override
            public void onFailed(Exception e) {
                iLoginView.showLoginError(e);
                iLoginView.hideLoading();
            }
        });
    }

}
