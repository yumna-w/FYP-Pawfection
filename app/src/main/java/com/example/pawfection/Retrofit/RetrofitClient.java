package com.example.pawfection.Retrofit;

import java.net.InetAddress;
import java.net.UnknownHostException;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {

    private static Retrofit instance;

    public static Retrofit getInstance() {

        /*InetAddress inetAddress = null;
        try {
            inetAddress = InetAddress.getLocalHost();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }

        String baseURL = "http://" + inetAddress.getHostAddress() + ":3000/";*/

        if (instance == null) {
            instance = new Retrofit.Builder()
                    .baseUrl("http://192.168.18.13:3000/")
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                    .build();
        }
        return instance;
    }
}
