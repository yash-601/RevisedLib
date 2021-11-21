package com.example.lms;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;

public class Return_Book extends AppCompatActivity {

    EditText book_id, reader_id, date_of_return;
    Button return_book;
    TextView fineshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_return_book);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Return Book</font>"));

        book_id = findViewById(R.id.Book_id_return);
        reader_id = findViewById(R.id.Reader_id_return);
        date_of_return = findViewById(R.id.Date_Reg_return);
        return_book = findViewById(R.id.Return_Book_Button);
        fineshow =findViewById(R.id.Fine_txt);

        return_book.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                DatabaseHelper databaseHelper=new DatabaseHelper(Return_Book.this);
                // calling function from database helper
                int success = 0;
                int fine=0;
                try {
                    TwovaluesReturn ans=databaseHelper.returnBook(Integer.parseInt(book_id.getText().toString()), Integer.parseInt(reader_id.getText().toString()), date_of_return.getText().toString());
                    //success = databaseHelper.returnBook(Integer.parseInt(book_id.getText().toString()), Integer.parseInt(reader_id.getText().toString()), date_of_return.getText().toString());
                    success=ans.status;
                    fine=ans.fine;

                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (success == 1){
                    Toast.makeText(Return_Book.this, "Book Returned Successfully!!", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Return_Book.this, "fine = " + fine, Toast.LENGTH_SHORT).show();
                    //Cmmented out this as fine is not visible
                    fineshow.setText(String.valueOf(fine));
                }

                else
                    Toast.makeText(Return_Book.this, "Not working :(", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}