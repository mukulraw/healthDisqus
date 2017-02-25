package com.example.tvs.healthdisqus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import addPostPOJO.addPodtBean;
import interfaces.allAPIs;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class AddPost extends AppCompatActivity {

    EditText sub , desc;
    Button add;
    ProgressBar progress;
    String tid , cid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        sub = (EditText)findViewById(R.id.subject);
        desc = (EditText)findViewById(R.id.desc);
        progress = (ProgressBar)findViewById(R.id.progress);
        add = (Button)findViewById(R.id.add);

        tid = getIntent().getStringExtra("tid");
        cid = getIntent().getStringExtra("cid");

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

                bean b = (bean)getApplicationContext();

                Call<addPodtBean> call = cr.addPost(tid , cid , b.id , sub.getText().toString() , desc.getText().toString() , "" , "" , "" , "" , "" , "" , "");

                call.enqueue(new Callback<addPodtBean>() {
                    @Override
                    public void onResponse(Call<addPodtBean> call, Response<addPodtBean> response) {

                        progress.setVisibility(View.GONE);
                        finish();

                    }

                    @Override
                    public void onFailure(Call<addPodtBean> call, Throwable t) {

                    }
                });


            }
        });


    }
}
