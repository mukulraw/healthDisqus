package com.example.tvs.healthdisqus;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import addTopicPOJO.addTopicBean;
import bookmarkPOJO.addBean;
import interfaces.allAPIs;
import removePOJO.removeBean;
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
    FloatingActionButton add;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container , Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment2 , container , false);

        ((MainActivity) getActivity()).toolbar.setTitle(getArguments().getString("name"));
        ((MainActivity) getActivity()).toolbar.setTitleTextColor(Color.WHITE);

        list = new ArrayList<>();

        add = (FloatingActionButton)view.findViewById(R.id.add);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        recyclerView = (RecyclerView)view.findViewById(R.id.recycler);

        dataadapter1 = new Dataadapter1(getContext() , list);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());

        recyclerView.setLayoutManager(mLayoutManager);

        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(dataadapter1);





        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(getContext() , AddTopic.class);
                intent.putExtra("id" , getArguments().getString("id"));

                startActivity(intent);

            }
        });


        return view;
    }


    @Override
    public void onResume() {
        super.onResume();

        progress.setVisibility(View.VISIBLE);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.healthdisqus.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final allAPIs cr = retrofit.create(allAPIs.class);

        final bean b = (bean)getContext().getApplicationContext();

        Call<topicBean> call = cr.fetchTopics(getArguments().getString("id") , b.id);

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


    }

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
        public void onBindViewHolder(final MyViewHolder holder, int position) {

            holder.setIsRecyclable(false);

            final Topic item = list.get(position);


            holder.name.setText(item.getUserName());
            holder.title.setText(item.getTopicTitle());

            try {

                holder.desc.setText(Html.fromHtml(item.getTopicDetail().get(0).getDescription()));

            }catch (IndexOutOfBoundsException e)
            {
                e.printStackTrace();
            }


            //holder.count.setText(item.getTopicDetail().get(0).getTotalReply());

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


                    if (Objects.equals(item.getIsBookmark(), "false"))
                    {
                        progress.setVisibility(View.VISIBLE);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://www.healthdisqus.com/")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();


                        final allAPIs cr = retrofit.create(allAPIs.class);

                        final bean b = (bean)getContext().getApplicationContext();

                        Call<addBean> call = cr.addBookmark(b.id , item.getTopicId());

                        call.enqueue(new Callback<addBean>() {
                            @Override
                            public void onResponse(Call<addBean> call, Response<addBean> response) {

                                Call<topicBean> call2 = cr.fetchTopics(getArguments().getString("id") , b.id);

                                call2.enqueue(new Callback<topicBean>() {
                                    @Override
                                    public void onResponse(Call<topicBean> call, Response<topicBean> response) {

                                        list.clear();

                                        list = response.body().getTopic();

                                        dataadapter1.setGridData(list);

                                        progress.setVisibility(View.GONE);

                                    }

                                    @Override
                                    public void onFailure(Call<topicBean> call, Throwable t) {

                                    }
                                });

                            }

                            @Override
                            public void onFailure(Call<addBean> call, Throwable t) {

                            }
                        });

                    }
                    else if (Objects.equals(item.getIsBookmark(), "true"))
                    {
                        progress.setVisibility(View.VISIBLE);

                        Retrofit retrofit = new Retrofit.Builder()
                                .baseUrl("http://www.healthdisqus.com/")
                                .addConverterFactory(ScalarsConverterFactory.create())
                                .addConverterFactory(GsonConverterFactory.create())
                                .build();


                        final allAPIs cr = retrofit.create(allAPIs.class);

                        final bean b = (bean)getContext().getApplicationContext();

                        Call<removeBean> call = cr.removeBookmark(b.id , item.getTopicId());

                        call.enqueue(new Callback<removeBean>() {
                            @Override
                            public void onResponse(Call<removeBean> call, Response<removeBean> response) {

                                Call<topicBean> call2 = cr.fetchTopics(getArguments().getString("id") , b.id);

                                call2.enqueue(new Callback<topicBean>() {
                                    @Override
                                    public void onResponse(Call<topicBean> call, Response<topicBean> response) {

                                        list.clear();

                                        list = response.body().getTopic();

                                        dataadapter1.setGridData(list);

                                        progress.setVisibility(View.GONE);

                                    }

                                    @Override
                                    public void onFailure(Call<topicBean> call, Throwable t) {

                                    }
                                });

                            }

                            @Override
                            public void onFailure(Call<removeBean> call, Throwable t) {

                            }
                        });
                    }



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
                    b.putString("topic" , item.getTopicTitle());
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

}
