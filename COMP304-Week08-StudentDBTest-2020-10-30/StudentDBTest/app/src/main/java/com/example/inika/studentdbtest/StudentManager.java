package com.example.inika.studentdbtest;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class StudentManager extends SQLiteOpenHelper{
    //database name and version
    private static final String DATABASE_NAME = "StudentDB";
    private static final int DATABASE_VERSION = 1;
    // table name and table creator string (SQL statement to create the table)
    // should be set from within main activity
    private static String tableName;
    private static String tableCreatorString;
    //
    // no-argument constructor
    public StudentManager(Context context)
    {
        super(context, DATABASE_NAME , null, DATABASE_VERSION);
    }
    // Called when the database is created for the first time.
    // This is where the creation of tables and the initial population
    // of the tables should happen.
    @Override
    public void onCreate(SQLiteDatabase db) {
        //create the table
        db.execSQL(tableCreatorString);
    }
    //
    // Called when the database needs to be upgraded.
    // The implementation should use this method to drop tables,
    // add tables, or do anything else it needs to upgrade
    // to the new schema version.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //drop table if existed
        db.execSQL("DROP TABLE IF EXISTS " + tableName);
        //recreate the table
        onCreate(db);
    }
    //
    //
    //
    // initialize table names and CREATE TABLE statement
    // called by activity to create a table in the database
    // The following arguments should be passed:
    // tableName - a String variable which holds the table name
    // tableCreatorString - a String variable which holds the CREATE Table statement

    public void dbInitialize(String tableName, String tableCreatorString)
    {
        this.tableName=tableName;
        this.tableCreatorString=tableCreatorString;

    }
    //
    // CRUD Operations
    //
    //This method is called by the activity to add a row in the table
    // The following arguments should be passed:
    // values - a ContentValues object that holds row values
    public boolean addRow  (ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        // Insert the row
        long nr= db.insert(tableName, null, values);
        db.close(); //close database connection
        return nr> -1;
    }

    // This method returns a task object which holds the table row with the given id
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    public Student getStudentById(Object id, String fieldName) throws Exception{
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + tableName + " where "+ fieldName + "='"+String.valueOf(id)+"'", null );
        Student student = new Student(); //create a new Student object
        if (cursor.moveToFirst()) { //if row exists
            cursor.moveToFirst(); //move to first row
            //initialize the instance variables of task object
            student.setStudentId(cursor.getInt(0));
            student.setStudentName(cursor.getString(1));
            student.setStudentEmail(cursor.getString(2));
            cursor.close();

        } else {
            student = null;
        }
        db.close();
        return student;

    }
    //
    //
    // The following argument should be passed:
    // id - an Object which holds the primary key value
    // fieldName - the  name of the primary key field
    // values - a ContentValues object that holds row values
    public boolean editRow (Object id, String fieldName, ContentValues values) throws Exception {
        SQLiteDatabase db = this.getWritableDatabase();
        //
        int nr = db.update(tableName, values, fieldName + " = ? ", new String[]{String.valueOf(id)});
        return nr > 0;
    }
}
