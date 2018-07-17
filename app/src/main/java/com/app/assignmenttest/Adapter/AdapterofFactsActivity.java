package com.app.assignmenttest.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.assignmenttest.R;
import com.app.assignmenttest.Model.DescOfFacts;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by Devanshu Nath Tripathi on 17/7/18.
 */

public class AdapterofFactsActivity extends RecyclerView.Adapter<AdapterofFactsActivity.MyViewHolder> {

    private ArrayList<DescOfFacts> factsList;
    private Context mcontaxt;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView title, txtdescription;
        private ImageView list_image;


        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.txttitle);
            list_image = (ImageView) view.findViewById(R.id.list_image);
            txtdescription = (TextView) view.findViewById(R.id.txtdescription);

        }
    }


    public AdapterofFactsActivity(Context mcontaxt, ArrayList<DescOfFacts> factslist) {
        this.factsList = factslist;
        this.mcontaxt = mcontaxt;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DescOfFacts facts = factsList.get(position);
        holder.title.setText(facts.getTitle());
        if (!TextUtils.isEmpty(facts.getDescription()))
            holder.txtdescription.setText(facts.getDescription().toString().trim());

        Glide.with(mcontaxt).load(facts.getImageHref())
                .thumbnail(0.5f)
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.list_image);

    }

    @Override
    public int getItemCount() {
        return factsList.size();
    }
}
