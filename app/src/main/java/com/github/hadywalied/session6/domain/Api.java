package com.github.hadywalied.session6.domain;

import com.squareup.moshi.Moshi;
import com.squareup.moshi.Moshi.Builder;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class Api {

    // Trailing slash is needed
//    private final String BASE_URL =;


    private Moshi moshi = new Moshi.Builder().build();
    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl( "https://api.github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build();

    public ServiceInterface getService() {
        return retrofit.create(ServiceInterface.class);
    }


}
