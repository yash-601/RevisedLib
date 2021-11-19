package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Books extends AppCompatActivity {

    ListView mview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Books</font>"));
        mview=findViewById(R.id.irlog_view);

        DatabaseHelper databaseHelper=new DatabaseHelper(Books.this);
        List<BookRows> everyone=databaseHelper.getEveryone();
        ArrayAdapter customerArrayAdapter=new ArrayAdapter<BookRows>(Books.this, android.R.layout.simple_list_item_1,everyone);
        mview.setAdapter(customerArrayAdapter);







    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}