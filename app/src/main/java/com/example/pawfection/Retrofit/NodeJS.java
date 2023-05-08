package com.example.pawfection.Retrofit;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface NodeJS {
    @POST("register")
    @FormUrlEncoded
    Observable<String> registerUser(@Field("emailAddress") String emailAddress,
                                    @Field("fullName") String fullName,
                                    @Field("password") String password,
                                    @Field("cnic") String cnic,
                                    @Field("accountType") String accountType);

    @POST("login")
    @FormUrlEncoded
    Observable<String> loginUser(@Field("emailAddress") String emailAddress,
                                 @Field("password") String password);

    @POST("create-new-alert")
    @FormUrlEncoded
    Observable<String> createNewAlert(@Field("petName") String petName,
                                      @Field("lastSeen") String lastSeen,
                                      @Field("contactNumber") String contactNumber,
                                      @Field("users_id") int users_id);

    @GET("get-alerts")
    Observable<String> getAlerts();
}
