package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Librarian's Desk</font>"));
    }

    public void btn_logout(View view) {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(getApplicationContext(),App_Launch.class));
        finish();
    }

    public void btn_addbook(View view) {
        startActivity(new Intent(getApplicationContext(),Add_Book.class));

    }

    public void btn_addreader(View view) {
        startActivity(new Intent(getApplicationContext(),Add_Reader.class));
    }

    public void btn_issuebook(View view) {
        startActivity(new Intent(getApplicationContext(),Issue_Book.class));
    }

    public void btn_returnbook(View view) {
        startActivity(new Intent(getApplicationContext(),Return_Book.class));
    }

    public void btn_irlog(View view) {
        startActivity(new Intent(getApplicationContext(),IR_Log.class));
    }

    public void btn_books(View view) {
        startActivity(new Intent(getApplicationContext(),Books.class));
    }

    public void btn_readers(View view) {
        startActivity(new Intent(getApplicationContext(),Reader_display.class));
    }
}