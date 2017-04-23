package com.wasperstore.andelachallenge.apiData;

import com.wasperstore.andelachallenge.model.ItemResult;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Habeex on 4/20/2017.
 */

public interface Service {
    //search for user's that write java language which their location is Lagos
    @GET("/search/users?q=language:java+location:lagos")
    Call<ItemResult> getItems();
}
