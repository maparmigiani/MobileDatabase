package nurse;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Nurse.class}, version = 1)
public abstract class NurseDataBase extends RoomDatabase {
    private static NurseDataBase instance;

    public abstract NurseDao noteDao();

    public static synchronized NurseDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    NurseDataBase.class, "nurse_database")
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
        private NurseDao nurseDao;
        private PopulateDbAsyncTask(NurseDataBase db){
            nurseDao = db.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            nurseDao.insert(new Nurse("Sandra","Gonzalez","Oncology", "123456"));
            nurseDao.insert(new Nurse("Cesar","Leite","Intensive Therapy", "123456"));
            nurseDao.insert(new Nurse("Marcos","Silva","Rehabilitation ", "123456"));
            nurseDao.insert(new Nurse("Lolla","Ventura","UCI","123456"));
            nurseDao.insert(new Nurse("Vinicius","Medeiros","Cardiology","123456"));
            nurseDao.insert(new Nurse("Milena","Lipovsky","Neurology","123456"));
            nurseDao.insert(new Nurse("Karin","Lindsey","Maternity","123456"));








            return null;
        }
    }
}
