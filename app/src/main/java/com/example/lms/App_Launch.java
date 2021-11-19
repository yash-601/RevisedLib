package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

public class App_Launch extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_launch);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Welcome</font>"));
    }

    public void btn_lib_login(View view) {
        startActivity(new Intent(getApplicationContext(),Login.class));
    }

    public void btn_reader_login(View view) {
        startActivity(new Intent(getApplicationContext(),Reader_Login.class));
    }
}