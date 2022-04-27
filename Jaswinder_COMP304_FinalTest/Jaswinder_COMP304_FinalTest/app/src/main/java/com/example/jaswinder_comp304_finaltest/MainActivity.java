package com.example.jaswinder_comp304_finaltest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    //sqlite Table name
    private final static String TABLE_NAME = "Book";
    /*  sql string to create the table
     *  Will be a sql query as below
     *  CREATE TABLE "Book" (bookId integer primary key, bookName text,category text,publishedYear integer, bookPrice real);
     */
    private static final String tableCreatorString =
            "CREATE TABLE "+ TABLE_NAME + " (bookId integer primary key, bookName text,category text,publishedYear integer, bookPrice real);";

    //Book Manager Instance
    private BookManager bookManager;
    private String popupMessage;
    private Button btnAdd,bookService;
    private EditText bookname,category,published,price;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Get UI Controls
        //button
        btnAdd = (Button)findViewById(R.id.btnAdd);
        bookService = (Button)findViewById(R.id.bookService);

        //Edit Text
        bookname = (EditText) findViewById(R.id.bookname);
        category = (EditText) findViewById(R.id.category);
        published = (EditText) findViewById(R.id.published);
        price = (EditText) findViewById(R.id.price);


        // instantiate the StudentManager
        // initialize the tables
        try
        {
            bookManager = new BookManager(this);
            //create the table
            bookManager.dbInitialize(TABLE_NAME, tableCreatorString);
        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }
    }

    public void addBook(View view)
    {
        //initialize ContentValues object with the new student
        ContentValues contentValues1 = new ContentValues();
        contentValues1.put("bookId",1);
        contentValues1.put("bookName","harry potter part 1");
        contentValues1.put("category","fiction");
        contentValues1.put("publishedYear",2001);
        contentValues1.put("bookPrice",1500);

        ContentValues contentValues2 = new ContentValues();
        contentValues2.put("bookId",2);
        contentValues2.put("bookName","harry potter part 2");
        contentValues2.put("category","fiction");
        contentValues2.put("publishedYear",2004);
        contentValues2.put("bookPrice",2000);

        ContentValues contentValues3 = new ContentValues();
        contentValues3.put("bookId",3);
        contentValues3.put("bookName","harry potter part 3");
        contentValues3.put("category","fiction");
        contentValues3.put("publishedYear",2006);
        contentValues3.put("bookPrice",2200);

    ContentValues contentValues4 = new ContentValues();
        contentValues4.put("bookId",4);
        contentValues4.put("bookName","harry potter part 4");
        contentValues4.put("category","fiction");
        contentValues4.put("publishedYear",2008);
        contentValues4.put("bookPrice",2340);

        try
        {
            bookManager.addBook(contentValues1);
            bookManager.addBook(contentValues2);
            bookManager.addBook(contentValues3);
            bookManager.addBook(contentValues4);
        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }


    }

    public void onRadioButtonClicked(View view) throws Exception {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio_1:
                if (checked)
                    Toast.makeText(getApplicationContext(),"Id 1",Toast.LENGTH_SHORT).show();
                    insertData(1);
                break;
            case R.id.radio_2:
                if (checked)
                    Toast.makeText(getApplicationContext(),"Id 2",Toast.LENGTH_SHORT).show();
                    insertData(2);

                break;
            case R.id.radio_3:
            if (checked)
                Toast.makeText(getApplicationContext(),"Id 3",Toast.LENGTH_SHORT).show();
                insertData(3);

                break;
            case R.id.radio_4:
            if (checked)
                Toast.makeText(getApplicationContext(),"Id 4",Toast.LENGTH_SHORT).show();
                insertData(4);
                break;
        }
    }

    public void insertData(int bookId){
        try
        {
            Book book    = bookManager.getBookById(bookId,"bookId");
            bookname.setText(book.getBookName());
            category.setText(book.getCategory());
            published.setText(book.getPublishedYear());
            price.setText(book.getBookPrice().toString());
            this.popupMessage = book.getBookName()+" "+book.getCategory()+" "+book.getPublishedYear()+" "+book.getBookPrice().toString();

        }
        catch (Exception exception)
        {
            Toast.makeText(MainActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }
    }

    public void onBookServicesClicked(View view){

        Toast.makeText(MainActivity.this,
                popupMessage, Toast.LENGTH_SHORT).show();
    }
}