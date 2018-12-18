package com.app.microyang.view;

import com.app.microyang.bean.UserBean;

public interface ILoginView {

        void showLoginSuccess(int code, String msg);

        void showLoginError(Exception e);

        //得到用户填写的信息
        String getLgUsername();

        String getLgPassword();

        //显示和隐藏加载圈
        void showLoading();

        void hideLoading();

        void showUserInfo(UserBean userBean);

}
