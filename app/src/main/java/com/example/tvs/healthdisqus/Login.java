package com.example.tvs.healthdisqus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button signin,join;
    EditText username,password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        signin= (Button) findViewById(R.id.button);
        join= (Button) findViewById(R.id.button1);

        username= (EditText) findViewById(R.id.edit);
        password= (EditText) findViewById(R.id.edit1);


        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,MainActivity.class);
                startActivity(i);
                Toast.makeText(Login.this, "username or password cannot be empty", Toast.LENGTH_SHORT).show();
            }
        });


        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Register.class);
                startActivity(i);
            }
        });
    }
}
