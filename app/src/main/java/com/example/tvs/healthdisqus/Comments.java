package com.example.tvs.healthdisqus;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;

import java.util.ArrayList;
import java.util.List;

import commentPOJO.TopicDetail;
import commentPOJO.topicBean;
import interfaces.allAPIs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class Comments extends Fragment {

    RecyclerView grid;
    GridLayoutManager manager;
    List<TopicDetail> list;
    CommentAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment , container , false);



        grid = (RecyclerView)view.findViewById(R.id.recycler);

        list = new ArrayList<>();

        manager = new GridLayoutManager(getContext() , 1);

        adapter = new CommentAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.healthdisqus.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<topicBean> call = cr.getComments(getArguments().getString("catid") , getArguments().getString("topicid"));


        call.enqueue(new Callback<topicBean>() {
            @Override
            public void onResponse(Call<topicBean> call, Response<topicBean> response) {

                list = response.body().getTopicDetail();

                adapter.setGridData(list);

            }

            @Override
            public void onFailure(Call<topicBean> call, Throwable t) {

            }
        });


        return view;
    }
}
