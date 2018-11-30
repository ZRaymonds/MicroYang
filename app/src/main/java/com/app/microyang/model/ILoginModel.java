package com.app.microyang.model;

import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;

public interface ILoginModel {

    void getLogin(String student_id, String password, final OnNetListener<UserBean> onNetListener);

}
