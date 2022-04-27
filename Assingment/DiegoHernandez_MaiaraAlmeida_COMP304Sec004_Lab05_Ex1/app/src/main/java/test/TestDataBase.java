package test;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Test.class}, version = 1)
public abstract class TestDataBase extends RoomDatabase {
    private static TestDataBase instance;

    public abstract TestDao testDao();

    public static synchronized TestDataBase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    TestDataBase.class, "test_database")
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
        private TestDao testDao;
        private PopulateDbAsyncTask(TestDataBase db){
            testDao = db.testDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            testDao.insert(new Test(1,1,130,7,37.3));
            testDao.insert(new Test(1,1,140,14,36.4));
            testDao.insert(new Test(1,1,160,20,38.1));
            return null;
        }
    }
}
