package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {

    public static String mail;

    public void get_user_mail (String user_name) {
        mail = user_name;
    }

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Reader's Desk</font>"));


        // displaying user name and id on display
        textView = findViewById(R.id.info);
        DatabaseHelper dbHelper = new DatabaseHelper(MainActivity2.this);
        int id = dbHelper.get_id(mail);
        String name = dbHelper.get_name(id);

        textView.setText("  ID: "+ id +"\n" + "  Name: " + name+"  ");
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