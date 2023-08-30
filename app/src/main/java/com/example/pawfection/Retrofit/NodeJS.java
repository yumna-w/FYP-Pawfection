package com.example.pawfection.Retrofit;

import io.reactivex.rxjava3.core.Observable;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

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

    @GET("get-pets")
    Observable<String> getPets(@Query("users_id") int users_id);

    @POST("create-new-pet")
    @FormUrlEncoded
    Observable<String> createNewPet(@Field("users_id") int users_id,
                                    @Field("petName") String petName,
                                    @Field("type") String type,
                                    @Field("age") int age,
                                    @Field("breed") String breed,
                                    @Field("color") String color,
                                    @Field("gender") String gender);

    @POST("create-vet-clinic")
    @FormUrlEncoded
    Observable<String> createVetClinic(@Field("users_id") int users_id,
                                       @Field("qualification") String qualification,
                                       @Field("address") String address,
                                       @Field("area") String area,
                                       @Field("days") String days);

    @GET("check-set-up-form")
    Observable<String> checkSetUpForm(@Query("users_id") int users_id);

    @GET("get-vets")
    Observable<String> getVets();

    @GET("get-name")
    Observable<String> getName(@Query("users_id") int users_id);

    @GET("get-days")
    Observable<String> getDays(@Query("users_id") int users_id);

}
