package com.example.saif.tenbigideas;


import com.example.saif.tenbigideas.Person.Person;

public interface AppListener {
    public void onSignUp(boolean success, Person person);
    public void onSignIn(boolean success, Person person);
}
