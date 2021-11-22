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

import java.util.Objects;

public class Add_Reader extends AppCompatActivity {

    Button add_reader;
    EditText reader_id, reader_name, phone_num, email_id, password, date_of_registration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reader);
        Objects.requireNonNull(getSupportActionBar()).setTitle(Html.fromHtml("<font color='#ffffff'> Add Reader</font>"));

        reader_id = findViewById(R.id.Reader_id);
        reader_name = findViewById(R.id.Reader_name);
        phone_num = findViewById(R.id.Phone_Number);
        email_id = findViewById(R.id.Email_Reader);
        password = findViewById(R.id.Password_reader);
        date_of_registration = findViewById(R.id.Date_Reg);
        add_reader = findViewById(R.id.Add_Reader_Button);

        add_reader.setOnClickListener(new View.OnClickListener() {

            Reader reader;
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(reader_id.getText().toString())){
                    Toast.makeText(Add_Reader.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(reader_name.getText().toString())){
                    Toast.makeText(Add_Reader.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(phone_num.getText().toString())){
                    Toast.makeText(Add_Reader.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(email_id.getText().toString())){
                    Toast.makeText(Add_Reader.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password.getText().toString())){
                    Toast.makeText(Add_Reader.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(date_of_registration.getText().toString())){
                    Toast.makeText(Add_Reader.this, "Enter Details", Toast.LENGTH_SHORT).show();
                    return;
                }


                try {
                    reader = new Reader(Integer.parseInt(reader_id.getText().toString()), reader_name.getText().toString(), phone_num.getText().toString(), email_id.getText().toString(), password.getText().toString(), date_of_registration.getText().toString());
                }
                catch (Exception e) {
                    Toast.makeText(Add_Reader.this, "Error", Toast.LENGTH_SHORT).show();
                }
                
                DatabaseHelper db_help_reader = new DatabaseHelper(Add_Reader.this);
                boolean success = db_help_reader.addReader(reader);
                if (success) Toast.makeText(Add_Reader.this, "successfully added", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
}