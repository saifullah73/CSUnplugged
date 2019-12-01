package com.example.saif.tenbigideas.Backend;

import android.app.Activity;
import android.widget.LinearLayout;


import com.example.saif.tenbigideas.AppListener;
import java.util.List;



public class ServiceProxy implements AppService {
    private AppBackend appBackend;



    public ServiceProxy(Activity activity) {
        appBackend = new AppBackend(activity);
    }

    @Override
    public void addListeners(final AppListener listener) {
        appBackend.addListeners(listener);
    }

    @Override
    public void removeListeners(AppListener listener) {
        appBackend.removeListeners(listener);
    }

    @Override
    public void signIn(String email, String password) {
        if (email != null && !email.equals(""))
            appBackend.signIn(email,password);
    }

    @Override
    public void signUp(String email, String password) {
        if (email != null && !email.equals("") && password != null && !password.equals(""))
            appBackend.signUp(email,password);
    }

    @Override
    public boolean isCurrentUserAdmin() {
        return appBackend.isCurrentUserAdmin();
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
