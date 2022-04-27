package com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import test.Test;
import test.TestAdapter;
import test.TestViewModel;

public class ViewTestInfo extends AppCompatActivity {

    RecyclerView recyclerViewTests;
    TestViewModel testViewModel;
    List<Test> testsByThisPatient = new ArrayList<>();
    Button btnAddTest, btnSeePatients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        recyclerViewTests = findViewById(R.id.recyclerViewTests);
        btnAddTest = findViewById(R.id.btnAddTest);
        btnSeePatients = findViewById(R.id.btnSeePatients);
        recyclerViewTests.setLayoutManager(new LinearLayoutManager(this));
        TestAdapter adapter = new TestAdapter();
        recyclerViewTests.setAdapter(adapter);


        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        int patientID = extras.getInt("EXTRA_TEST_PATIENT_ID", -1);


        testViewModel = new ViewModelProvider(this).get(TestViewModel.class);
        testViewModel.getAllTest().observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                testsByThisPatient.clear();

                if (!tests.isEmpty()) {
                    for (Test test : tests) {
                        if (test.getPatientId() == patientID) {
                            testsByThisPatient.add(test);
                        }
                    }
                }

                if (testsByThisPatient.size() > 0) {
                    adapter.setTests(testsByThisPatient);
                    adapter.notifyDataSetChanged();
                } else {
                    Toast.makeText(getApplicationContext(), "This patient doesn't have any test yet", Toast.LENGTH_SHORT).show();
                }


            }
        });
        //Button listeners
        btnAddTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewTestInfo.this, TestActivity.class);
                intent.putExtra("EXTRA_PATIENT_ID",patientID);
                startActivity(intent);

            }
        });
        btnSeePatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Intent intent = new Intent(ViewTestInfo.this,PatientActivity.class);
                //startActivity(intent);
                finish();

            }
        });
    }
}