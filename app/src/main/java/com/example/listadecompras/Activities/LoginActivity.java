package com.example.listadecompras.Activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.example.listadecompras.MainActivity;
import com.example.listadecompras.Network.NetworkOkHttp;
import com.example.listadecompras.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtPassword;
    private ProgressDialog progress;
    private NetworkOkHttp net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);

    }


    public void buttonLogin(View v) {
        String email = edtEmail.getText().toString();
        String password = edtPassword.getText().toString();

        if ((email.equals("")) && (password.equals(""))) {
            Toast.makeText(this, "Verifique os campos em branco!", Toast.LENGTH_LONG).show();
        } else {
            progress = ProgressDialog.show(LoginActivity.this, "", "Autenticando Usuário.", true, true);
            net = new NetworkOkHttp(LoginActivity.this);
            net.login(email, password, new NetworkOkHttp.HttpCallback() {

                @Override
                public void onSuccess(String response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progress.dismiss();
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
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
                            Toast.makeText(LoginActivity.this, "Não foi possível fazer o login!", Toast.LENGTH_LONG).show();
                            progress.dismiss();
                        }
                    });
                }
            });
        }
    }

    public void goSignUp (View v){
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}

