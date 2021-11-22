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
import java.util.List;
import java.util.Objects;

public class Books extends AppCompatActivity {
    Button button;

    ListView mview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_books);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color='#ffffff'> Books</font>"));
        mview=findViewById(R.id.irlog_view);
        button=findViewById(R.id.show);
        DatabaseHelper databaseHelper=new DatabaseHelper(Books.this);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res=databaseHelper.getbooks();

                if(res.getCount()==0){
                    Toast.makeText(Books.this, "Library is empty.", Toast.LENGTH_SHORT).show();
                    return;
                }

                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("\n");
                    buffer.append("BOOKID: "+res.getString(0)+", ");
                    buffer.append("BOOKNAME: "+res.getString(1)+", ");
                    buffer.append("ISBN: "+res.getString(2)+", ");
                    buffer.append("PUBLISHER: "+res.getString(3)+", ");
                    buffer.append("EDITION: "+res.getString(4)+", ");
                    buffer.append("PAGES: "+res.getString(5)+"\n ");
//                    buffer.append("STATUS: "+res.getString(6)+"\n");
                    //newview.setAdapter(adapter);
                }
                ArrayAdapter adapter= new ArrayAdapter(Books.this, android.R.layout.simple_list_item_1, Collections.singletonList(buffer));
                mview.setAdapter(adapter);

                /*
                List<BookRows> everyone=databaseHelper.getEveryone();
                ArrayAdapter customerArrayAdapter=new ArrayAdapter<BookRows>(Books.this, android.R.layout.simple_list_item_1,everyone);
                mview.setAdapter(customerArrayAdapter);*/

            }

        });









    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}