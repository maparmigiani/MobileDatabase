package test;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class TestViewModel extends AndroidViewModel {

    private TestRepository repository;
    private LiveData<List<Test>> allTest;

    public TestViewModel(@NonNull Application application) {
        super(application);

        repository = new TestRepository(application);
        allTest = repository.getAllTests();
    }

    public void insert(Test test){
        repository.insert(test);
    }

    public void update(Test test){
        repository.update(test);
    }

    public void delete(Test test){
        repository.delete(test);
    }

    public void deleteAllTest(){
        repository.deleteAllTest();
    }

    public LiveData<List<Test>> getAllTest(){
        return allTest;
    }



}
