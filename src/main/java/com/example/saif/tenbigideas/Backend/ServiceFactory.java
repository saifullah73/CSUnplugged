package com.example.saif.tenbigideas.Backend;

import android.app.Activity;

public class ServiceFactory {
    private static AppBackend instance;
    public static AppService getService(Activity activity){
        if (instance == null) {
            instance = new AppBackend(activity);
        }
        return instance;
    }
}
