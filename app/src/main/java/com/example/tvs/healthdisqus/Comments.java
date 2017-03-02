package com.example.tvs.healthdisqus;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.rockerhieu.emojicon.EmojiconTextView;

import org.apache.commons.lang3.StringEscapeUtils;

import java.util.ArrayList;
import java.util.List;

import addPostPOJO.addPodtBean;
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
    FloatingActionButton comment;
    ProgressBar progress;
    TextView hide;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.category_fragment3 , container , false);

        ((MainActivity) getActivity()).toolbar.setTitle(getArguments().getString("topic"));
        ((MainActivity) getActivity()).toolbar.setTitleTextColor(Color.WHITE);

        comment = (FloatingActionButton)view.findViewById(R.id.comment);

        hide = (TextView)view.findViewById(R.id.hide);

        progress = (ProgressBar)view.findViewById(R.id.progress);

        grid = (RecyclerView)view.findViewById(R.id.recycler);

        list = new ArrayList<>();

        manager = new GridLayoutManager(getContext() , 1);

        adapter = new CommentAdapter(getContext() , list);

        grid.setAdapter(adapter);
        grid.setLayoutManager(manager);





        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =  new Intent(getContext() , AddPost.class);
                intent.putExtra("tid" , getArguments().getString("topicid"));
                intent.putExtra("cid" , getArguments().getString("catid"));
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


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<topicBean> call = cr.getComments(getArguments().getString("catid") , getArguments().getString("topicid"));


        call.enqueue(new Callback<topicBean>() {
            @Override
            public void onResponse(Call<topicBean> call, Response<topicBean> response) {

                list = response.body().getTopicDetail();

                adapter.setGridData(list);

                if (list.size() == 0)
                {
                    hide.setVisibility(View.VISIBLE);
                }
                else
                {
                    hide.setVisibility(View.GONE);
                }

                progress.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<topicBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
            }
        });


    }


    public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

        List<TopicDetail> list = new ArrayList<>();
        Context context;

        public CommentAdapter(Context context , List<TopicDetail> list)
        {
            this.context = context;
            this.list = list;
        }

        public void setGridData(List<TopicDetail> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.comment_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.setIsRecyclable(false);

            TopicDetail item = list.get(position);

            ImageLoader loader = ImageLoader.getInstance();

            loader.displayImage(item.getUserDetail().get(0).getUserProfile() , holder.image);

            holder.name.setText(item.getUserDetail().get(0).getUsername());
            holder.date.setText(item.getPostTime());
            holder.subject.setText(item.getSubject());

            holder.desc.setText(Html.fromHtml(StringEscapeUtils.unescapeJava(item.getDescription())));




        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            ImageView image;
            TextView name , date , subject;
            EmojiconTextView desc;

            public ViewHolder(View itemView) {
                super(itemView);

                image = (ImageView)itemView.findViewById(R.id.image);
                name = (TextView)itemView.findViewById(R.id.name);
                date = (TextView)itemView.findViewById(R.id.date);
                subject = (TextView)itemView.findViewById(R.id.subject);
                desc = (EmojiconTextView) itemView.findViewById(R.id.desc);

            }
        }

    }


}
