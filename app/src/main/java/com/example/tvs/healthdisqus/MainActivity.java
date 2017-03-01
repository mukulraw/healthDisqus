package com.example.tvs.healthdisqus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;


public class MainActivity extends AppCompatActivity {


    Toolbar toolbar;
    DrawerLayout drawer;
    TextView name , settings , logout , book;

    SharedPreferences pref;
    SharedPreferences.Editor edit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pref = getSharedPreferences("my" , MODE_PRIVATE);
        edit = pref.edit();

        ImageLoader.getInstance().init(ImageLoaderConfiguration.createDefault(MainActivity.this));

        name = (TextView)findViewById(R.id.name);

        settings = (TextView)findViewById(R.id.settings);
        logout = (TextView)findViewById(R.id.logout);
        book = (TextView)findViewById(R.id.book);

        book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this , Bookmarks.class);
                startActivity(intent);

            }
        });

        toolbar = (Toolbar)findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        try
        {
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e1)
        {
            e1.printStackTrace();
        }


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(MainActivity.this , Profile.class);
                startActivity(intent);


            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edit.remove("id");
                edit.remove("password");
                edit.commit();

                bean b = (bean)getApplicationContext();

                b.id = "";
                b.name = "";

                Intent i = new Intent(getApplicationContext() , Login.class);
                i.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(i);
                finish();
            }
        });


        drawer = (DrawerLayout) findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open , R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        bean be = (bean)getApplicationContext();

        name.setText("Hello, " + be.name);

        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Category_fragment category_fragment = new Category_fragment();

        Bundle b = new Bundle();

        b.putString("id" , "0");
        b.putString("name" , "Categories");

        category_fragment.setArguments(b);

        ft.replace(R.id.layout_to_replace,category_fragment);

        ft.commit();






    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu , menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.Search) {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

            Category_fragment frag1 = new Category_fragment();
            Bundle b = new Bundle();

            b.putString("id" , "0");
            b.putString("name" , "Categories");

            frag1.setArguments(b);

            ft.replace(R.id.layout_to_replace , frag1);
            ft.addToBackStack(null);
            ft.commit();

            drawer.closeDrawer(GravityCompat.START);

        }


        return true;

    }

    @Override
    public void onBackPressed() {

        if (drawer.isDrawerOpen(GravityCompat.START))
        {
            drawer.closeDrawer(GravityCompat.START);
        }
        else
        {
            super.onBackPressed();
        }


    }
}
