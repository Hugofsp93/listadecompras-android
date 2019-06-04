package com.example.listadecompras.Activities;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.listadecompras.MainActivity;
import com.example.listadecompras.Network.NetworkOkHttp;
import com.example.listadecompras.R;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ListActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private ProgressDialog progress;
//    private NetworkOkHttp net;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mTextViewResult = findViewById(R.id.product_lists);
        onProductLists();
    }

    public void onProductLists(){

        progress = ProgressDialog.show(ListActivity.this, "", "Carregando listas", true, true);
        OkHttpClient client = new OkHttpClient();

        String url = "https://listadecompras-unibratec.herokuapp.com/product_lists.json";

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();
        progress.dismiss();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()){
                    final String myResponse = response.body().string();
                    ListActivity.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mTextViewResult.setText(myResponse);
                        }
                    });
                }
            }
        });
    }
}
//        net = new NetworkOkHttp(MainActivity.this);
//        net.products(list_name, product_name, quantity, measure, new NetworkOkHttp.HttpCallback() {
//
//            @Override
//            public void onSuccess(String response) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        progress.dismiss();
//                    }
//                });
//            }
//
//            @Override
//            public void onFailure(String response, Throwable throwable) {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        Toast.makeText(MainActivity.this, "Não foi possível carregar suas listas!", Toast.LENGTH_LONG).show();
//                        progress.dismiss();
//                    }
//                });
//            }
//        });

