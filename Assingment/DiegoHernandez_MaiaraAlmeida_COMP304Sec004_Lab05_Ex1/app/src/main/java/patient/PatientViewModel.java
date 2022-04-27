package patient;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class PatientViewModel extends AndroidViewModel {

    private PatientRepository repository;
    private LiveData<List<Patient>> allPatients;

    public PatientViewModel(@NonNull Application application) {
        super(application);

        repository = new PatientRepository(application);
        allPatients = repository.getAllPatients();
    }

    public void insert(Patient patient){
        repository.insert(patient);
    }

    public void update(Patient patient){
        repository.update(patient);
    }

    public void delete(Patient patient){
        repository.delete(patient);
    }

    public void deleteAllPatients(){
        repository.deleteAllPatients();
    }

    public LiveData<List<Patient>> getAllPatients(){
        return allPatients;
    }



}
