package com.wasperstore.andelachallenge.apiData;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Habeex on 4/20/2017.
 */

public class Client {
    // get the client base url
    public static final String BASE_URL = "https://api.github.com";
    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
