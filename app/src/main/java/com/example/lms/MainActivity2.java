package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Reader's Desk</font>"));

    }

    public void btn_logout(View view) {
//        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),App_Launch.class));
        finish();
    }

    public void btn_books(View view) {
        startActivity(new Intent(getApplicationContext(),Books2.class));

    }

    public void btn_issuedbooks(View view) {
        startActivity(new Intent(getApplicationContext(),Issued_Books_Reader.class));

    }
}