package com.example.tvs.healthdisqus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

import catPOJO.Category;
import catPOJO.catBean;
import interfaces.allAPIs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Category_fragment extends Fragment {
    RecyclerView recyclerView;
    Adapter mAdapter;
    List<Category> list;
    ProgressBar progress;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment, container, false);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);

        list = new ArrayList<>();

        mAdapter = new Adapter(getContext() , list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        progress.setVisibility(View.VISIBLE);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://hellthnu.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<catBean> call = cr.fetchCat(getArguments().getString("id"));

        call.enqueue(new Callback<catBean>() {
            @Override
            public void onResponse(Call<catBean> call, Response<catBean> response) {

                list = response.body().getCategory();

                mAdapter.setGridData(list);

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<catBean> call, Throwable t) {

            }
        });


        return view;
    }
}
