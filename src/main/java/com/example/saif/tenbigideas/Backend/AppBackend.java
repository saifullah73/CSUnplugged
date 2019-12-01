package com.example.saif.tenbigideas.Backend;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.saif.tenbigideas.AppListener;
import com.example.saif.tenbigideas.Person.FirebaseUserToPersonAdapter;
import com.example.saif.tenbigideas.Person.Person;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

public class AppBackend implements AppService {
    private final String TAG = "AppService:AppBackend";
    private FirebaseAuth mAuth;
    private List<AppListener> listeners;
    private Activity context;
    private AppListener listener;

    public AppBackend(Activity context){
        this.context = context;
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void signUp(String email, String password){

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Person person = new FirebaseUserToPersonAdapter(user);
                            for (AppListener listener: listeners)
                                listener.onSignUp(true,person);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.e(TAG, "createUserWithEmail:failure", task.getException());
                            for (AppListener listener: listeners)
                                listener.onSignUp(true,null);
                        }
                    }
                });
    }

    @Override
    public void addListeners(AppListener listener) {
        listeners.add(listener);
    }

    @Override
    public void removeListeners(AppListener listener) {
        try{
            listeners.remove(listener);
        }
        catch (Exception e){

        }
    }

    @Override
    public void signIn(String email, final String password) {
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(context, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Person person = new FirebaseUserToPersonAdapter(user);
                            for (AppListener listener: listeners)
                                listener.onSignIn(true,person);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            for (AppListener listener: listeners)
                                listener.onSignIn(true,null);
                        }
                    }
                });
    }

    @Override
    public boolean isCurrentUserAdmin() {
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            // this is a temporary implementation, in reality admin must be verified via secured tokens
            if(user.getDisplayName().equals("admin")){
                return true;
            }
        }
        return false;

    }

    @Override
    public void uploadVideo() {

    }

    @Override
    public void getVideo(int id) {

    }

    @Override
    public List<UserActivity> getUserActivity(String admintoken) {
        return null;
    }
}

