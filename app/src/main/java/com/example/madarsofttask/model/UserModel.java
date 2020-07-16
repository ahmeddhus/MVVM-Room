package com.example.madarsofttask.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "UserData")
public class UserModel {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String name;
    private int age;
    private String jobTitle;
    private String gender;

    public UserModel(String name, int age, String jobTitle, String gender) {
        this.name = name;
        this.age = age;
        this.jobTitle = jobTitle;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
