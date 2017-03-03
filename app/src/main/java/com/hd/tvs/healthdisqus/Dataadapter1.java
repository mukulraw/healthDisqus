package com.hd.tvs.healthdisqus;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import topicPOJO.Topic;

public class Dataadapter1 extends RecyclerView.Adapter<Dataadapter1.MyViewHolder> {

    Context context;
    List<Topic> list = new ArrayList<>();

    public Dataadapter1(Context context , List<Topic> list){
        this.context=context;
        this.list = list;
    }


    public void setGridData(List<Topic> list)
    {
        this.list = list;
        notifyDataSetChanged();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_models, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        final Topic item = list.get(position);


        holder.name.setText(item.getUserName());
        holder.title.setText(item.getTopicTitle());
        holder.desc.setText(Html.fromHtml(item.getTopicDetail().get(0).getDescription()));
        holder.count.setText(item.getTopicDetail().get(0).getTotalReply());

        if (Objects.equals(item.getIsBookmark(), "true"))
        {
            holder.star.setBackground(context.getResources().getDrawable(R.drawable.star_yellow));
        }
        else if (Objects.equals(item.getIsBookmark(), "false"))
        {
            holder.star.setBackground(context.getResources().getDrawable(R.drawable.star));
        }


        holder.star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FragmentManager fm = ((MainActivity)context).getSupportFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                Comments category_fragment = new Comments();

                Bundle b = new Bundle();
                b.putString("catid" , item.getCatId());
                b.putString("topicid" , item.getTopicId());
                category_fragment.setArguments(b);

                ft.replace(R.id.layout_to_replace,category_fragment);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name , title , desc , count;
        ImageButton star;

        public MyViewHolder(View itemView) {
            super(itemView);

            name = (TextView)itemView.findViewById(R.id.name);
            count = (TextView)itemView.findViewById(R.id.count);
            title = (TextView)itemView.findViewById(R.id.title);
            desc = (TextView)itemView.findViewById(R.id.desc);
            star = (ImageButton)itemView.findViewById(R.id.bookmark);

        }
    }
}
