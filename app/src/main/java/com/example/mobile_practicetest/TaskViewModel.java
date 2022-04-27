package com.example.mobile_practicetest;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;


public class TaskViewModel extends AndroidViewModel {
    private TaskRepository repository;
    private LiveData<List<Task>> allTasks;
    private MutableLiveData<List<Task>> searchResults;

    //
    public TaskViewModel(Application application) {
        super(application);
        repository = new TaskRepository(application);
        allTasks = repository.getAllTasks();
        searchResults = repository.getSearchResults();
    }
    //
    //implement the methods that will be called from
    // within the UI controller in response to button
    // clicks and when setting up observers on the
    // LiveData objects

    MutableLiveData<List<Task>> getSearchResults() {
        return searchResults;
    }

    LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }

    public void insertTask(Task task) {
        repository.insertTask(task);
    }

    public void findTask(int taskId) {
        repository.findTask(taskId);
    }

    public void deleteTask(int taskId) {
        repository.deleteTask(taskId);
    }
    public void updateTask(Task task) {
        repository.updateTask(task);
    }

}

