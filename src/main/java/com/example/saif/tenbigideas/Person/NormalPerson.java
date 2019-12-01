package com.example.saif.tenbigideas.Person;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


//@Entity(tableName = "person")
public class NormalPerson implements Person {
    private String name;
//    @PrimaryKey
//    @NonNull
    private String username;
    private String school;

    @Override
    public String getName() {
        return name;
    }

    @Override
    //@NonNull
    public String getUsername() {
        return username;
    }


    @Override
    public String getSchool() {
        return school;
    }


    public NormalPerson(String name, String username, String school) {
        this.name = name;
        this.username = username;
        this.school = school;
    }
}
