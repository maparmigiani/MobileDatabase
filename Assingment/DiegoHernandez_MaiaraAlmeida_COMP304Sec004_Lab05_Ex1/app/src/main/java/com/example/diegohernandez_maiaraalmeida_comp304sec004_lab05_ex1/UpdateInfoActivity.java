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
import patient.Patient;
import patient.PatientViewModel;

public class UpdateInfoActivity extends AppCompatActivity {

    Spinner spinnerUpdatePatientRoom,spinnerUpdatePatientDepartment, spinnerUpdatePatientNurseID;
    EditText editTextPatientFirstname;
    EditText editTextPatientLastname;
    Button btnUpdatePatient, btnCancel;
    NurseViewModel nurseViewModel;
    ArrayList<Integer> nursesId = new ArrayList<Integer>();
    PatientViewModel patientViewModel;
    private int nurseID, nurseIDposition, room;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        editTextPatientFirstname = findViewById(R.id.editTextUpdatePatientFirstName);
        editTextPatientLastname = findViewById(R.id.editTextUpdatePatientLastName);
        spinnerUpdatePatientDepartment = findViewById(R.id.spinnerUpdatePatientDepartment);
        spinnerUpdatePatientNurseID = findViewById(R.id.spinnerUpdatePatientNurseID);
        spinnerUpdatePatientRoom = findViewById(R.id.spinnerUpdatePatientRoom);
        btnUpdatePatient = findViewById(R.id.btnUpdatePatient);
        btnCancel = findViewById(R.id.btnCancel);

        //Retrievening intent data
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        int id = extras.getInt("EXTRA_ID");
        String firstname = extras.getString("EXTRA_FIRSTNAME");
        String lastname = extras.getString("EXTRA_LASTNAME");
        String department = extras.getString("EXTRA_DEPARTMENT");
        int room = extras.getInt("EXTRA_ROOM");
        int nurseID = extras.getInt("EXTRA_NURSE_ID");
        //int nurseIDposition =getSpinnerField().getAdapter().indexOf(nurseID);
        //int nurseIDposition = getIntent().getIntExtra( "EXTRA_NURSE_ID",nurseID);


        editTextPatientFirstname.setText(firstname);
        editTextPatientLastname.setText(lastname);
        //spinnerUpdatePatientDepartment.setSelection(nurseID);
        //spinnerUpdatePatientNurseID.getItemAtPosition(nurseIDposition);
        //spinnerUpdatePatientRoom.getItemAtPosition(room);


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
                spinnerUpdatePatientNurseID.setAdapter(adapterNurse);
                //Setting position
                int spinnerPositionNurse = adapterNurse.getPosition(nurseID);
                spinnerUpdatePatientNurseID.setSelection(spinnerPositionNurse);

                Toast.makeText(getApplicationContext(), "OnChanges", Toast.LENGTH_SHORT).show();
            }
        });

        // Create an ArrayAdapter for Rooms using the string array and a default spinner layout
        ArrayAdapter adapterRoom = ArrayAdapter.createFromResource(this,
                R.array.rooms, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterRoom.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerUpdatePatientRoom.setAdapter(adapterRoom);
        //Setting position
        int spinnerPositionRoom = adapterRoom.getPosition(String.valueOf(room));
        spinnerUpdatePatientRoom.setSelection(spinnerPositionRoom);




        // Create an ArrayAdapter for Departments using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapterDepartments = ArrayAdapter.createFromResource(this,
                R.array.departments, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapterDepartments.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinnerUpdatePatientDepartment.setAdapter(adapterDepartments);
        //Setting position
        int spinnerPositionDepartment = adapterDepartments.getPosition(String.valueOf(department));
        spinnerUpdatePatientDepartment.setSelection(spinnerPositionDepartment);

        btnUpdatePatient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try{
                    String firstname = editTextPatientFirstname.getText().toString();
                    String lastname = editTextPatientLastname.getText().toString();
                    String department = spinnerUpdatePatientDepartment.getSelectedItem().toString();
                    int nurseId = Integer.valueOf(spinnerUpdatePatientNurseID.getSelectedItem().toString());
                    int room = Integer.valueOf(spinnerUpdatePatientRoom.getSelectedItem().toString());

                    Patient patientToUpdate = new Patient(firstname, lastname,department,nurseId,room);
                    patientToUpdate.setId(id);
                    patientViewModel.update(patientToUpdate);
                    Toast.makeText(getApplicationContext(),"Patient updated",Toast.LENGTH_SHORT).show();
                    finish();

                } catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();

                }

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UpdateInfoActivity.this,PatientActivity.class);
                startActivity(intent);

            }
        });
    }
}