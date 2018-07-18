package com.app.assignmenttest.Activity;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.app.assignmenttest.Fragment.ListOfFactsFragment;
import com.app.assignmenttest.Presenter.SetTitle;
import com.app.assignmenttest.R;


/**
 * Created by Devanshu Nath Tripathi on 17/7/18.
 */

public class ListOfFactsActivity extends AppCompatActivity implements SetTitle {

    private ActionBar actionBar;
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

    }


    @Override
    public void onTitle(String Title) {
        actionBar.setTitle(Title);
    }
}
