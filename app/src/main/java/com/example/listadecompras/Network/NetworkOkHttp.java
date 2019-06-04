package com.example.listadecompras.Network;

import android.content.Context;
import com.github.rodlibs.persistencecookie.PersistentCookieStore;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetworkOkHttp {

    private static final String URL_APPLICATION = "https://listadecompras-unibratec.herokuapp.com/";
    private static final String QUERY_LOGIN = URL_APPLICATION + "users/sign_in.json";
    private static final String QUERY_REGISTRE = URL_APPLICATION + "admin/users.json";
    private static final String QUERY_PRODUCTS = URL_APPLICATION + "product_lists.json";
    private static final String QUERY_SETTING = URL_APPLICATION + "global_settings/";
    private static final String QUERY_ID_SETTING = "4";
    private static final String QUERY_SETTING2 = QUERY_SETTING + QUERY_ID_SETTING;
//    private static final String QUERY_RECOVER_PASSWORD = URL_APPLICATION + "users/password.json";
//    private static final String QUERY_UPDATE = URL_APPLICATION + "users.json";

    private Context context;
    PersistentCookieStore myCookie;

    public NetworkOkHttp(Context context) {
        this.context = context;
    }

    public void login(String email, String password, final HttpCallback cb) {
        final String json = "{\"user\":{" +
                "\"email\":\"" + email + "\"," +
                "\"password\":\"" + password + "\"}}";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        if (myCookie == null) {
            myCookie = new PersistentCookieStore(context);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30000,TimeUnit.MILLISECONDS)
                .writeTimeout(30000,TimeUnit.MILLISECONDS)
                .readTimeout(30000,TimeUnit.MILLISECONDS).build();


        Request request = new Request.Builder()
                .url(QUERY_LOGIN)
                .post(body)
                .build();


        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!call.isCanceled()) {
                    cb.onFailure(null, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    cb.onSuccess(response.body().string());
                } else {
                    cb.onFailure(response.body().string(), null);
                }
            }
        });
    }

    public void products(String list_name, String product_name, int quantity, String measure, final HttpCallback cb) {
        final String json = "{\"user\":{" +
                "\"list_name\":\"" + list_name + "\"," +
                "\"product_name\":\"" + product_name + "\"," +
                "\"quantity\":\"" + quantity + "\"," +
                "\"measure\":\"" + measure + "\"}}";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        if (myCookie == null) {
            myCookie = new PersistentCookieStore(context);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30000,TimeUnit.MILLISECONDS)
                .writeTimeout(30000,TimeUnit.MILLISECONDS)
                .readTimeout(30000,TimeUnit.MILLISECONDS).build();


        Request request = new Request.Builder()
                .url(QUERY_PRODUCTS)
                .post(body)
                .build();


        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!call.isCanceled()) {
                    cb.onFailure(null, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    cb.onSuccess(response.body().string());
                } else {
                    cb.onFailure(response.body().string(), null);
                }
            }
        });
    }

    public void signin(String name, String email, String password, String password_confirmation, final HttpCallback cb) {
        final String json = "{\"user\":{" +
                "\"name\":\"" + name + "\"," +
                "\"email\":\"" + email + "\"," +
                "\"password\":\"" + password + "\"," +
                "\"password_confirmation\":\"" + password_confirmation + "\"}}";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        if (myCookie == null) {
            myCookie = new PersistentCookieStore(context);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30000,TimeUnit.MILLISECONDS)
                .writeTimeout(30000,TimeUnit.MILLISECONDS)
                .readTimeout(30000,TimeUnit.MILLISECONDS).build();

        Request request = new Request.Builder()
                .url(QUERY_REGISTRE)
                .post(body)
                .build();

        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!call.isCanceled()) {
                    cb.onFailure(null, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    cb.onSuccess(response.body().string());
                } else {
                    cb.onFailure(response.body().string(), null);
                }
            }
        });
    }

    public void settings(boolean singlelist, final HttpCallback cb) {
        final String json = "{\"single_list\":{" +
                "\"single_list_cenario\":" + singlelist + "}}";

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, json);
        if (myCookie == null) {
            myCookie = new PersistentCookieStore(context);
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(30000, TimeUnit.MILLISECONDS)
                .writeTimeout(30000, TimeUnit.MILLISECONDS)
                .readTimeout(30000, TimeUnit.MILLISECONDS).build();

        Request request = new Request.Builder()
                .url(QUERY_SETTING2)
                .put(body)
                .build();

        final Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (!call.isCanceled()) {
                    cb.onFailure(null, e);
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    cb.onSuccess(response.body().string());
                } else {
                    cb.onFailure(response.body().string(), null);
                }
            }
        });

    }

    public interface HttpCallback {
        void onSuccess(final String response);

        void onFailure(final String response, final Throwable throwable);
    }
}
