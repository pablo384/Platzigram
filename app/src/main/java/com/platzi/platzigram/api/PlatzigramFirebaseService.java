package com.platzi.platzigram.api;

import retrofit2.Call;
import retrofit2.http.GET;


public interface PlatzigramFirebaseService {

    @GET("post.json")
    Call<PostResponse> getPostList();
}
