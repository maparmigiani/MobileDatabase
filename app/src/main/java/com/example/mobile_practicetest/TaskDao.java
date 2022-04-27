package com.example.mobile_practicetest;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {
    @Insert
    void insertTask(Task task);
    //
    @Update
    void updateTask(Task task);
    //
    @Query("SELECT * FROM Task WHERE taskId = :taskId")
    List<Task> findTask(int taskId);
    //
    @Query("DELETE FROM Task WHERE taskId = :taskId")
    //
    void deleteTask(int taskId);
    //
    @Query("SELECT * FROM Task")
    LiveData<List<Task>> getAllTasks();
}

