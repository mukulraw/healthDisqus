package com.hd.tvs.healthdisqus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import interfaces.allAPIs;
import loginPOJO.loginBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Login extends AppCompatActivity {

    Button signin;
    TextView join;
    EditText username , password;
    ProgressBar progress;

    Toolbar toolbar;

    SharedPreferences pref;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        pref = getSharedPreferences("my" , MODE_PRIVATE);
        edit = pref.edit();


        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            toolbar.setTitle("Login");
            toolbar.setTitleTextColor(Color.WHITE);

        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }


        progress = (ProgressBar)findViewById(R.id.progress);

        signin= (Button) findViewById(R.id.button);
        join= (TextView) findViewById(R.id.button1);

        username= (EditText) findViewById(R.id.username);
        password= (EditText) findViewById(R.id.password);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = username.getText().toString();
                String p = password.getText().toString();

                if (u.length() > 0)
                {

                    if (p.length() > 0)
                    {

                        progress.setVisibility(View.VISIBLE);

                        login(u , p);

                    }
                    else
                    {
                        Toast.makeText(Login.this , "Invalid password" , Toast.LENGTH_SHORT).show();
                    }

                }
                else
                {
                    Toast.makeText(Login.this , "Invalid username" , Toast.LENGTH_SHORT).show();
                }

            }
        });


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this , Register.class);
                startActivity(i);
            }
        });
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

                    Intent i = new Intent(Login.this , MainActivity.class);

                    edit.putString("id" , username);
                    edit.putString("pass" , password);
                    edit.commit();

                    startActivity(i);

                    finish();

                }
                else
                {
                    Toast.makeText(Login.this , response.body().getLogin().get(0).getStatus() , Toast.LENGTH_SHORT).show();
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
