package com.app.microyang.model.impl;

import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.IRegisterModel;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class RegisterModelImpl implements IRegisterModel {

    Map<String, String> map = new HashMap<>();

    @Override
    public void getRegister(String username, String studentId, String sex, String school, String systemType, String classes, String password, final OnNetListener<UserBean> onNetListener) {
        map.put("username", username);
        map.put("studentId", studentId);
        map.put("sex", sex);
        map.put("school", school);
        map.put("systemType", systemType);
        map.put("classes", classes);
        map.put("password", password);
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.register(map)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<UserBean>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        onNetListener.onFailed((Exception) e);
                    }

                    @Override
                    public void onNext(UserBean userBean) {
                        onNetListener.onSuccess(userBean);
                    }
                });
    }
}
