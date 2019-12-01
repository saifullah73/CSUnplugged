package com.example.saif.tenbigideas.Person;


import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;

public class FirebaseUserToPersonAdapter implements Person {
    FirebaseUser user;
    private String name;
    private String username;



    public FirebaseUserToPersonAdapter(FirebaseUser user) {
        setFields(user);
    }

    @Override
    public String getName() {
        return name;
    }

    @NonNull
    @Override
    public String getUsername() {
        return username;
    }


    @Override
    public String getSchool() {
        return null;
    }

    private void setFields(FirebaseUser user){
        name = user.getDisplayName();
        username = user.getEmail().substring(0,user.getEmail().indexOf("@"));
    }
}