package com.example.saif.tenbigideas.Person;

import androidx.annotation.NonNull;



public class AdminUser implements Person {
    private Person person;
    private String admintoken;



    public AdminUser(Person person, String admintoken){
        this.person = person;
        this.admintoken = admintoken;
    }

    @Override
    public String getName(){
        return person.getName();
    }

    @NonNull
    @Override
    public String getUsername(){
        return person.getUsername();
    }

    @Override
    public String getSchool(){
        return person.getSchool();
    }

    public void setAdmintoken(String a){
        admintoken = a;
    }

    public String getAdmintoken() {
        return admintoken;
    }
}
