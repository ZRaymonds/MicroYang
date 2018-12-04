package com.app.microyang.network;


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

    @GET("http://api01.idataapi.cn:8000/news/qihoo?kw=%E6%A0%A1%E5%9B%AD&site=qq.com&apikey=rVdPWU1LZzfjfQBn3iKQzzdUHum1nNao9qO0TqYsKITRbyvUYjeI11VpUmZdj07J")
    Observable<NewsBean> getNews();


}
