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

import interfaces.allAPIs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import topicPOJO.Topic;
import topicPOJO.topicBean;


public class Fragment_category3 extends Fragment {
    RecyclerView recyclerView;
    Dataadapter1 dataadapter1;
    ProgressBar progress;
    List<Topic> list;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment , container , false);

        list = new ArrayList<>();

        progress = (ProgressBar)view.findViewById(R.id.progress);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

        dataadapter1 = new Dataadapter1(getContext() , list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(dataadapter1);


        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://hellthnu.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<topicBean> call = cr.fetchTopics(getArguments().getString("id"));

        call.enqueue(new Callback<topicBean>() {
            @Override
            public void onResponse(Call<topicBean> call, Response<topicBean> response) {

                list = response.body().getTopic();

                dataadapter1.setGridData(list);

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<topicBean> call, Throwable t) {

            }
        });



        return view;
    }
}
