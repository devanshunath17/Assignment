package com.app.assignmenttest.Presenter;

import android.content.Context;
import android.util.Log;

import com.app.assignmenttest.Model.DescOfFacts;
import com.app.assignmenttest.Model.NameOfFacts;
import com.app.assignmenttest.Retrofit.ApiClient;
import com.app.assignmenttest.Retrofit.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by Devanshu Nath Tripathi on 17/7/18.
 */

public class Intractor implements GetDataContract.Interactor {
    private GetDataContract.onGetDataListener mOnGetDatalistener;
    ArrayList<DescOfFacts> factsList = new ArrayList<>();


    public Intractor(GetDataContract.onGetDataListener mOnGetDatalistener) {
        this.mOnGetDatalistener = mOnGetDatalistener;
    }

    @Override
    public void initRetrofitCall(Context context, String url) {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<NameOfFacts> response = apiService.getFacts();
        response.enqueue(new Callback<NameOfFacts>() {
            @Override
            public void onResponse(Call<NameOfFacts> call, Response<NameOfFacts> response) {

                factsList = response.body().getRows();
                Log.d("Data", "Refreshed");
                mOnGetDatalistener.onSuccess("List Size: " + factsList.size(), factsList, response.body().getTitle());


            }

            @Override
            public void onFailure(Call<NameOfFacts> call, Throwable t) {
                Log.v("Error", t.getMessage());
                mOnGetDatalistener.onFailure(t.getMessage());
            }
        });


    }
}
