package com.example.tvs.healthdisqus;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by tvs on 2/6/2017.
 */

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {



    Context context;

    public Adapter(Context context){
        this.context=context;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_model,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {




    }

    @Override
    public int getItemCount() {
        return 10;
    }

     public class MyViewHolder extends RecyclerView.ViewHolder{

         public MyViewHolder(View itemView) {
             super(itemView);

             itemView.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {

                     FragmentManager fm=((MainActivity)context).getSupportFragmentManager();
                     FragmentTransaction ft=fm.beginTransaction();
                     Category_fragment2 category_fragment=new Category_fragment2();
                     ft.replace(R.id.layout_to_replace,category_fragment);
                     ft.addToBackStack(null);
                     ft.commit();


                 }
             });

         }
     }



}
