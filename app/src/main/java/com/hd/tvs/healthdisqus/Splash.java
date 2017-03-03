package com.hd.tvs.healthdisqus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

import interfaces.allAPIs;
import loginPOJO.loginBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Splash extends AppCompatActivity {

    Timer t;
    ProgressBar progress;
    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        pref = getSharedPreferences("my", MODE_PRIVATE);
        edit = pref.edit();

        progress = (ProgressBar) findViewById(R.id.progress);


        String e = pref.getString("id", "");
        String p = pref.getString("pass", "");


        if (e.length() > 0 && p.length() > 0) {


            login(e,p);

        } else
        {
            t = new Timer();

            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    Intent intent = new Intent(Splash.this , Login.class);
                    startActivity(intent);
                    finish();
                }
            } , 1500);
        }




    }

    public void login(final String username , final String password)
    {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://www.healthdisqus.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<loginBean> call = cr.login(username , password);

        call.enqueue(new Callback<loginBean>() {
            @Override
            public void onResponse(Call<loginBean> call, Response<loginBean> response) {


                if (response.body().getLogin().get(0).getUserId().length() > 0)
                {

                    progress.setVisibility(View.GONE);

                    bean b = (bean)getApplicationContext();

                    b.id = response.body().getLogin().get(0).getUserId();

                    b.name = response.body().getLogin().get(0).getUserName();

                    Intent i = new Intent(Splash.this , MainActivity.class);

                    edit.putString("id" , username);
                    edit.putString("pass" , password);
                    edit.commit();

                    startActivity(i);

                    finish();

                }
                else
                {
                    Toast.makeText(Splash.this , response.body().getLogin().get(0).getStatus() , Toast.LENGTH_SHORT).show();
                    progress.setVisibility(View.GONE);
                }




            }

            @Override
            public void onFailure(Call<loginBean> call, Throwable t) {

                progress.setVisibility(View.GONE);

            }
        });



    }

}
