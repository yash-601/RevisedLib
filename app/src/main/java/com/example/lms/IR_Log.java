package com.example.lms;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class IR_Log extends AppCompatActivity {
    Button button;
    ListView newview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ir_log);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color='#ffffff'> Issue Return Logs</font>"));

        newview = findViewById(R.id.irlogview);
        button=findViewById(R.id.button);
        //List<String> list=new ArrayList<>();

        DatabaseHelper databaseHelper=new DatabaseHelper(IR_Log.this);

        /*
        ArrayList everyone=databaseHelper.getlogs();
        //List<BookRows> everyone=databaseHelper.getEveryone();
        ArrayAdapter customerArrayAdapter=new ArrayAdapter<BookRows>(IR_Log.this, android.R.layout.simple_list_item_1,everyone);
        newview.setAdapter(customerArrayAdapter);*/
        //IRLog+" ("+bookid+" INT, "+reader_id+" INT, "+date+" TEXT, "+Entry_Type+" TEXT)";



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=databaseHelper.getir();

                if(res.getCount()==0){
                    Toast.makeText(IR_Log.this, "IR is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("\n");
                    buffer.append("BOOKID: "+res.getString(0)+", ");
                    buffer.append("READERID: "+res.getString(1)+", ");
                    buffer.append("DATE: "+res.getString(2)+", ");
                    buffer.append("ENTRYTYPE: "+res.getString(3)+"\n");

                    //newview.setAdapter(adapter);
                }
                ArrayAdapter adapter= new ArrayAdapter(IR_Log.this, android.R.layout.simple_list_item_1, Collections.singletonList(buffer));
                newview.setAdapter(adapter);

            }
        });






    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }


}