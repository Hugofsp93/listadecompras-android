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

public class SignUpActivity extends AppCompatActivity {

    private EditText editText3;
    private EditText editText7;
    private EditText editText5;
    private EditText editText6;
    private ProgressDialog progress;
    private NetworkOkHttp net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        editText3 = findViewById(R.id.editText3);
        editText7 = findViewById(R.id.editText7);
        editText5 = findViewById(R.id.editText5);
        editText6 = findViewById(R.id.editText6);

    }

    public void buttonSignUp(View v) {
        String name = editText3.getText().toString();
        String email = editText7.getText().toString();
        String password = editText5.getText().toString();
        String password_confirmation = editText6.getText().toString();

        if ((name.equals("")) || (email.equals("")) || (password.equals("")) || (password_confirmation.equals(""))) {
            Toast.makeText(this, "Verifique os campos em branco!", Toast.LENGTH_LONG).show();
        } else if ((!password.equals(password_confirmation))) {
            Toast.makeText(this, "Senha e confirmação de senha não estão iguais!", Toast.LENGTH_LONG).show();
        } else {
            progress = ProgressDialog.show(SignUpActivity.this, "", "Criando Usuário.", true, true);
            net = new NetworkOkHttp(SignUpActivity.this);
            net.signin(name, email, password, password_confirmation, new NetworkOkHttp.HttpCallback() {

                @Override
                public void onSuccess(String response) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            progress.dismiss();
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
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
                            Toast.makeText(SignUpActivity.this, "Não foi possível fazer o cadastro!", Toast.LENGTH_LONG).show();
                            progress.dismiss();
                        }
                    });
                }
            });
        }
    }

    public void goSignIn(View v){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}
