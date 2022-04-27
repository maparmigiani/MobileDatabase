package test;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TestRepository {
    private TestDao testDao;
    private LiveData<List<Test>> allTests;

    public TestRepository(Application application) {
        TestDataBase dataBase = TestDataBase.getInstance(application);
        testDao = dataBase.testDao();
        allTests = testDao.getAllTests();
    }

    public void insert(Test test){
        new InsertTestAsyncTask(testDao).execute(test);

    }

    public void update(Test test){
        new UpdateTestAsyncTask(testDao).execute(test);
    }

    public void delete(Test test){
        new DeleteTestAsyncTask(testDao).execute(test);
    }

    public void deleteAllTest(){
        new DeleteAllTestAsyncTask(testDao).execute();
    }

    public LiveData<List<Test>> getAllTests(){
        return allTests;
    }

    private static class InsertTestAsyncTask extends AsyncTask<Test, Void, Void>{

        private TestDao testDao;

        private InsertTestAsyncTask(TestDao testDao){
            this.testDao=testDao;
        }


        @Override
        protected Void  doInBackground(Test... tests) {
            testDao.insert(tests[0]);
            return null;
        }
    }

    private static class UpdateTestAsyncTask extends AsyncTask<Test, Void, Void>{

        private TestDao testDao;

        private UpdateTestAsyncTask(TestDao testDao){
            this.testDao=testDao;
        }


        @Override
        protected Void  doInBackground(Test... tests) {
            testDao.update(tests[0]);
            return null;
        }
    }

    private static class DeleteTestAsyncTask extends AsyncTask<Test, Void, Void>{

        private TestDao testDao;

        private DeleteTestAsyncTask(TestDao testDao){
            this.testDao=testDao;
        }


        @Override
        protected Void  doInBackground(Test... tests) {
            testDao.delete(tests[0]);
            return null;
        }
    }

    private static class DeleteAllTestAsyncTask extends AsyncTask<Test, Void, Void>{

        private TestDao testDao;

        private DeleteAllTestAsyncTask(TestDao testDao){
            this.testDao=testDao;
        }


        @Override
        protected Void  doInBackground(Test... tests) {
            testDao.deleteAllTests();
            return null;
        }
    }


}
