package com.example.inika.studentdbtest;

import android.support.v7.app.AppCompatActivity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
//
public class MainActivity extends AppCompatActivity {

    private StudentManager studentManager;
    private EditText txtId, txtStudentName, txtStudentEmail ;
    private Button btnAdd, btnShow, btnEdit;
    private final static String TABLE_NAME = "AndroidStudent";
    //sql string to create the table
    private static final String tableCreatorString =
            "CREATE TABLE "+ TABLE_NAME + " (studentId integer primary key, studentName text,studentEmail text);";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        txtId = (EditText) findViewById(R.id.txtId);
        txtStudentName = (EditText) findViewById(R.id.txtStudentName);
        txtStudentEmail = (EditText) findViewById(R.id.txtStudentEmail);
        //
        btnAdd = (Button)findViewById(R.id.btnAdd);
        btnShow = (Button)findViewById(R.id.btnSHow);
        btnEdit = (Button)findViewById(R.id.btnEdit);
        // instantiate the StudentManager
        // initialize the tables
        try {
            studentManager = new StudentManager(this);
            //create the table
            studentManager.dbInitialize(TABLE_NAME, tableCreatorString);
        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());
        }

    } //end of onCreate method
    //event handling
    public void addStudent(View view)
    {
        //read values for text fields
        int studentId = Integer.parseInt(txtId.getText().toString());
        String studentName = txtStudentName.getText().toString();
        String studentEmail = txtStudentEmail.getText().toString();
        //initialize ContentValues object with the new student
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentId",studentId);
        contentValues.put("studentName",studentName);
        contentValues.put("studentEmail",studentEmail);
        //
        try {
            studentManager.addRow(contentValues);
        }
        catch(Exception exception)
        {
            //
            Toast.makeText(MainActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }


    }
    //
    public void showStudent(View view)
    {
        try {
            Student student = studentManager.getStudentById(txtId.getText().toString(), "studentId");
            txtStudentName.setText(student.getStudentName());
            txtStudentEmail.setText(student.getStudentEmail());
        }
        catch (Exception exception)
        {
            Toast.makeText(MainActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }


    }
    //
    public void editStudent(View view)
    {
        int studentId = Integer.parseInt(txtId.getText().toString());

        String studentName = txtStudentName.getText().toString();
        String studentEmail = txtStudentEmail.getText().toString();
        try{
            ContentValues contentValues = new ContentValues();
            contentValues.put("studentId",studentId);
            contentValues.put("studentName",studentName);
            contentValues.put("studentEmail",studentEmail);
            //edit the row
            boolean b=studentManager.editRow(studentId, "studentId", contentValues);


        }
        catch(Exception exception)
        {
            Toast.makeText(MainActivity.this,
                    exception.getMessage(), Toast.LENGTH_SHORT).show();
            Log.i("Error: ",exception.getMessage());

        }


    }


}
