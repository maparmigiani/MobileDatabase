package test;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TestDao {

    @Insert
    void insert(Test note);

    @Update
    void update(Test note);

    @Delete
    void delete(Test note);

    @Query("Delete FROM test_table")
    void deleteAllTests();

    @Query("SELECT * FROM test_table ORDER BY id DESC")
    LiveData <List<Test>> getAllTests();

}
