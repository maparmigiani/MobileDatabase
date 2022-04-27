package com.example.diegohernandez_maiaraalmeida_comp304sec004_lab05_ex1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import java.util.List;

import nurse.Nurse;
import nurse.NurseViewModel;

public class LoginActivity extends AppCompatActivity {

    private NurseViewModel nurseViewModel;
    Button btnLogin;
    EditText editTextUsername; //THE ID
    EditText editTextPassword;
    CheckBox chkRememberMe;
    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = findViewById(R.id.btnLogin);
        editTextUsername = findViewById(R.id.editTextUserName);
        editTextPassword = findViewById(R.id.editTextTextPassword);
        chkRememberMe = findViewById(R.id.checkBoxRememberMe);

        //Shared Preferences
        sharedPreferences = getSharedPreferences("SHARE_PREFERENCES",MODE_PRIVATE);

        chkRememberMe.setChecked(sharedPreferences.getBoolean("REMEMBER_ME",false));

        if(chkRememberMe.isChecked()){
            //try{
                int sharedNurseId = sharedPreferences.getInt("ID",-1);
                String password = sharedPreferences.getString("PASSWORD",null);
                editTextUsername.setText(String.valueOf(sharedNurseId));
                editTextPassword.setText(password);
            //} //catch (Exception e){
             //  Toast.makeText(LoginActivity.this,e.getMessage(),Toast.LENGTH_SHORT);
            //}

        }

        nurseViewModel = new ViewModelProvider(this).get(NurseViewModel.class);
        nurseViewModel.getAllNurses().observe(this, new Observer<List<Nurse>>() {
            @Override
            public void onChanged(List<Nurse> nurses) {
                //update Recyclerview
                Toast.makeText(getApplicationContext(), "OnChanges", Toast.LENGTH_SHORT).show();
                //adapter.setNotes(notes);

            }
        });

        //------------------------------------Login BTN listener
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean canLogin = false;
                String id = editTextUsername.getText().toString();
                String password = editTextPassword.getText().toString();
                Nurse loggedNurse = null;

                for(Nurse nurse : nurseViewModel.getAllNurses().getValue()){
                    Log.i("Nurse",nurse.toString());
                    //Toast.makeText(getApplicationContext(), nurse.getFirstname()+nurse.getLastname(), Toast.LENGTH_SHORT).show();
                    if(id.equals(String.valueOf(nurse.getId())) && password.equals(nurse.getPassword())){
                        canLogin = true;
                        loggedNurse = nurse;

                    }
                }

                if(canLogin && loggedNurse!=null){

                    //Shared preferences implementation
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("ID",loggedNurse.getId());
                    editor.putString("FIRSTNAME",loggedNurse.getFirstname());
                    editor.putString("LASTNAME",loggedNurse.getLastname());
                    editor.putString("DEPARTMENT",loggedNurse.getDepartment());
                    editor.putString("PASSWORD",loggedNurse.getPassword());
                    editor.putBoolean("REMEMBER_ME",chkRememberMe.isChecked());
                    editor.apply();

                    Intent data = new Intent();
                    data.putExtra("EXTRA_ID",loggedNurse.getId());
                    data.putExtra("EXTRA_FIRSTNAME",loggedNurse.getFirstname());
                    data.putExtra("EXTRA_LASTNAME",loggedNurse.getLastname());
                    data.putExtra("EXTRA_DEPARTMENT",loggedNurse.getDepartment());


                    setResult(RESULT_OK,data);
                    finish();

                    Toast.makeText(getApplicationContext(), "Login successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "cannot login", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}