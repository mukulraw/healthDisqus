package com.example.tvs.healthdisqus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText username,email,password,confirmpassword;
    Button signup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        username= (EditText) findViewById(R.id.edit);
        email= (EditText) findViewById(R.id.edit1);
        password= (EditText) findViewById(R.id.edit2);
        confirmpassword= (EditText) findViewById(R.id.edit3);
        signup= (Button) findViewById(R.id.button);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(Register.this, "email,password,username can not be empty", Toast.LENGTH_SHORT).show();

            }
        });
    }
}
