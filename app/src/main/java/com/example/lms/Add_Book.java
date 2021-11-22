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
import java.sql.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class Add_Book extends AppCompatActivity {

    EditText mbid,mname,misbn,mpublisher,medition,mpages;
    Button madd;
    int status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Add Book</font>"));




        mbid=findViewById(R.id.Book_id);
        mname=findViewById(R.id.BookName);
        misbn=findViewById(R.id.ISBN);
        mpublisher=findViewById(R.id.Publisher);
        medition=findViewById(R.id.Edition);
        mpages=findViewById(R.id.Pages);
        madd=findViewById(R.id.Add);
        status = 1;

        madd.setOnClickListener(new View.OnClickListener() {

            BookRows customerModel;
            @Override
            public void onClick(View v) {

                if(TextUtils.isEmpty(mbid.getText().toString())){
                    Toast.makeText(Add_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mname.getText().toString())){
                    Toast.makeText(Add_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(misbn.getText().toString())){
                    Toast.makeText(Add_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mpublisher.getText().toString())){
                    Toast.makeText(Add_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(medition.getText().toString())){
                    Toast.makeText(Add_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(mpages.getText().toString())){
                    Toast.makeText(Add_Book.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }

                try {
                    customerModel=new BookRows(Integer.parseInt(mbid.getText().toString()),mname.getText().toString(),Integer.parseInt(misbn.getText().toString()),mpublisher.getText().toString(),Integer.parseInt(medition.getText().toString()),Integer.parseInt(mpages.getText().toString()),1);
                    //Toast.makeText(Add_Book.this, customerModel.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Toast.makeText(Add_Book.this, "Error Adding book", Toast.LENGTH_SHORT).show();
                    customerModel = new BookRows(-1,"error",0,"error",0,0,0);


                }
                DatabaseHelper databaseHelper=new DatabaseHelper(Add_Book.this);
                boolean success = databaseHelper.addOne(customerModel);
                Toast.makeText(Add_Book.this, "Book Added "+success, Toast.LENGTH_SHORT).show();



            }
        });


        /* this was a try to connect to database using jdbc

        madd.setOnClickListener(new View.OnClickListener() {




            public void onClick(View v) {
                String isbn=misbn.getText().toString().trim();
                String book_id=mbid.getText().toString().trim();
                String publisher=mpublisher.getText().toString().trim();
                String edition=medition.getText().toString().trim();

                String pages=mpages.getText().toString().trim();
                String name=mname.getText().toString().trim();
                int isbn1=Integer.parseInt(isbn);
                int book_id1=Integer.parseInt(book_id);
                int edition1=Integer.parseInt(edition);

                int pages1=Integer.parseInt(pages);
                String url="jdbc:mysql://localhost::3306/LMS";
                String username="abhishek";
                String password="1510";
                try{
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection con=DriverManager.getConnection(url,username,password);
                    Statement statement=con.createStatement();
                    String sql="INSERT INTO books VALUES("+book_id1+",'"+name+"',"+isbn1+",'"+publisher+"',"+edition1+","+pages1+")";
                    statement.executeUpdate(sql);



                    Toast.makeText(Add_Book.this, "Book Added To Library", Toast.LENGTH_SHORT).show();
                    con.close();



                } catch (Exception e) {
                    Toast.makeText(Add_Book.this, "Error!"+e.getMessage(), Toast.LENGTH_SHORT).show();
                }


            }
        });*/

    }



    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}