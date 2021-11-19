package com.example.lms;

public class Reader {
    private int reader_id;
    private String reader_name;
    private String phone_num;
    private String email_id;
    private String password;
    private String date_of_registration;
    private int fine;

    public Reader(int reader_id, String reader_name, String phone_num, String email_id, String password, String date_of_registration) {
        this.reader_id = reader_id;
        this.reader_name = reader_name;
        this.phone_num = phone_num;
        this.email_id = email_id;
        this.password = password;
        this.date_of_registration = date_of_registration;
        this.fine = 0;
    }

    public int getReader_id() {
        return reader_id;
    }

    public void setReader_id(int reader_id) {
        this.reader_id = reader_id;
    }

    public String getReader_name() {
        return reader_name;
    }

    public void setReader_name(String reader_name) {
        this.reader_name = reader_name;
    }

    public String getPhone_num() {
        return phone_num;
    }

    public void setPhone_num(String phone_num) {
        this.phone_num = phone_num;
    }

    public String getEmail_id() {
        return email_id;
    }

    public void setEmail_id(String email_id) {
        this.email_id = email_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDate_of_registration() {
        return date_of_registration;
    }

    public void setDate_of_registration(String date_of_registration) {
        this.date_of_registration = date_of_registration;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getFine() {
        return fine;
    }
}
