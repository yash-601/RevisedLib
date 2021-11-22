package com.example.lms;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Issue_Book extends AppCompatActivity {

    EditText book_id, reader_id, date_of_issue;
    Button issue_book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_book);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Issue Book</font>"));

        book_id = findViewById(R.id.Book_id_return);
        reader_id = findViewById(R.id.Reader_id_return);
        date_of_issue = findViewById(R.id.Date_Reg_issue);
        issue_book = findViewById(R.id.Issue_Book_Button);

        // for button ...
        issue_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatabaseHelper databaseHelper=new DatabaseHelper(Issue_Book.this);
                if(TextUtils.isEmpty(book_id.getText().toString())){
                    Toast.makeText(Issue_Book.this, "Enter Details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(reader_id.getText().toString())){
                    Toast.makeText(Issue_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(date_of_issue.getText().toString())){
                    Toast.makeText(Issue_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }

                int success = databaseHelper.issueBook(Integer.parseInt(book_id.getText().toString()), Integer.parseInt(reader_id.getText().toString()), date_of_issue.getText().toString());
                if (success == 1)
                    Toast.makeText(Issue_Book.this, "Book Issued Successfully!!", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(Issue_Book.this, "Not working :(", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}