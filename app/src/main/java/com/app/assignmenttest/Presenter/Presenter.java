package com.app.assignmenttest.Presenter;

import android.content.Context;

import com.app.assignmenttest.Model.DescOfFacts;

import java.util.ArrayList;





/**
 * Created by Devanshu Nath Tripathi on 17/7/18.
 */

public class Presenter implements GetDataContract.Presenter, GetDataContract.onGetDataListener {
    private GetDataContract.View mGetDataView;
    private Intractor mIntractor;
    public Presenter(GetDataContract.View mGetDataView){
        this.mGetDataView = mGetDataView;
        mIntractor = new Intractor(this);
    }
    @Override
    public void getDataFromURL(Context context, String url) {
        mIntractor.initRetrofitCall(context,url);
    }

    @Override
    public void onSuccess(String message, ArrayList<DescOfFacts> allCountriesData,String title) {
        mGetDataView.onGetDataSuccess(message, allCountriesData ,title);
    }

    @Override
    public void onFailure(String message) {
        mGetDataView.onGetDataFailure(message);
    }
}
