package com.app.microyang.model;

import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;

public interface IRegisterModel {

    void getRegister(String username, String studentId, String sex, String school, String systemType, String classes, String password, final OnNetListener<UserBean> onNetListener);

}
