package com.app.microyang.network;


import com.app.microyang.bean.MatchBean;
import com.app.microyang.bean.NewsBean;
import com.app.microyang.bean.UserBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import rx.Observable;

public interface ServerApi {

    @FormUrlEncoded
    @POST(Api.REGISTER)
    Observable<UserBean> register(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST(Api.LOGIN)
    Observable<UserBean> login(@FieldMap Map<String, String> map);

    @GET(Api.NEWS)
    Observable<NewsBean> getNews();

    @POST(Api.LOGOUT)
    Observable<UserBean> logout();

    @GET(Api.MATCHLIST)
    Observable<MatchBean> getMatch();

}
