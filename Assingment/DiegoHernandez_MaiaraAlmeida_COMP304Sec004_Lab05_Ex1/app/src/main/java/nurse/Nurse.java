package nurse;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nurse_table")
public class Nurse {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstname;
    private String lastname;
    private String department;
    private String password;

    public Nurse(String firstname, String lastname, String department, String password) {

        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.password = password;
    }



    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getDepartment() {
        return department;
    }

    public String getPassword() {
        return password;
    }

    @NonNull
    @Override
    public String toString() {
        return id + " "+ firstname+ " "+lastname+" "+department+" "+password;
    }
}
