package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class IR_Log extends AppCompatActivity {
    ListView newview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ir_log);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Issue Return Logs</font>"));

        newview = findViewById(R.id.irlogview);
        List<String> list=new ArrayList<>();

        DatabaseHelper databaseHelper=new DatabaseHelper(IR_Log.this);
        ArrayAdapter adapter= new ArrayAdapter(IR_Log.this, android.R.layout.simple_list_item_1,list);
        /*
        ArrayList everyone=databaseHelper.getlogs();
        //List<BookRows> everyone=databaseHelper.getEveryone();
        ArrayAdapter customerArrayAdapter=new ArrayAdapter<BookRows>(IR_Log.this, android.R.layout.simple_list_item_1,everyone);
        newview.setAdapter(customerArrayAdapter);*/
        //IRLog+" ("+bookid+" INT, "+reader_id+" INT, "+date+" TEXT, "+Entry_Type+" TEXT)";
        Cursor res=databaseHelper.getir();
        while (res.moveToNext()){
            list.add("BOOKID: "+res.getString(0)+", ");
            list.add("READERID: "+res.getString(1)+", ");
            list.add("DATE: "+res.getString(2)+", ");
            list.add("ENTRYTYPE: "+res.getString(3)+" ");
            newview.setAdapter(adapter);
        }

    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}