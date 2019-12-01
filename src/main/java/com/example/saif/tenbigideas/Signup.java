package com.example.saif.tenbigideas;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.saif.tenbigideas.Backend.AppService;
import com.example.saif.tenbigideas.Backend.ServiceFactory;
import com.example.saif.tenbigideas.Person.Person;
import com.google.android.material.textfield.TextInputEditText;

public class Signup extends AppCompatActivity implements View.OnClickListener {

    TextInputEditText name,email,password,confirmpassword,schoolI;
    Button signUp;
    TextView loginPage;
    private static Signup instance;
    ProgressBar progressBar;
    AppService service;
    AppListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name=findViewById(R.id.NametextInputEditText);
        email=findViewById(R.id.EmailtextInputEditText);
        password=findViewById(R.id.PasswordtextInputEditText);
        confirmpassword = findViewById(R.id.cc_signup);
        schoolI = findViewById(R.id.school_signup);
        signUp=findViewById(R.id.signupButton);
        progressBar = findViewById(R.id.signup_prog);
        signUp.setOnClickListener(this);
        instance = this;
        service = ServiceFactory.getService(Login.getInstance());
        listener = new AppListener() {
            @Override
            public void onSignUp(boolean success, Person person) {
                if (success)
                    Signup.getInstance().success(person);
                else
                    Signup.getInstance().failure(person);
            }

            @Override
            public void onSignIn(boolean success, Person person){
        }
                
        };
        service.addListeners(listener);


    }


    public static Signup getInstance(){
        if (instance==null){
            return new Signup();
        }
        return instance;
    }



    @Override
    public void onClick(View v) {

        if (v==signUp){
            Editable editName=(Editable) name.getText();
            String name=editName.toString().trim();

            Editable editUser=(Editable) email.getText();
            String user=editUser.toString().trim();

            Editable editPass=(Editable) password.getText();
            String password=editPass.toString().trim();

            Editable editConfirm=(Editable) confirmpassword.getText();
            String confirmPass=editConfirm.toString().trim();

            Editable schoolE=(Editable) schoolI.getText();
            String school=schoolE.toString().trim();


            if (name.length() ==0 || email.length() == 0 || school.length() == 0){
                Toast.makeText(this,"Please fill all fields",Toast.LENGTH_SHORT).show();
            }
            else if (password.length() == 0){
                Toast.makeText(this,"Please enter a password",Toast.LENGTH_SHORT).show();
            }
            else if(!password.equals(confirmPass)){
                Toast.makeText(this,"Password do not match",Toast.LENGTH_SHORT).show();
            }
            else {
                signUp.setEnabled(false);
                hideKeyboard(instance);
                progressBar.setVisibility(View.VISIBLE);
                service.signUp(user,password);
                //Person person = new Person(name,user,password,school);
//                DataBaseManager dataBaseManager = DataBaseManager.DataBaseManagerFactory.getDataBaseManager(getInstance());
//                dataBaseManager.checkAndInsert(person);
            }
        }
    }
    public void success(Person person){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                Log.i("Signup", "run: auth done and connected successfully" );
                Toast.makeText(getApplication(),"Account Created Successfully",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(Signup.this, Login.class);
                startActivity(i);
                finish();
            }
        });

    }

    public void failure(Person person){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progressBar.setVisibility(View.GONE);
                signUp.setEnabled(true);
                Toast.makeText(getApplicationContext(),"Error creating account",Toast.LENGTH_SHORT).show();
                //Toast.makeText(getApplicationContext(),s,Toast.LENGTH_SHORT).show();
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
