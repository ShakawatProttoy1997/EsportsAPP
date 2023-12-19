package com.example.signupandloginwithfirebase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class MainActivity extends AppCompatActivity {

   // public String api = "https://ecommercebe.azurewebsites.net";
   Button watchmore;
   Fragment homeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // Retrofit retrofit = new Retrofit.Builder().baseUrl(api)
            //    .addConverterFactory(GsonConverterFactory.create()).build();
       watchmore = findViewById(R.id.watchmoreBtn);
       watchmore.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
              startActivity(new Intent(MainActivity.this,HomePage.class));
           }
       });
       homeFragment = new HomeFragment();
       loadFragment(homeFragment);

    }

    private void loadFragment(Fragment homeFragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.layout1,homeFragment);
        transaction.commit();
    }
}