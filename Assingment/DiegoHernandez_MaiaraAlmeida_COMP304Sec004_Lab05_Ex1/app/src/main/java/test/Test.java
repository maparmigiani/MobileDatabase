package test;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "test_table")
public class Test {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int patientId;
    private int nurseId;
    private int bpl;
    private int bph;
    private double temperature;

    public Test(int patientId, int nurseId, int bpl, int bph, double temperature) {
        this.patientId = patientId;
        this.nurseId = nurseId;
        this.bpl = bpl;
        this.bph = bph;
        this.temperature = temperature;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getPatientId() {
        return patientId;
    }

    public int getNurseId() {
        return nurseId;
    }

    public int getBpl() {
        return bpl;
    }

    public int getBph() {
        return bph;
    }

    public double getTemperature() {
        return temperature;
    }

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", patientId=" + patientId +
                ", nurseId=" + nurseId +
                ", bpl=" + bpl +
                ", bph=" + bph +
                ", temperature=" + temperature +
                '}';
    }
}
