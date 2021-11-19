package com.example.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelperReader extends SQLiteOpenHelper {

    private static final String readers = "Readers";
    private static final String reader_id = "reader_id";
    private static final String reader_name = "reader_name";
    private static final String phone_num = "phone_number";
    private static final String email_id = "email_id";
    private static final String password = "password";
    private static final String date_of_registration = "date_of_registration";
    private static final String fine = "fine";

    public DatabaseHelperReader(@Nullable Context context) {
        super(context, "reader.db", null, 1);
    }
    // Called when the database is accessed for first time
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String create_table = "CREATE TABLE " + readers + " (" + reader_id + " INT PRIMARY KEY," + reader_name + " TEXT," + phone_num + " TEXT," + email_id + " TEXT," + password + " TEXT," + date_of_registration + " TEXT, "+fine+" INT)";

        sqLiteDatabase.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // function for adding a single reader
    public boolean addReader (Reader reader) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(reader_id, reader.getReader_id());
        cv.put(reader_name, reader.getReader_name());
        cv.put(phone_num, reader.getPhone_num());
        cv.put(email_id, reader.getEmail_id());
        cv.put(password, reader.getPassword());
        cv.put(date_of_registration, reader.getDate_of_registration());

        long insertSuccess = sqLiteDatabase.insert(readers, null, cv);
        return insertSuccess != -1;
    }
}
