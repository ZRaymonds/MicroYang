package com.app.microyang.network;


import com.app.microyang.bean.UserBean;

import java.util.Map;

import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

public interface ServerApi {

    @FormUrlEncoded
    @POST(Api.REGISTER)
    Observable<UserBean> register(@FieldMap Map<String, String> map);

    @GET(Api.LOGIN)
    Observable<UserBean> login(@Query("studentid") int student_id, @Query("password") String password);

}
