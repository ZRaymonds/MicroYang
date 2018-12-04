package com.app.microyang.presenter;

import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.IRegisterModel;
import com.app.microyang.model.impl.RegisterModelImpl;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.view.IRegisterView;

public class RegisterPresenter {

    private IRegisterView iRegisterView;

    private IRegisterModel iRegisterModel;

    public RegisterPresenter(IRegisterView iRegisterView) {
        this.iRegisterView = iRegisterView;
        iRegisterModel = new RegisterModelImpl();
    }

    public void register() {
        iRegisterView.showLoading();
        iRegisterModel.getRegister(iRegisterView.getRgUsername(), iRegisterView.getRgStudentId(), iRegisterView.getRgSex(), iRegisterView.getRgSchool(), iRegisterView.getRgSystemType(), iRegisterView.getRgClasses(), iRegisterView.getRgPassword(), new OnNetListener<UserBean>() {
            @Override
            public void onSuccess(UserBean userBean) {
                int code = userBean.getCode();
                String msg = userBean.getMsg();
                iRegisterView.showRegisterSuccess(code, msg);
                iRegisterView.hideLoading();
            }

            @Override
            public void onFailed(Exception e) {
                iRegisterView.showRegisterError(e);
                iRegisterView.hideLoading();
            }
        });
    }

}
