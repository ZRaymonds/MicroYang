package com.app.microyang.model.impl;


import com.app.microyang.bean.UserBean;
import com.app.microyang.callBack.OnNetListener;
import com.app.microyang.model.ILoginModel;
import com.app.microyang.network.ServerApi;
import com.app.microyang.utils.Base64Util;
import com.app.microyang.utils.LogUtil;
import com.app.microyang.utils.RSAUtil;
import com.app.microyang.utils.RetrofitHelper;

import java.security.KeyPair;
import java.security.interfaces.RSAPublicKey;
import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class LoginModelImpl implements ILoginModel {

    Map<String, String> map = new HashMap<>();

    @Override
    public void getLogin(String username, String password, final OnNetListener<UserBean> onNetListener) {
        map.put("username", username);
        map.put("password", password);
        ServerApi serverApi = RetrofitHelper.getServerApi();
        serverApi.login(map)
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
