package patient;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "patient_table")
public class Patient {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String firstname;
    private String lastname;
    private String department;
    private int nurseId;
    private int room;

    public Patient(String firstname, String lastname, String department, int nurseId, int room) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.nurseId = nurseId;
        this.room = room;
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

    public int getNurseId() {
        return nurseId;
    }

    public int getRoom() {
        return room;
    }

    @Override
    public String toString() {
        return "Patient{" +
                "id=" + id +
                ", firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", department='" + department + '\'' +
                ", nurseId=" + nurseId +
                ", room=" + room +
                '}';
    }
}
