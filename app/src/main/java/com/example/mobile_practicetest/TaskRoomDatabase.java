package com.example.mobile_practicetest;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Task.class}, version = 1)
public abstract class TaskRoomDatabase extends RoomDatabase {
    //
    public abstract TaskDao taskDao();
    private static TaskRoomDatabase INSTANCE;

    //
    public static synchronized TaskRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TaskRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE =
                            Room.databaseBuilder(context.getApplicationContext(),
                                    TaskRoomDatabase.class,
                                    "TaskDB").addCallback(roomCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static  Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void,Void>
    {
        private TaskDao taskDao;
        private PopulateDbAsyncTask(TaskRoomDatabase db){
            taskDao = db.taskDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            taskDao.insertTask(new Task("Test1","Test1"));
            taskDao.insertTask(new Task("Test2","Test2"));
            taskDao.insertTask(new Task("Test3","Test3"));
            taskDao.insertTask(new Task("Test4","Test4"));
            taskDao.insertTask(new Task("Test5","Test5"));
            return null;
        }
    }
}//

