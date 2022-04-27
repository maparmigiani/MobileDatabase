package com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import patient.Patient;
import patient.PatientAdapter;
import patient.PatientViewModel;

public class PatientActivity extends AppCompatActivity {

    RecyclerView recyclerViewPatients;
    Button btnAddPatient;
    PatientViewModel patientViewModel;
    public static final int ADD_NOTE_REQUEST=1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        recyclerViewPatients = findViewById(R.id.recyclerViewPatients);
        btnAddPatient = findViewById(R.id.btnAddPatient);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewPatients);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        PatientAdapter adapter = new PatientAdapter();
        recyclerView.setAdapter(adapter);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                //update Recyclerview

                adapter.setPatients(patients);
                adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(), "OnChanges"+String.valueOf(patients.size()), Toast.LENGTH_SHORT).show();

            }
        });


        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                int DIRECTION_RIGHT=8;
                int DIRECTION_LEFT=4;
                int itemPosition = viewHolder.getAdapterPosition();
                adapter.setPatients(patientViewModel.getAllPatients().getValue());
                adapter.notifyDataSetChanged();


                Patient patient = adapter.getPatientAt(itemPosition);
                if(direction == DIRECTION_RIGHT) {
                    Intent intent = new Intent(PatientActivity.this, ViewTestInfo.class);
                    intent.putExtra("EXTRA_TEST_PATIENT_ID", patient.getId()) ;
                    startActivity(intent);

                }

                Toast.makeText(PatientActivity.this,"Swiped" + String.valueOf(direction), Toast.LENGTH_SHORT).show();
            }



        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new PatientAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Patient patient, int position) {
                Patient patientToUpdate = adapter.getPatientAt(position);
                Intent intent = new Intent(PatientActivity.this, UpdateInfoActivity.class);
                intent.putExtra("EXTRA_ID",patientToUpdate.getId());
                intent.putExtra("EXTRA_FIRSTNAME", patientToUpdate.getFirstname());
                intent.putExtra("EXTRA_LASTNAME", patientToUpdate.getLastname());
                intent.putExtra("EXTRA_DEPARTMENT", patientToUpdate.getDepartment());
                intent.putExtra("EXTRA_NURSE_ID", patientToUpdate.getNurseId());
                intent.putExtra("EXTRA_ROOM", patientToUpdate.getRoom());

                startActivity(intent);

            }
        });



        btnAddPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PatientActivity.this, AddPatientActivity.class);
               startActivity(intent);
            }
        });


    }


}