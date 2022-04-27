package patient;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Patient.class}, version = 1)
public abstract class PatientDataBase extends RoomDatabase {
    private static PatientDataBase instance;

    public abstract PatientDao patientDao();

    public static synchronized PatientDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    PatientDataBase.class, "patient_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }

        return instance;

    }

    private static  Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,Void>
    {
        private PatientDao patientDao;
        private PopulateDbAsyncTask(PatientDataBase db){
            patientDao = db.patientDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            patientDao.insert(new Patient("Diego", "Hernandez", "Oncology",1,1));
            patientDao.insert(new Patient("Olga", "Lipovisky", "Cardiology",3,2));
            patientDao.insert(new Patient("Chennie", "Lindsey", "UCI",4,3));
            patientDao.insert(new Patient("Leandro", "Oliveira", "Cardiology",4,4));
            patientDao.insert(new Patient("Walter", "Salim", "Rehabilitation",3,5));
            patientDao.insert(new Patient("Munique", "Parmigiani", "Maternity",1,6));
            patientDao.insert(new Patient("Fernanda", "Sgroi", "Cardiology",6,7));
            patientDao.insert(new Patient("Vitor", "Cesar", "Neurology",5,8));
            patientDao.insert(new Patient("Tiemi", "Kinouti", "Maternity",7,5));
            return null;
        }
    }
}
