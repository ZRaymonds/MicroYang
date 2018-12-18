package com.app.microyang.presenter;

import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.ILoginModel;
import com.app.microyang.model.impl.LoginModelImpl;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.view.ILoginView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class LoginPresenter {

    private ILoginView iLoginView;

    private ILoginModel iLoginModel;

    private UserBean.DataBean dataBean;

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
                iLoginView.showLoginSuccess(code, msg);
                iLoginView.hideLoading();
                iLoginView.showUserInfo(userBean);
                sendEvent(userBean);
                LogUtil.d("userInfo", userBean.toString());
            }

            @Override
            public void onFailed(Exception e) {
                iLoginView.showLoginError(e);
                iLoginView.hideLoading();
                LogUtil.e("error",e.getMessage());
            }
        });
    }

    private void sendEvent(UserBean userBean) {
        dataBean = new UserBean.DataBean();
        dataBean.setUsername(iLoginView.getLgUsername());
        dataBean.setSex(userBean.getData().getSex());
        dataBean.setSchool(userBean.getData().getSchool());
        dataBean.setClasses(userBean.getData().getClasses());
        dataBean.setStudent_id(userBean.getData().getStudent_id());
        dataBean.setSystem_type(userBean.getData().getSystem_type());
        EventBus.getDefault().postSticky(dataBean);
    }

}
