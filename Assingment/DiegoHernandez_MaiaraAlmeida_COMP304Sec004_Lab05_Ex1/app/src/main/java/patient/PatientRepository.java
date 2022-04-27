package patient;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class PatientRepository {
    private PatientDao patientDao;
    private LiveData<List<Patient>> allPatients;

    public PatientRepository(Application application) {
        PatientDataBase dataBase = PatientDataBase.getInstance(application);
        patientDao = dataBase.patientDao();
        allPatients = patientDao.getAllPatients();
    }

    public void insert(Patient patient){
        new InsertPatientAsyncTask(patientDao).execute(patient);

    }

    public void update(Patient patient){
        new UpdatePatientAsyncTask(patientDao).execute(patient);

    }

    public void delete(Patient patient){
        new DeletePatientAsyncTask(patientDao).execute(patient);
    }

    public void deleteAllPatients(){
        new DeletePatientAsyncTask(patientDao).execute();
    }

    public LiveData<List<Patient>> getAllPatients(){
        return allPatients;
    }

    private static class InsertPatientAsyncTask extends AsyncTask<Patient, Void, Void>{

        private PatientDao patientDao;

        private InsertPatientAsyncTask(PatientDao patientDao){
            this.patientDao=patientDao;
        }


        @Override
        protected Void  doInBackground(Patient... patients) {
            patientDao.insert(patients[0]);
            return null;
        }
    }

    private static class UpdatePatientAsyncTask extends AsyncTask<Patient, Void, Void>{

        private PatientDao patientDao;

        private UpdatePatientAsyncTask(PatientDao patientDao){
            this.patientDao=patientDao;
        }


        @Override
        protected Void  doInBackground(Patient... patients) {
            patientDao.update(patients[0]);
            return null;
        }
    }

    private static class DeletePatientAsyncTask extends AsyncTask<Patient, Void, Void>{

        private PatientDao patientDao;

        private DeletePatientAsyncTask(PatientDao patientDao){
            this.patientDao=patientDao;
        }


        @Override
        protected Void  doInBackground(Patient... patients) {
            patientDao.delete(patients[0]);
            return null;
        }
    }

    private static class DeleteAllPatientsAsyncTask extends AsyncTask<Patient, Void, Void>{

        private PatientDao patientDao;

        private DeleteAllPatientsAsyncTask(PatientDao patientDao){
            this.patientDao=patientDao;
        }


        @Override
        protected Void  doInBackground(Patient... patients) {
            patientDao.deleteAllPatients();
            return null;
        }
    }


}
