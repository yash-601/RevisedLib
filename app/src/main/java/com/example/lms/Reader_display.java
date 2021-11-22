package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Collections;
import java.util.Objects;

public class Reader_display extends AppCompatActivity {
    Button button;

    ListView mview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_display);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color='#ffffff'> Readers</font>"));
        mview=findViewById(R.id.readerlist_view);
        button=findViewById(R.id.show_readers);
        DatabaseHelper db= new DatabaseHelper(Reader_display.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res=db.getreaders();

                if(res.getCount()==0){
                    Toast.makeText(Reader_display.this, "No Readers Added.", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("\n");
                    buffer.append("READERID: "+res.getString(0)+", ");
                    buffer.append("NAME: "+res.getString(1)+", ");
                    buffer.append("PHONE NO: "+res.getString(2)+", ");
                    buffer.append("EMAIL: "+res.getString(3)+", ");
                    buffer.append("DOR: "+res.getString(5)+", ");
                    buffer.append("FINE: "+res.getString(6)+"\n ");
//                    buffer.append("STATUS: "+res.getString(6)+"\n");
                    //newview.setAdapter(adapter);
                }
                ArrayAdapter adapter= new ArrayAdapter(Reader_display.this, android.R.layout.simple_list_item_1, Collections.singletonList(buffer));
                mview.setAdapter(adapter);

                //"CREATE TABLE " + readers + " (" + reader_id + " INT PRIMARY KEY," + reader_name + " TEXT," + phone_num + " TEXT," + email_id + " TEXT," + password + " TEXT," + date_of_registration + " TEXT, "+fine+" INT)";


            }
        });

    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));

    }
}