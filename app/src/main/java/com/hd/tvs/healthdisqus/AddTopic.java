package com.hd.tvs.healthdisqus;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import addTopicPOJO.addTopicBean;
import interfaces.allAPIs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddTopic extends AppCompatActivity {

    String catId;
    ProgressBar progress;

    EditText sub , desc;

    Toolbar toolbar;

    Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_topic);

        toolbar = (Toolbar)findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        toolbar.setTitleTextColor(Color.WHITE);

        toolbar.setTitle("Add Topic");

        toolbar.setNavigationIcon(R.drawable.back);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        catId = getIntent().getExtras().getString("id");

        add = (Button)findViewById(R.id.add);
        sub = (EditText)findViewById(R.id.subject);
        desc = (EditText)findViewById(R.id.desc);

        progress = (ProgressBar)findViewById(R.id.progress);

        final bean b = (bean)getApplicationContext();


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://www.healthdisqus.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();


                final allAPIs cr = retrofit.create(allAPIs.class);

                progress.setVisibility(View.VISIBLE);

                Call<addTopicBean> call = cr.addTopic(catId , b.id , sub.getText().toString() , desc.getText().toString() , "" , "" , "" , "" , "" , "" , "");

                call.enqueue(new Callback<addTopicBean>() {
                    @Override
                    public void onResponse(Call<addTopicBean> call, Response<addTopicBean> response) {

                        progress.setVisibility(View.GONE);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<addTopicBean> call, Throwable t) {
                        progress.setVisibility(View.GONE);
                    }
                });

            }
        });






    }
}
