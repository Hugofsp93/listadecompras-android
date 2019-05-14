package com.example.listadecompras.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.listadecompras.MainActivity;
import com.example.listadecompras.R;
import com.github.rodlibs.persistencecookie.PersistentCookieStore;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);




        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                loadCookie();
            }
        }, 2000);
    }





    private void loadCookie(){
        PersistentCookieStore myCookieStore = new PersistentCookieStore(SplashActivity.this);
        if (!myCookieStore.getCookies().isEmpty()) {

            if (myCookieStore.getCookies().get(0).getDomain().equals("listadecompras-unibratec.herokuapp.com")) {
                startActivity(new Intent(SplashActivity.this, MainActivity.class));
                finish();
            }
            else {
                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                finish();
            }
        }
        else {
            startActivity(new Intent(SplashActivity.this, LoginActivity.class));
            finish();
        }
    }
}
