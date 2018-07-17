package com.app.assignmenttest.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.IdRes;
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

import com.app.assignmenttest.Adapter.AdapterofFactsActivity;
import com.app.assignmenttest.Fragment.ListOfFactsFragment;
import com.app.assignmenttest.Model.DescOfFacts;
import com.app.assignmenttest.Model.NameOfFacts;
import com.app.assignmenttest.Presenter.GetDataContract;
import com.app.assignmenttest.Presenter.Presenter;
import com.app.assignmenttest.Presenter.SetTitle;
import com.app.assignmenttest.R;
import com.app.assignmenttest.Retrofit.ApiClient;
import com.app.assignmenttest.Retrofit.ApiService;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
/**
 * Created by Devanshu Nath Tripathi on 17/7/18.
 */


public class ListOfFactsActivity extends AppCompatActivity implements SetTitle {

    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<DescOfFacts> factsList = new ArrayList<DescOfFacts>();
    private AdapterofFactsActivity mAdapter;
    private ProgressBar progress;
    private ActionBar actionBar;
    private Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_list);
        //inItView();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBar = getSupportActionBar();

        ListOfFactsFragment myFragment = new ListOfFactsFragment();
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        replaceFragment(R.id.container, myFragment, ListOfFactsFragment.class.getSimpleName());
        transaction.commit();


    }
    protected void replaceFragment(@IdRes int container, Fragment fragment, String tag) {
        getFragmentManager().beginTransaction().replace(container, fragment, tag).addToBackStack(null).commit();
        // getFragmentManager().beginTransaction().replace(container, fragment,tag).commit();


    }

    /*
    * here we set the Id
    * */
    private void inItView() {

//        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.simpleSwipeRefreshLayout);
//        recyclerView = (RecyclerView) findViewById(R.id.factsList);
//        progress = (ProgressBar) findViewById(R.id.progress);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//        actionBar = getSupportActionBar();
//
//        mPresenter = new Presenter(this);
//        mPresenter.getDataFromURL(getApplicationContext(), "");
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


    @Override
    public void onTitle(String Title) {

        actionBar.setTitle(Title);
    }
}
