package com.hd.tvs.healthdisqus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import catPOJO.Category;


public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {


    Context context;
    List<Category> list = new ArrayList<>();

    public Adapter(Context context , List<Category> list){
        this.context=context;
        this.list = list;
    }


    public void setGridData(List<Category> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_model,parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {


        final Category item = list.get(position);

        holder.name.setText(item.getCatName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int c = Integer.parseInt(item.getSubcatCount());

                if (c>0)
                {
                    FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Category_fragment category_fragment = new Category_fragment();

                    Bundle b = new Bundle();
                    b.putString("id" , item.getCatId());
                    b.putString("name" , item.getCatName());
                    category_fragment.setArguments(b);

                    ft.replace(R.id.layout_to_replace,category_fragment);
                    ft.addToBackStack(null);
                    ft.commit();
                }
                else
                {

                    FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    Fragment_category3 category_fragment = new Fragment_category3();

                    Bundle b = new Bundle();
                    b.putString("id" , item.getCatId());

                    b.putString("name" , item.getCatName());

                    category_fragment.setArguments(b);

                    ft.replace(R.id.layout_to_replace,category_fragment);
                    ft.addToBackStack(null);
                    ft.commit();

                }




            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

     public class MyViewHolder extends RecyclerView.ViewHolder{

         TextView name;

         public MyViewHolder(View itemView) {
             super(itemView);

             name = (TextView)itemView.findViewById(R.id.name);



         }
     }



}
