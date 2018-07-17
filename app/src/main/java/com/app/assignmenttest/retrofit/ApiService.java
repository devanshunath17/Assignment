package com.app.assignmenttest.retrofit;


import com.app.assignmenttest.model.NameOfFacts;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by oem on 13/6/17.
 */

public interface ApiService {

    @GET("facts.json")
    Call<NameOfFacts> getFacts();
}
