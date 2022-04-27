package nurse;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NurseRepository {
    private NurseDao nurseDao;
    private LiveData<List<Nurse>> allNurses;

    public NurseRepository(Application application) {
        NurseDataBase dataBase = NurseDataBase.getInstance(application);
        nurseDao = dataBase.noteDao();
        allNurses= nurseDao.getAllNurses();
    }

    public void insert(Nurse nurse){
        new InsertNurseAsyncTask(nurseDao).execute(nurse);

    }

    public void update(Nurse nurse){
        new UpdateNurseAsyncTask(nurseDao).execute(nurse);
    }

    public void delete(Nurse nurse){
        new DeleteNurseAsyncTask(nurseDao).execute(nurse);
    }

    public void deleteAllNurses(){
        new DeleteAllNursesAsyncTask(nurseDao).execute();
    }

    public LiveData<List<Nurse>> getAllNurses(){
        return allNurses;
    }

    private static class InsertNurseAsyncTask extends AsyncTask<Nurse, Void, Void>{

        private NurseDao nurseDao;

        private InsertNurseAsyncTask(NurseDao nurseDao){
            this.nurseDao = nurseDao;
        }


        @Override
        protected Void  doInBackground(Nurse... nurses) {
            nurseDao.insert(nurses[0]);
            return null;
        }
    }

    private static class UpdateNurseAsyncTask extends AsyncTask<Nurse, Void, Void>{

        private NurseDao nurseDao;

        private UpdateNurseAsyncTask(NurseDao nurseDao){
            this.nurseDao = nurseDao;
        }


        @Override
        protected Void  doInBackground(Nurse... nurses) {
            nurseDao.update(nurses[0]);
            return null;
        }
    }

    private static class DeleteNurseAsyncTask extends AsyncTask<Nurse, Void, Void>{

        private NurseDao nurseDao;

        private DeleteNurseAsyncTask(NurseDao nurseDao){
            this.nurseDao = nurseDao;
        }


        @Override
        protected Void  doInBackground(Nurse... nurses) {
            nurseDao.delete(nurses[0]);
            return null;
        }
    }

    private static class DeleteAllNursesAsyncTask extends AsyncTask<Nurse, Void, Void>{

        private NurseDao nurseDao;

        private DeleteAllNursesAsyncTask(NurseDao nurseDao){
            this.nurseDao = nurseDao;
        }


        @Override
        protected Void  doInBackground(Nurse... nurses) {
            nurseDao.deleteAllNurses();
            return null;
        }
    }


}
