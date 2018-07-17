package com.app.assignmenttest.Presenter;

import android.content.Context;

import com.app.assignmenttest.Model.DescOfFacts;

import java.util.ArrayList;



/**
 * Created by Devanshu Nath Tripathi on 17/7/18.
 */

public interface GetDataContract {
    interface View{
        void onGetDataSuccess(String message, ArrayList<DescOfFacts> list, String title);
        void onGetDataFailure(String message);
    }
    interface Presenter{
        void getDataFromURL(Context context, String url);
    }
    interface Interactor{
        void initRetrofitCall(Context context, String url);

    }
    interface onGetDataListener{
        void onSuccess(String message, ArrayList<DescOfFacts> list, String title);
        void onFailure(String message);
    }
}
