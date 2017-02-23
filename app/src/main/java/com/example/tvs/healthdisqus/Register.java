package com.example.tvs.healthdisqus;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.Objects;

import interfaces.allAPIs;
import registerPOJO.registerBean;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class Register extends AppCompatActivity {

    EditText username,email,password,confirmpassword;
    Button signup;
    Toolbar toolbar;
    ProgressBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progress = (ProgressBar)findViewById(R.id.progress);

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        try {
            getSupportActionBar().setTitle("Register");
            toolbar.setTitleTextColor(Color.WHITE);

        } catch (NullPointerException e1) {
            e1.printStackTrace();
        }



        username = (EditText) findViewById(R.id.edit);
        email = (EditText) findViewById(R.id.edit1);
        password = (EditText) findViewById(R.id.edit2);
        confirmpassword = (EditText) findViewById(R.id.edit3);
        signup = (Button) findViewById(R.id.button);


        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String e = email.getText().toString();
                String u = username.getText().toString();
                String p = password.getText().toString();
                String c = confirmpassword.getText().toString();

                if (e.length() > 0)
                {

                    if (u.length() > 0)
                    {

                        if (p.length() > 0)
                        {

                            if (Objects.equals(p, c))
                            {

                                progress.setVisibility(View.VISIBLE);
                                register(u , e , p);

                            }
                            else
                            {
                                Toast.makeText(Register.this , "Passwords do not match" , Toast.LENGTH_SHORT).show();
                            }

                        }
                        else
                        {
                            Toast.makeText(Register.this , "Please enter a password" , Toast.LENGTH_SHORT).show();
                        }

                    }
                    else
                    {
                        Toast.makeText(Register.this , "Invalid username" , Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(Register.this , "Invalid email" , Toast.LENGTH_SHORT).show();
                }

            }
        });


    }

    public void register(String username , String email , String password)
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://hellthnu.com/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        allAPIs cr = retrofit.create(allAPIs.class);

        Call<registerBean> call = cr.register(username , password , email , "en" , "UTC");

        call.enqueue(new Callback<registerBean>() {
            @Override
            public void onResponse(Call<registerBean> call, Response<registerBean> response) {

                progress.setVisibility(View.GONE);
                finish();

            }

            @Override
            public void onFailure(Call<registerBean> call, Throwable t) {
                progress.setVisibility(View.GONE);
                Toast.makeText(Register.this , "Error" , Toast.LENGTH_SHORT).show();
            }
        });
    }

}
