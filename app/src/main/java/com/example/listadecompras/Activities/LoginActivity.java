package com.example.listadecompras.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.listadecompras.MainActivity;
import com.example.listadecompras.R;

public class LoginActivity extends AppCompatActivity  {

//    private EditText edtEmail;
//    private EditText edtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


//        edtEmail = findViewById(R.id.edtEmail);
//        edtPassword =  findViewById(R.id.edtPassword);

    }

    public void buttonLogin(View v){
//        String email = edtEmail.getText().toString();
//        String password = edtPassword.getText().toString();
//        if ((!email.isEmpty()) && (!password.isEmpty())){
//
//
//        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void goSignUp(View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }



    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }



}

