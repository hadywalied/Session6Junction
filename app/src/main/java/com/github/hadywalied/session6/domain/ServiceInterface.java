package com.github.hadywalied.session6.domain;

import com.github.hadywalied.session6.model.Repos;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ServiceInterface {

    @GET("users/{username}/repos")
    Call<List<Repos>> getUser(@Path("username") String username);

}
