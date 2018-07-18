package com.app.assignmenttest.Fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.app.assignmenttest.Activity.ListOfFactsActivity;
import com.app.assignmenttest.Adapter.AdapterofFactsActivity;
import com.app.assignmenttest.Model.DescOfFacts;
import com.app.assignmenttest.Presenter.GetDataContract;
import com.app.assignmenttest.Presenter.Presenter;
import com.app.assignmenttest.R;

import java.util.ArrayList;

/**
 * Created by Devanshu Nath Tripathi on 17/7/18.
 */

public class ListOfFactsFragment extends Fragment implements GetDataContract.View {
    private View view;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView textView;
    private RecyclerView recyclerView;
    private ArrayList<DescOfFacts> factsList = new ArrayList<DescOfFacts>();
    private AdapterofFactsActivity mAdapter;
    private ProgressBar progress;
    private ActionBar actionBar;
    private Presenter mPresenter;
    Activity mactivity;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_list, container, false);
        inItView(view);
        return view;
    }



    /*
   * here we define  the Id
   * */
    private void inItView(View view) {

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.simpleSwipeRefreshLayout);
        recyclerView = (RecyclerView) view.findViewById(R.id.factsList);
        progress = (ProgressBar) view.findViewById(R.id.progress);
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);

        progress.setVisibility(View.VISIBLE);
        mPresenter = new Presenter(this);
        mPresenter.getDataFromURL(getActivity(), "");

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                // implement Handler to wait for 3 seconds and then update UI means update value of TextView
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // cancle the Visual indication of a refresh
                        swipeRefreshLayout.setRefreshing(false);

                        mPresenter = new Presenter(ListOfFactsFragment.this);
                        mPresenter.getDataFromURL(getActivity(), "");
                    }
                }, 3000);
            }
        });


    }

/*
* After getting the success result
* */
    @Override
    public void onGetDataSuccess(String message, ArrayList<DescOfFacts> list, String title) {
        progress.setVisibility(View.GONE);
        factsList.clear();
        factsList = list;
        mAdapter = new AdapterofFactsActivity(getActivity(), factsList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
        ((ListOfFactsActivity)getActivity()).onTitle(title);

    }
    /*
    * After getting the Failure result
    * */
    @Override
    public void onGetDataFailure(String message) {
        progress.setVisibility(View.GONE);
        Toast.makeText(getActivity(), message, Toast.LENGTH_LONG).show();

    }
}
