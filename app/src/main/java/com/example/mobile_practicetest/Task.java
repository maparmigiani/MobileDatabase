package com.example.mobile_practicetest;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Task {

    @PrimaryKey(autoGenerate = true)
    //private fields
    private int taskId;
    private String taskName, taskDescription;

    public Task(String taskName, String taskDescription) {
        this.taskName = taskName;
        this.taskDescription = taskDescription;
    }

    public String getTaskDescription() {return taskDescription;  }

    public void setTaskDescription(String taskDescription) {this.taskDescription = taskDescription;}

    public int getTaskId() { return taskId;}

    public void setTaskId(int taskId) {this.taskId = taskId; }

    public String getTaskName() {    return taskName; }

    public void setTaskName(String taskName) {this.taskName = taskName; }
    @NonNull
    @Override
    public String toString() {
        return "Id= " + taskId +
                ", Task Name= " + taskName +
                ", Task Description= " + taskDescription;
    }
}
