package com.example.tvs.healthdisqus;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tvs on 2/7/2017.
 */

public class Dataadapter extends RecyclerView.Adapter<Dataadapter.MyviewHolder> {
    Context context;

    public Dataadapter(Context context){
        this.context=context;
    }


    @Override
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_model, parent, false);
        return new MyviewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 15;
    }

    public class MyviewHolder extends RecyclerView.ViewHolder{

        public MyviewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    FragmentManager fm=((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft=fm.beginTransaction();
                    Fragment_category3 fragment_category3=new Fragment_category3();
                    ft.replace(R.id.layout_to_replace,fragment_category3);
                    ft.addToBackStack(null);
                    ft.commit();


                }
            });
        }
    }
}
