package com.app.assignmenttest.job;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.app.assignmenttest.Retrofit.ApiClient;
import com.app.assignmenttest.Retrofit.ApiService;
import com.app.assignmenttest.Utils.DatabaseInitializer;
import com.app.assignmenttest.Utils.NetworkException;
import com.app.assignmenttest.Utils.Preference;
import com.app.assignmenttest.Utils.StaticData;
import com.app.assignmenttest.database.AppDatabase;
import com.app.assignmenttest.entity.ListItem;
import com.app.assignmenttest.entity.NameOfFacts;
import com.birbit.android.jobqueue.Job;
import com.birbit.android.jobqueue.Params;
import com.birbit.android.jobqueue.RetryConstraint;

import java.util.ArrayList;


import retrofit2.Call;

/**
 * Created by Devanshu 26 july 2018
 */

public class FetchListFromserver extends Job {
    private boolean reload;
    private boolean loadMore;

    transient Context context;
    public static final String TAG = FetchListFromserver.class.getCanonicalName();

    public FetchListFromserver(Context ctx,boolean reload, boolean loadMore) {
        super(new Params(JobConstants.PRIORITY_NORMAL)
                .requireNetwork()
                .singleInstanceBy(TAG)
                .addTags(TAG)
        );
        this.reload = reload;
        this.loadMore = loadMore;
        this.context = ctx;


    }
    @Override
    public void onAdded() {
    }
    @Override
    public void onRun() throws Throwable {
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<NameOfFacts> response1 = apiService.getFacts();
        NameOfFacts response = response1.execute().body();

        Preference.getInstance(context).put(StaticData.TITLE,response.getTitle().toString());
        ArrayList<ListItem> factsList = response.getRows();

        AppDatabase db=   AppDatabase.getAppDatabase(context);
        DatabaseInitializer obj = new DatabaseInitializer();
        obj.deleteData(db);
        obj.populateAsync(db,factsList);




    }
    @Override
    protected void onCancel(int cancelReason, @Nullable Throwable throwable) {
     //   EventBus.getDefault().post(new GetCuisineEvent(0, "Request Cancel", reload, loadMore));
    }
    @Override
    protected RetryConstraint shouldReRunOnThrowable(@NonNull Throwable throwable, int runCount, int maxRunCount) {
        if (throwable instanceof NetworkException) {

            NetworkException error = (NetworkException) throwable;

            int statusCode = error.getResponse().raw().code();

            if (statusCode >= 400 && statusCode < 500) {
                return RetryConstraint.CANCEL;
            }
        }

        return RetryConstraint.RETRY;
    }
}