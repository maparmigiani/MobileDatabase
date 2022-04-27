package com.example.mobile_practicetest;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import java.util.List;


public class TaskRepository {
    //
    private MutableLiveData<List<Task>> searchResults =
            new MutableLiveData<>();
    private LiveData<List<Task>> allTasks;
    private TaskDao taskDao;

    //
    public TaskRepository(Application application) {
        TaskRoomDatabase db = TaskRoomDatabase.getDatabase(application);
        taskDao = db.taskDao();
        allTasks = taskDao.getAllTasks();
    }
    //
    public void insertTask(Task task) {
        InsertAsyncTask insertTask = new InsertAsyncTask(taskDao);
        insertTask.execute(task);
    }
    public void deleteTask(int taskId) {
        DeleteAsyncTask deleteTask = new DeleteAsyncTask(taskDao);
        deleteTask.execute(taskId);
    }
    public void updateTask(Task task){
        UpdateAsyncTask updateTask = new UpdateAsyncTask(taskDao);
        updateTask.execute(task);
    }
    //
    public void findTask(int taskId) {
        QueryAsyncTask queryTask = new QueryAsyncTask(taskDao);
        queryTask.taskRepository = this;
        queryTask.execute(taskId);
    }
    // add methods that the ViewModel can call to obtain references
    // to the allTasks and searchResults live data objects:
    public LiveData<List<Task>> getAllTasks() {
        return allTasks;
    }
    public MutableLiveData<List<Task>> getSearchResults() {
        return searchResults;
    }
    // results of a search operation are stored whenever
    // an asynchronous search task completes
    private void asyncFinished(List<Task> results) {
        searchResults.setValue(results);
    }
    //
    private static class QueryAsyncTask extends
            AsyncTask<Integer, Void, List<Task>> {

        private TaskDao asyncTaskDao;
        private TaskRepository taskRepository = null;

        QueryAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected List<Task> doInBackground(final Integer... params) {
            return asyncTaskDao.findTask(params[0]);
        }

        @Override
        protected void onPostExecute(List<Task> result) {
            taskRepository.asyncFinished(result);
        }
    }
    // include the following AsyncTask implementation for
    // inserting tasks into the database
    private static class InsertAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao asyncTaskDao;

        InsertAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            asyncTaskDao.insertTask(params[0]);
            return null;
        }
    }
    // This is used when deleting tasks from the database
    private static class DeleteAsyncTask extends AsyncTask<Integer, Void, Void> {

        private TaskDao asyncTaskDao;

        DeleteAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Integer... params) {
            asyncTaskDao.deleteTask(params[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Task, Void, Void> {

        private TaskDao asyncTaskDao;

        UpdateAsyncTask(TaskDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Task... params) {
            asyncTaskDao.updateTask(params[0]);
            return null;
        }
    }
}


