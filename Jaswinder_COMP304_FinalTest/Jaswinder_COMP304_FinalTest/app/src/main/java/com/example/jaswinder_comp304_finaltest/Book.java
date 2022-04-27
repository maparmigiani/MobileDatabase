package com.example.jaswinder_comp304_finaltest;

//class named Book
public class Book
{
    //private instances
    private int bookId,publishedYear;
    private String bookName, category;
    private Double bookPrice;

    //default constructor
    public Book() {
    }

    //constructor with five arguments
    public Book(int bookId, String bookName, String category,int publishedYear, Double bookPrice) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.category = category;
        this.publishedYear = publishedYear;
        this.bookPrice = bookPrice;
    }

    //getter and setter methods
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(int publishedYear) {
        this.publishedYear = publishedYear;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getBookPrice() {
        return bookPrice;
    }

    public void setBookPrice(Double bookPrice) {
        this.bookPrice = bookPrice;
    }
}
