package com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import nurse.Nurse;
import nurse.NurseViewModel;

public class MainActivity extends AppCompatActivity {
    private NurseViewModel nurseViewModel;
    public static final int ADD_NOTE_REQUEST=1;

    SharedPreferences sharedPreferences;
    Nurse loggedNurse = null;
    ImageButton btnStartPatientsActivity;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnStartPatientsActivity = findViewById(R.id.btnStartPatientsActivity);
        btnStartPatientsActivity.setEnabled(false);




        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);
        nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
                //update Recyclerview
                Toast.makeText(getApplicationContext(), "OnChanges", Toast.LENGTH_SHORT).show();
                //adapter.setNotes(notes);

            }
        });

        //Buttons listeners
        btnStartPatientsActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,PatientActivity.class);
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.login_menu, menu);

        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.loginMenu:
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivityForResult(intent, ADD_NOTE_REQUEST);

                Toast.makeText(this, "To Login", Toast.LENGTH_SHORT).show();
                return true;
            default:return super.onOptionsItemSelected(item);
        }



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == ADD_NOTE_REQUEST && resultCode == RESULT_OK){
            int id = data.getIntExtra("EXTRA_ID",1);
            String firstname = data.getStringExtra("EXTRA_FIRSTNAME");
            String lastname = data.getStringExtra("EXTRA_LASTNAME");
            String department = data.getStringExtra("EXTRA_DEPARTMENT");
            btnStartPatientsActivity.setEnabled(true);
            btnStartPatientsActivity.setBackgroundColor(Color.GREEN);

             loggedNurse = new Nurse(firstname, lastname,department,"this is not a password");
             Toast.makeText(this, loggedNurse.toString(), Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
        }
    }
}