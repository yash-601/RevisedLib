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

public class Issued_Books_Reader extends AppCompatActivity {
    Button button;
    ListView disp;
    static String fin_mail;

    public void get_email(String mail) {
        fin_mail = mail;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issued_books_reader);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Issued Books</font>"));
        button=findViewById(R.id.show_red);
        disp=findViewById(R.id.issue_show);


        DatabaseHelper db=new DatabaseHelper(Issued_Books_Reader.this);

        String email = fin_mail;


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id= db.get_id(email);
                //Toast.makeText(Issued_Books_Reader.this, "email="+id, Toast.LENGTH_SHORT).show();

                Cursor res=db.getmybooks(id);

                if(res.getCount()==0){
                    Toast.makeText(Issued_Books_Reader.this, "No books issued", Toast.LENGTH_SHORT).show();
                    return;
                }
                //ssued_Books+" ("+bookid+" INT,"+reader_id+" INT, "+date_of_issue+" TEXT)

                StringBuffer buffer=new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("\n");
                    buffer.append("BOOKID: "+res.getString(0)+", ");
                    buffer.append("DATE ISSUED: "+res.getString(2)+"\n");

//                    buffer.append("STATUS: "+res.getString(6)+"\n");
                    //newview.setAdapter(adapter);
                }
                ArrayAdapter adapter= new ArrayAdapter(Issued_Books_Reader.this, android.R.layout.simple_list_item_1, Collections.singletonList(buffer));
                disp.setAdapter(adapter);



            }
        });

    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity2.class));
    }
}
