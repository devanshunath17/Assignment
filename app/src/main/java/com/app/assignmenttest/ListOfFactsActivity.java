package com.app.assignmenttest;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.assignmenttest.adapter.AdapterofFactsActivity;
import com.app.assignmenttest.model.DescOfFacts;
import com.app.assignmenttest.model.NameOfFacts;
import com.app.assignmenttest.retrofit.ApiClient;
import com.app.assignmenttest.retrofit.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListOfFactsActivity extends AppCompatActivity {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<DescOfFacts> factsList = new ArrayList<DescOfFacts>();
    private AdapterofFactsActivity mAdapter;
    private ProgressBar progress;
    private ActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_list);
        inItView();
        currencyList();

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // implement Handler to wait for 3 seconds and then update UI means update value of TextView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // cancle the Visual indication of a refresh
                        swipeRefreshLayout.setRefreshing(false);

                        currencyList();
                    }
                }, 3000);
            }
        });
    }


    /*
    * here we set the Id
    * */
    private void inItView() {

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
        recyclerView = (RecyclerView) findViewById(R.id.factsList);
        progress = (ProgressBar) findViewById(R.id.progress);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();
    }



/*Here we call the Retrofit Api (GET) for geting the List of Facts.
* */

    private void currencyList() {
        progress.setVisibility(View.VISIBLE);
        ApiService apiService = ApiClient.getClient().create(ApiService.class);
        Call<NameOfFacts> response = apiService.getFacts();
        response.enqueue(new Callback<NameOfFacts>() {
            @Override
            public void onResponse(Call<NameOfFacts> call, Response<NameOfFacts> response) {
                progress.setVisibility(View.GONE);
                actionBar.setTitle(response.body().getTitle());
                factsList.clear();
                factsList = response.body().getRows();
                mAdapter = new AdapterofFactsActivity(ListOfFactsActivity.this, factsList);
                RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(mLayoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                recyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<NameOfFacts> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(ListOfFactsActivity.this, getResources().getString(R.string.error), Toast.LENGTH_LONG).show();

            }
        });
    }
}
