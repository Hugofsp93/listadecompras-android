package com.example.listadecompras.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.listadecompras.MainActivity;
import com.example.listadecompras.Network.NetworkOkHttp;
import com.example.listadecompras.R;

public class SettingActivity extends AppCompatActivity {

    public Switch sw;
    private NetworkOkHttp net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sw = findViewById(R.id.sw);

    }

    public void buttonSettings(View v) {

        net = new NetworkOkHttp(SettingActivity.this);
        net.settings(sw.isChecked(), new NetworkOkHttp.HttpCallback() {

            @Override
            public void onSuccess(String response) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (sw.isChecked()){
                            sw.setChecked(true);
                        } else {
                            sw.setChecked(false);
                        }
                        Toast.makeText(SettingActivity.this, "Configurações salvas!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(String response, Throwable throwable) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(SettingActivity.this, "Alterações não foram salvas!", Toast.LENGTH_LONG).show();
                    }
                });
            }
        });
    }

}
