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

/**
 * Created by tvs on 2/7/2017.
 */

public class Fragment_category3 extends Fragment {
    RecyclerView recyclerView;
    Dataadapter1 dataadapter1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.category_fragment,container,false);


        recyclerView=(RecyclerView)view.findViewById(R.id.recycler);
        dataadapter1 = new Dataadapter1(getContext());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(dataadapter1);
        return view;

    }
}
