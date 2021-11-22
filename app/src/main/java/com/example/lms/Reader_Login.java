package com.example.lms;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Reader_Login extends AppCompatActivity {

    EditText mEmail,mPassword;
    Button mLogin;
    ProgressBar progressBar;
    public String mail;
//    FirebaseAuth fAuth;


    public String retmail(){
        return mail;

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reader_login);
        getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'> Student Login</font>"));
        
        // creating instance of issued_books_reader
        Issued_Books_Reader instance = new Issued_Books_Reader();
        // creating instance of mainactivity2
        MainActivity2 inst = new MainActivity2();
        
        mEmail=findViewById(R.id.Email_login);
        mPassword=findViewById(R.id.Password_login);
        progressBar=findViewById(R.id.progressBar3);
//        fAuth=FirebaseAuth.getInstance();
        mLogin=findViewById(R.id.Login);


//        mLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String email= mEmail.getText().toString().trim();
//                String password=mPassword.getText().toString().trim();
//
//                if(TextUtils.isEmpty(email)){
//                    mEmail.setError("Email Is Required");
//                    progressBar.setVisibility(View.INVISIBLE);
//                    return;
//                }
//                if(TextUtils.isEmpty(password)){
//                    mPassword.setError("Password Is Required");
//                    progressBar.setVisibility(View.INVISIBLE);
//                    return;
//                }
//
//
//                progressBar.setVisibility(View.VISIBLE);
//                //authentication
//
//                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if (task.isSuccessful()){
//                            Toast.makeText(Reader_Login.this,"Sign In Sucessful", Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(),MainActivity2.class));
//                        }
//                        else{
//                            Toast.makeText(Reader_Login.this, "Error!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
//                            progressBar.setVisibility(View.INVISIBLE);
//                        }
//                    }
//                });
//
//            }
//        });


        mLogin.setOnClickListener(new View.OnClickListener() {
        @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(mEmail.getText().toString())){
                    Toast.makeText(Reader_Login.this, "Enter details", Toast.LENGTH_SHORT).show();
                    return;

                }

                if(TextUtils.isEmpty(mPassword.getText().toString())){
                    Toast.makeText(Reader_Login.this, "Enter details", Toast.LENGTH_SHORT).show();
                    return;

                }
                String email= mEmail.getText().toString().trim();
                instance.get_email(email);
                inst.get_user_mail(email);
                String password=mPassword.getText().toString().trim();
                boolean ok = true;
                if (email.equals("")) {
                    Toast.makeText(Reader_Login.this, "Email is required", Toast.LENGTH_SHORT).show();
                    ok = false;
                }
                if (password.equals("")) {
                    Toast.makeText(Reader_Login.this, "Password field is required", Toast.LENGTH_SHORT).show();
                    ok = false;
                }

                if (ok) {
                    DatabaseHelper dbHelper = new DatabaseHelper(Reader_Login.this);
                    boolean success = dbHelper.check_reader_login(email, password);

                    if (success) {
                        Toast.makeText(Reader_Login.this, "Signed in Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainActivity2.class));
                    } else {
                        Toast.makeText(Reader_Login.this, "Sign in Failed! Try Again", Toast.LENGTH_SHORT).show();
                    }
                }
            }
    });

    }

    public void btn_back_welcome(View view) {
        startActivity(new Intent(getApplicationContext(),App_Launch.class));
    }
}
