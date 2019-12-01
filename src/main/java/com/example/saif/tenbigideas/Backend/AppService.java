package com.example.saif.tenbigideas.Backend;


import com.example.saif.tenbigideas.AppListener;


import java.util.List;

public interface AppService {
    public void signIn(String email, String password);
    public void signUp(String email, String password);
    public boolean isCurrentUserAdmin();
    public void addListeners(AppListener listener);
    public void removeListeners(AppListener listener);
    public void uploadVideo();
    public void getVideo(int id);
    public List<UserActivity> getUserActivity(String admintoken);
}
