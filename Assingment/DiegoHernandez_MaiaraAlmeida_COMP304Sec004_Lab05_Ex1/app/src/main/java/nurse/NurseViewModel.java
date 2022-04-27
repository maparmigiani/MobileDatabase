package nurse;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NurseViewModel extends AndroidViewModel {

    private NurseRepository repository;
    private LiveData<List<Nurse>> allNurses;

    public NurseViewModel(@NonNull Application application) {
        super(application);

        repository = new NurseRepository(application);
        allNurses = repository.getAllNurses();
    }

    public void insert(Nurse nurse){
        repository.insert(nurse);
    }

    public void update(Nurse nurse){
        repository.update(nurse);
    }

    public void delete(Nurse nurse){
        repository.delete(nurse);
    }

    public void deleteAllNurses(){
        repository.deleteAllNurses();
    }

    public LiveData<List<Nurse>> getAllNurses(){
        return allNurses;
    }



}
