package com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1;

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
import patient.Patient;
import patient.PatientViewModel;

public class AddPatientActivity extends AppCompatActivity {

    EditText editTextPatientFirstname;
    EditText editTextPatientLastname;
    Spinner spinnerPatientNurseId, spinnerPatientDepartment, spinnerPatientRoom;
    Button btnCreateNewPatient, btnCancelNewPatient;
    NurseViewModel nurseViewModel;
    PatientViewModel patientViewModel;
    ArrayList<Integer> nursesId = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_patient);

        editTextPatientFirstname = findViewById(R.id.editTextPatientFirstName);
        editTextPatientLastname = findViewById(R.id.editTextPatientLastName);
        spinnerPatientDepartment = findViewById(R.id.spinnerPatientDepartment);
        spinnerPatientNurseId = findViewById(R.id.spinnerPatientNurseId);
        spinnerPatientRoom = findViewById(R.id.spinnerPatientRoom);
        btnCreateNewPatient = findViewById(R.id.btnCreateNewPatient);
        btnCancelNewPatient = findViewById(R.id.btnCancelNewPatient);

        patientViewModel = new ViewModelProvider(this).get(PatientViewModel.class);
        patientViewModel.getAllPatients().observe(this, new Observer<List<Patient>>() {
            @Override
            public void onChanged(List<Patient> patients) {
                //update Recyclerview
                Toast.makeText(getApplicationContext(), "OnChanges"+String.valueOf(patients.size()), Toast.LENGTH_SHORT).show();
                //adapter.setNotes(notes);
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
                spinnerPatientNurseId.setAdapter(adapterNurse);
                Toast.makeText(getApplicationContext(), "OnChanges", Toast.LENGTH_SHORT).show();
            }
        });

        // Create an ArrayAdapter for Rooms using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterRoom = ArrayAdapter.createFromResource(this,
                R.array.rooms, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerPatientRoom.setAdapter(adapterRoom);

        // Create an ArrayAdapter for Departments using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterDepartments = ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDepartments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerPatientDepartment.setAdapter(adapterDepartments);

        btnCreateNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String firstname = editTextPatientFirstname.getText().toString();
                    String lastname = editTextPatientLastname.getText().toString();
                    String department = spinnerPatientDepartment.getSelectedItem().toString();
                    int nurseId = Integer.valueOf(spinnerPatientNurseId.getSelectedItem().toString());
                    int room = Integer.valueOf(spinnerPatientRoom.getSelectedItem().toString());
                    patientViewModel.insert(new Patient(firstname, lastname,department,nurseId,room));
                    finish();

                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                }
                finish();
            }
        });
        btnCancelNewPatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}