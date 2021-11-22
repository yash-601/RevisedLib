package com.example.lms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import java.lang.reflect.Array;
import java.security.PublicKey;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;


public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String books="books"; // table name
    public static final String bookid="book_id";
    public static final String isbn="isbn";
    public static final String publisher="publisher";
    public static final String edition="edition";
    public static final String pages="pages";
    public static final String bookname="bookname";
    public static final String status = "status";
    public static final String Issued_Books = "Issued_Books"; // table name
    public static final String reader_id = "reader_id";
    public static final String date_of_issue = "date_of_issue";
    public static final String IRLog = "IRLog"; // table name
    public static final String date = "date";
    public static final String Entry_Type = "Entry_Type";
    public static final String readers = "Readers";
    public static final String reader_name = "reader_name";
    public static final String phone_num = "phone_number";
    public static final String email_id = "email_id";
    public static final String password = "password";
    public static final String date_of_registration = "date_of_registration";
    public static final String fine = "fine";
    public static final int fine_rate = 50;
          

    public DatabaseHelper(@Nullable Context context) {
        super(context, "Library.db", null, 1);
    }
    //first time database is accessed
    String createTableStatement="CREATE TABLE "+books+" ("+bookid+" INT PRIMARY KEY,"+bookname+" TEXT,"+isbn+" INT,"+publisher+" TEXT,"+edition+" INT,"+pages+" INT,"+status+" INT)";
    String createIssuedBooksTable = "CREATE TABLE "+Issued_Books+" ("+bookid+" INT,"+reader_id+" INT, "+date_of_issue+" TEXT)";
    String createIRLogTable = "CREATE TABLE "+IRLog+" ("+bookid+" INT, "+reader_id+" INT, "+date+" TEXT, "+Entry_Type+" TEXT)";
    String createReaderTable = "CREATE TABLE " + readers + " (" + reader_id + " INT PRIMARY KEY," + reader_name + " TEXT," + phone_num + " TEXT," + email_id + " TEXT," + password + " TEXT," + date_of_registration + " TEXT, "+fine+" INT)";
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createTableStatement);
        db.execSQL(createIssuedBooksTable);
        db.execSQL(createIRLogTable);
        db.execSQL(createReaderTable);
    }
    //if version changes

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }



    //this is for adding book librarian
    public boolean addOne(BookRows customerModel){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(bookid,customerModel.getBookid());
        cv.put(bookname,customerModel.getBname());
        cv.put(isbn,customerModel.getIsbn());
        cv.put(publisher,customerModel.getPublisher());
        cv.put(edition,customerModel.getEdition());
        cv.put(pages,customerModel.getPages());
        cv.put(status,customerModel.setStatus(1));

        long insert = db.insert(books,null,cv);
        if(insert==-1) {
            return false;
        }
        else{
            return true;
        }
    }

    public boolean addReader (Reader reader) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(reader_id, reader.getReader_id());
        cv.put(reader_name, reader.getReader_name());
        cv.put(phone_num, reader.getPhone_num());
        cv.put(email_id, reader.getEmail_id());
        cv.put(password, reader.getPassword());
        cv.put(date_of_registration, reader.getDate_of_registration());
        cv.put(fine, 0);

        long insertSuccess = sqLiteDatabase.insert(readers, null, cv);
        return insertSuccess != -1;
    }

    public boolean check_reader_login(String email, String pass) throws SQLException {
        boolean exists = false;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        // checking email
        String selectQuery;
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + readers + " WHERE " + email_id + "=?", new String[]{email}, null);
        if (c.moveToFirst())
            exists = true;
        c.close();

        // checking password
        selectQuery = "SELECT * FROM " + readers + " WHERE " + password + " = " + pass;
        c = sqLiteDatabase.rawQuery(selectQuery, null);
        exists = c.moveToFirst();
        c.close();
        sqLiteDatabase.close();

        return  exists;
    }

    public int issueBook(int book_id, int readerid, String dateofissue) {
        int stat = 0;
        // Accessing the books database here
        String selectQuery = "SELECT * FROM " + books + " WHERE book_id = " + book_id;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            System.out.println("data present in cursor");
            stat = c.getInt(6);
        }

        c.close();
        db.close();

        // inserting data in issued books table
        if (stat == 1) {
            System.out.println("in if statement");
            db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();

            cv.put(bookid, book_id);
            cv.put(reader_id, readerid);
            cv.put(date_of_issue, dateofissue);

            long insert = db.insert(Issued_Books,null,cv);
            if (insert==-1) {
                return 0;
            }

//            db.update(TABLE_USER_CITY, valuesCity, KEY_ID + " = ?", new String[]{String.valueOf(id)});
            // updating values in books table
            ContentValues updated = new ContentValues();
            updated.put(status, 0);
            // status 1 -> available ] status 0 -> issued
            db.update(books, updated, bookid + " = ?", new String[] {String.valueOf(book_id)});

            // inserting data in irlog table
            ContentValues logs = new ContentValues();

            logs.put(bookid, book_id);
            logs.put(reader_id, readerid);
            logs.put(date, dateofissue);
            logs.put(Entry_Type, "Issue");

            long insert_in_logs = db.insert(IRLog,null, logs);
            if(insert_in_logs == -1){
                return 0;
            }

            db.close();
        }

        return stat;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    public int date_diff (String start_date, String end_date) throws ParseException {

        //Parsing the date
        LocalDate dateBefore = LocalDate.parse(start_date);
        LocalDate dateAfter = LocalDate.parse(end_date);

        //calculating number of days in between
        int noOfDaysBetween = (int) ChronoUnit.DAYS.between(dateBefore, dateAfter);

        return noOfDaysBetween;
    }



    @RequiresApi(api = Build.VERSION_CODES.O)
    public TwovaluesReturn returnBook(int book_id, int readerid, String date_of_return) throws ParseException {

        int success = 1;
        SQLiteDatabase db = this.getWritableDatabase();
        // updating status of book in the books table
        ContentValues updated = new ContentValues();
        updated.put(status, 1);
        // status 1 -> available ] status 0 -> issued
        db.update(books, updated, bookid + " = ?", new String[] {String.valueOf(book_id)});

        // adding entry to irlog
        ContentValues logs = new ContentValues();

        logs.put(bookid, book_id);
        logs.put(reader_id, readerid);
        logs.put(date, date_of_return);
        logs.put(Entry_Type, "Return");

        long insert_in_logs = db.insert(IRLog,null, logs);
        if(insert_in_logs == -1){
            return new TwovaluesReturn(0,0);
        }
        db.close();

        // fine calculation and deletion goes here
        db = this.getReadableDatabase();
        String selectQuery = "SELECT * FROM " + Issued_Books + " WHERE book_id = " + book_id;
        String date = "";
        Cursor c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            date = c.getString(2);
        }

        c.close();

        int days = date_diff(date, date_of_return);
        System.out.println("days = " + days);
        int fine_calc = (days > 15)? (days-15)*fine_rate: 0;
        int current_fine = 0;
        selectQuery = "SELECT * FROM " + readers + " WHERE reader_id = " + readerid;
        c = db.rawQuery(selectQuery, null);
        if (c.moveToFirst()) {
            current_fine = c.getInt(6);
        }
        fine_calc += current_fine;
        c.close();
        db.close();

        // now adding fine to reader table
        db = this.getWritableDatabase();
        ContentValues new_fine = new ContentValues();
        new_fine.put(fine, fine_calc);
        db.update(readers, new_fine, reader_id + " = ?", new String[] {String.valueOf(readerid)});

        // deleting from issued books
        db.delete(Issued_Books, bookid + " = ?", new String[] {String.valueOf(book_id)});

        //return success,fine_calc;
        return new TwovaluesReturn(success,fine_calc);
    }

    //this is for books.java to display books in librarian
/*
    public List<BookRows> getEveryone(){
        List<BookRows> returnlist=new ArrayList<>();
        //to gat data from data base

        String querString="SELECT * FROM "+books+" WHERE status = "+new int[]{1};
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(querString,null);
        if (cursor.moveToFirst()){
            //loop through cursor create customer objects and return  into the return list
            do {
                int dbookid=cursor.getInt(0);
                String dbookname=cursor.getString(1);
                int disbn=cursor.getInt(2);
                String dpublisher=cursor.getString(3);
                int dedition=cursor.getInt(4);
                int dpages=cursor.getInt(5);
                int dstatus=cursor.getInt(6);


                BookRows newCustomer=new BookRows(dbookid,dbookname,disbn,dpublisher,dedition,dpages,dstatus);
                returnlist.add(newCustomer);

            }while(cursor.moveToNext());


        }else{
            //failure do not add anything


        }

        //always close cursor and db
        cursor.close();
        db.close();
        return returnlist;

    }*/
/*
    //for ir log
    //IRLog+" ("+bookid+" INT, "+reader_id+" INT, "+date+" TEXT, "+Entry_Type+" TEXT)";
    public ArrayList getlogs(){
        ArrayList returnlist=new ArrayList<>();
        String[] iplist={};
        //to gat data from data base

        String querString="SELECT * FROM "+IRLog;
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor = db.rawQuery(querString,null);
        if (cursor.moveToFirst()){
            //loop through cursor create customer objects and return  into the return list
            do {
                String dbookid=cursor.getString(0);
                String dreaderid=cursor.getString(1);
                String ddate=cursor.getString(2);
                String dentrytype=cursor.getString(3);

                //BookRows newCustomer=new BookRows(dbookid,dbookname,disbn,dpublisher,dedition,dpages,dstatus);
                iplist= new String[]{dbookid,dreaderid,ddate,dentrytype};
                returnlist.add(iplist);

            }while(cursor.moveToNext());


        }else{
            //failure do not add anything


        }

        //always close cursor and db
        cursor.close();
        db.close();
        return returnlist;

    }*/

    public Cursor getbooks(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM books WHERE status = 1",null);
        return cursor;

    }
    public Cursor getir(){
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM IRLog",null);
        return cursor;
    }

    public int get_id (String email) {
        int id = -1;
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " +  readers + " WHERE " + email_id + "=?", new String[]{email});
        if (c.moveToFirst()) {
            id = c.getInt(0);
        }
        c.close();
        sqLiteDatabase.close();
        return id;
    }

    public String get_name (int id) {
        String name = "";
        SQLiteDatabase sqLiteDatabase = this.getReadableDatabase();
        Cursor c = sqLiteDatabase.rawQuery("SELECT * FROM " + readers + " WHERE " + reader_id + " = " + id, null);
        if (c.moveToFirst()) {
            name = c.getString(1);
        }
        c.close();
        sqLiteDatabase.close();
        return name;
    }

    public Cursor getmybooks(int readerid) {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM Issued_Books WHERE reader_id = " + readerid,null);
        return cursor;

    }

    // for displaying readers
    public Cursor getreaders() {
        SQLiteDatabase DB=this.getWritableDatabase();
        Cursor cursor=DB.rawQuery("SELECT * FROM readers",null);
        return cursor;

    }

    public void fine_zero(int readerid) {

        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues updated = new ContentValues();
        updated.put(fine,0);
        DB.update(readers,updated,reader_id + " = ?", new String[]{String.valueOf(readerid)});

    }
    //SQLiteDatabase db = this.getWritableDatabase();
    // updating status of book in the books table
    //ContentValues updated = new ContentValues();
    //    updated.put(status, 1);
    // status 1 -> available ] status 0 -> issued
    //    db.update(books, updated, bookid + " = ?", new String[] {String.valueOf(book_id)});


}












