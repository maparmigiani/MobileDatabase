package nurse;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NurseDao {

    @Insert
    void insert(Nurse nurse);

    @Update
    void update(Nurse nurse);

    @Delete
    void delete(Nurse nurse);

    @Query("Delete FROM nurse_table")
    void deleteAllNurses();

    @Query("SELECT * FROM nurse_table ORDER BY id DESC")
    LiveData <List<Nurse>> getAllNurses();


}
