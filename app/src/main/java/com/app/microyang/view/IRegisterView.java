package com.app.microyang.view;

public interface IRegisterView {

    void showRegisterSuccess(int code,String msg);

    void showRegisterError(Exception e);

    String getRgUsername();

    String getRgStudentId();

    String getRgSex();

    String getRgSchool();

    String getRgSystemType();

    String getRgClasses();

    String getRgPassword();

    //显示和隐藏加载圈
    void showLoading();

    void hideLoading();

}
