package com.example.jaswinder_comp304_finaltest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Created by Jaswinder on 04/12/2022.

public class BookManager extends SQLiteOpenHelper {
    //database name and version
    private static final String DATABASE_NAME = "BookDB";
    private static final int DATABASE_VERSION = 1;
    // table name and table creator string (SQL statement to create the table)
    private static String tableName;
    private static String tableCreatorString;

    // no-argument constructor
    public BookManager(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        db.execSQL(tableCreatorString);
    }

    // Called when the database needs to be upgraded.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        //recreate the table
        onCreate(db);
    }

    // initialize table names and CREATE TABLE statement
    // called by activity to create a table in the database

    public void dbInitialize(String tableName, String tableCreatorString)
    {
        this.tableName=tableName;
        this.tableCreatorString=tableCreatorString;
    }

    //This method is called by the activity to add a row in the table

    public boolean addBook(ContentValues values) throws Exception {
        Log.i("content value in book manager class: ",values.toString());
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the record
        long nr= db.insert(tableName, null, values);

        //close database connection
        db.close();
        return nr> -1;
    }

    // This method returns an object which holds the table row with the given name
    public Book getBookById(Object id, String fieldName) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + tableName + " where "+ fieldName + "='"+String.valueOf(id)+"'", null );
        //create a new object
        Book book = new Book();
        //if row exists
        if (cursor.moveToFirst()) {
            //move to first row
            cursor.moveToFirst();
            //initialize the instance variables of object
            book.setBookName(cursor.getString(0));
            book.setCategory(cursor.getString(1));
            book.setPublishedYear(cursor.getInt(2));
            book.setBookPrice(cursor.getDouble(3));
            cursor.close();

        }
        else
        {
            book = null;
        }
        db.close();
        return book;

    }

}

