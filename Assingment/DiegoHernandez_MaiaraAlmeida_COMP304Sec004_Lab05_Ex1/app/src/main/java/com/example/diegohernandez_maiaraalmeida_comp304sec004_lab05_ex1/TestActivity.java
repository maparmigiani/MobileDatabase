package com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.ArrayList;
import java.util.List;

import nurse.Nurse;
import nurse.NurseViewModel;
import test.Test;
import test.TestViewModel;

public class TestActivity extends AppCompatActivity {

    Spinner spinnerAddTestNurseId;
    EditText textViewBPL;
    EditText textViewBPH;
    EditText textViewTemperature;
    Button btnCreateTest,btnSeeTests;

    TestViewModel testViewModel;
    NurseViewModel nurseViewModel;
    ArrayList<Integer>nursesId = new ArrayList<Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int patientId = extras.getInt("EXTRA_PATIENT_ID",-1);

        spinnerAddTestNurseId = findViewById(R.id.spinnerAddTestNurseId);
        textViewBPL = findViewById(R.id.editTextAddTestBPL);
        textViewBPH = findViewById(R.id.editTextAddTestBPH);
        textViewTemperature = findViewById(R.id.editTextAddTestTemperature);
        btnCreateTest = findViewById(R.id.btnCreateTest);
        btnSeeTests = findViewById(R.id.btnSeeTests);

        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        testViewModel.getAllTest().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {

                Toast.makeText(getApplicationContext(), "OnChanges", Toast.LENGTH_SHORT).show();
            }
        });

        //Creating the ArrayAdapter instance having the nurseId list
        ArrayAdapter adapterNurse = new ArrayAdapter(this,android.R.layout.simple_spinner_item,nursesId);
        adapterNurse.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);
        nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {

                for (Nurse nurse : nurses) {
                    nursesId.add(nurse.getId());
                    Log.i("Nurses",nurse.toString());
                }
                //Setting the ArrayAdapter data on the Spinner
                spinnerAddTestNurseId.setAdapter(adapterNurse);
                Toast.makeText(getApplicationContext(), "OnChanges", Toast.LENGTH_SHORT).show();
            }
        });


        //Button listeners
        btnCreateTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int nurseId;
                int bpl;
                int bph;
                double temperature;

                try{
                    nurseId = Integer.valueOf(spinnerAddTestNurseId.getSelectedItem().toString());
                    bpl = Integer.valueOf(textViewBPL.getText().toString());
                    bph = Integer.valueOf(textViewBPH.getText().toString());
                    temperature = Double.valueOf(textViewTemperature.getText().toString());
                    testViewModel.insert(new Test(patientId,nurseId,bpl,bph,temperature));


                } catch (Exception e){
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    Toast.makeText(getApplicationContext(), "Please fill all the fields with valid inputs", Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        });
        btnSeeTests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(TestActivity.this,TestActivity.class);
                //startActivity(intent);
                finish();

            }
        });


    }
}