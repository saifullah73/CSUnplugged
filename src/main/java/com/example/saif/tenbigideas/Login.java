package com.example.saif.tenbigideas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saif.tenbigideas.Backend.AppService;
import com.example.saif.tenbigideas.Backend.ServiceFactory;
import com.example.saif.tenbigideas.Person.Person;

public class Login extends AppCompatActivity {

    EditText passwordView,emailView;
    Button loginBtn;
    ProgressBar progressBar;
    TextView signup;
    AppService service;
    AppListener listener;
    private static Login instance;
    private static final String TAG = "Login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        passwordView = findViewById(R.id.password);
        emailView = findViewById(R.id.email);
        loginBtn = findViewById(R.id.loginBtn);
        progressBar = findViewById(R.id.progressBar);
        signup=findViewById(R.id.createOne);
        instance = this;
        service = ServiceFactory.getService(Login.getInstance());
        listener = new AppListener() {
            @Override
            public void onSignUp(boolean success, Person person) {

            }

            @Override
            public void onSignIn(boolean success, Person person) {
                if (success)
                    Login.getInstance().success();
                else
                    Login.getInstance().failure();
            }
        };
        service.addListeners(listener);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //notification.displayNotification();
                if (perfromCheck()){
                    progressBar.setVisibility(View.VISIBLE);
                    loginBtn.setEnabled(false);
                    hideKeyboard(instance);
                    final String email = emailView.getText().toString().trim();
                    final String password = passwordView.getText().toString().trim();
                    service.signIn(email,password);
                }
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(Login.this,Signup.class);
                startActivity(i);
            }
        });
    }

    public static Login getInstance(){
        if (instance==null){
            return new Login();
        }
        return instance;
    }


    private boolean perfromCheck(){
        if(emailView.getText().length() ==0) {
            Toast.makeText(this, "email can't be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        else if (passwordView.getText().length() == 0) {
            Toast.makeText(this, "Password cannot be empty", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    public void success(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Log.e(TAG, "run: auth done and connected successfully" );
                Intent i = new Intent(Login.this, PostListActivity.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void failure(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                loginBtn.setEnabled(true);
                Toast.makeText(getApplicationContext(),"Cannot login",Toast.LENGTH_SHORT).show();
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (service != null && listener != null)
            service.removeListeners(listener);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (service != null && listener != null)
            service.addListeners(listener);

    }
}
