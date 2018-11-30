package com.app.microyang.model.impl;

import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.ILoginModel;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.RetrofitHelper;

import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModelImpl implements ILoginModel {

//    Map<String,String> map = new HashMap<>();

    @Override
    public void getLogin(String student_id, String password, final OnNetListener<UserBean> onNetListener) {
//        map.put("studentid",student_id);
//        map.put("password",password);
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.login(Integer.valueOf(student_id),password)
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
