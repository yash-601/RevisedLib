package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

public class Issued_Books_Reader extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issued_books_reader);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Issued Books</font>"));
    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
    }
}