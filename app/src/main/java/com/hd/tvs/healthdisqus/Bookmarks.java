package com.hd.tvs.healthdisqus;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import deleteBookPOJO.deleteBookBean;
import interfaces.allAPIs;
import mBookmarkPOJO.Managebookmark;
import mBookmarkPOJO.manageBookmarkBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Bookmarks extends AppCompatActivity {

    RecyclerView grid;
    GridLayoutManager manager;

    List<Managebookmark> list;

    Toolbar toolbar;

    BookAdapter adapter;

    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setTitle("Bookmarks");

        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        grid = (RecyclerView)findViewById(R.id.grid);
        manager = new GridLayoutManager(this , 1);
        list = new ArrayList<>();

        adapter = new BookAdapter(this , list);

        grid.setLayoutManager(manager);
        grid.setAdapter(adapter);


        progress = (ProgressBar)findViewById(R.id.progress);


    }


    @Override
    protected void onResume() {
        super.onResume();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.healthdisqus.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        final allAPIs cr = retrofit.create(allAPIs.class);

        progress.setVisibility(View.VISIBLE);

        bean b = (bean)getApplicationContext();


        Call<manageBookmarkBean> call = cr.bookmark(b.id);

        call.enqueue(new Callback<manageBookmarkBean>() {
            @Override
            public void onResponse(Call<manageBookmarkBean> call, Response<manageBookmarkBean> response) {

                list = response.body().getManagebookmark();

                adapter.setGridData(list);

                progress.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<manageBookmarkBean> call, Throwable t) {

            }
        });


    }

    public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder>
    {

        Context context;
        List<Managebookmark> list = new ArrayList<>();


        public BookAdapter(Context context , List<Managebookmark> list)
        {
            this.context = context;
            this.list = list;
        }


        public void setGridData(List<Managebookmark> list)
        {
            this.list = list;
            notifyDataSetChanged();
        }


        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view = inflater.inflate(R.layout.book_model , parent , false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            holder.setIsRecyclable(false);

            final Managebookmark item = list.get(position);

            holder.name.setText(item.getUserName());
            holder.desc.setText(item.getTopicTitle());


            holder.delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://www.healthdisqus.com/")
                            .addConverterFactory(ScalarsConverterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();


                    final allAPIs cr = retrofit.create(allAPIs.class);

                    progress.setVisibility(View.VISIBLE);

                    final bean b = (bean)getApplicationContext();

                    Call<deleteBookBean> call = cr.deleteBook(b.id , item.getTopicId());

                    call.enqueue(new Callback<deleteBookBean>() {
                        @Override
                        public void onResponse(Call<deleteBookBean> call, Response<deleteBookBean> response) {

                            Call<manageBookmarkBean> call2 = cr.bookmark(b.id);

                            call2.enqueue(new Callback<manageBookmarkBean>() {
                                @Override
                                public void onResponse(Call<manageBookmarkBean> call, Response<manageBookmarkBean> response) {

                                    list = response.body().getManagebookmark();

                                    adapter.setGridData(list);

                                    progress.setVisibility(View.GONE);

                                }

                                @Override
                                public void onFailure(Call<manageBookmarkBean> call, Throwable t) {

                                }
                            });


                        }

                        @Override
                        public void onFailure(Call<deleteBookBean> call, Throwable t) {

                        }
                    });

                }
            });

        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView name , desc;
            ImageButton delete;

            public ViewHolder(View itemView) {
                super(itemView);
                name = (TextView)itemView.findViewById(R.id.name);
                desc = (TextView)itemView.findViewById(R.id.desc);
                delete = (ImageButton)itemView.findViewById(R.id.delete);

            }
        }

    }

}
