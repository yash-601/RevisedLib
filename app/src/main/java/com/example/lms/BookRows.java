package com.example.lms;

public class BookRows {
    private int bookid;
    private String bname;
    private int isbn;
    private String publisher;
    private int edition;
    private int pages;
    private int status;


    //constructor


    public BookRows(int bookid, String bname, int isbn, String publisher, int edition, int pages, int status) {
        this.bookid = bookid;
        this.bname = bname;
        this.isbn = isbn;
        this.publisher = publisher;
        this.edition = edition;
        this.pages = pages;
        this.status = status;
    }

    public BookRows(){}
    //to string

    @Override
    public String toString() {
        String st;
        if (status==1){
            st="AV";
        }
        else{
            st="UNAV";
        }
        return "ID=" + bookid +
                ", NAME='" + bname + '\'' +
                ", ISBN=" + isbn +
                ", PUBLISHER='" + publisher + '\'' +
                ", EDITION=" + edition +
                ", PAGES=" + pages +
                ", STATUS="+st;
    }


    //getter and setter

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getBname() {
        return bname;
    }

    public void setBname(String bname) {
        this.bname = bname;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getEdition() {
        return edition;
    }

    public void setEdition(int edition) {
        this.edition = edition;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int setStatus(int status) {
        this.status = status;
        return status;
    }

    public int getStatus() {return status;}


}


