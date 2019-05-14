package com.example.listadecompras.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.listadecompras.R;

public class SignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }

    public void goSignIn(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
