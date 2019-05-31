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
    private static final String QUERY_SETTING = URL_APPLICATION + "global_settings/4/edit.json";
//    private static final String QUERY_RECOVER_PASSWORD = URL_APPLICATION + "users/password.json";
//    private static final String QUERY_UPDATE = URL_APPLICATION + "users.json";

    private Context context;
    private PersistentCookieStore myCookie;

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

    public void settings(boolean singlelist, final HttpCallback cb){
        final String json = "{\"global_setting\":{" +
                "\"single_list\":\"" + singlelist + "\"}}";

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
                .url(QUERY_SETTING)
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

//    public void update(Usuario user, String senhaAtual, final HttpCallback cb) {
//        mDialog.show();
//
//        MediaType MEDIA_TYPE_PNG = MediaType.parse("image/jpg");
//        MultipartBuilder multiPart = new MultipartBuilder();
//        multiPart.type(MultipartBuilder.FORM);
//        if (user.getPhone1() != null) {
//            if (user.getPhone1().equals("")) {
//            } else {
//                multiPart.addFormDataPart("user[phone1]", user.getPhone1());
//            }
//        }
//        if (user.getPhone2() != null) {
//            if (user.getPhone2().equals("")) {
//            } else {
//                multiPart.addFormDataPart("user[phone2]", user.getPhone2());
//            }
//        }
//        multiPart.addFormDataPart("user[current_password]", senhaAtual);
//
//        if (user.getPassword() != null) {
//            if (user.getPassword().equals("")) {
//            } else {
//                multiPart.addFormDataPart("user[password]", user.getPassword());
//            }
//        }
//
//        if (user.getPassword_confirmation() != null) {
//            if (user.getPassword_confirmation().equals("")) {
//            } else {
//                multiPart.addFormDataPart("user[password_confirmation]", user.getPassword_confirmation());
//            }
//        }
//
//        Log.i("FOTO", "" + user.getProfile_picture());
//        if ((user.getProfile_picture() != null) && (!(user.getProfile_picture().equals("null")))) {
//            try {
//                if (user.getProfile_picture().matches(".*" + "https:" + ".*") || user.getProfile_picture().matches(".*" + "/picture1s" + ".*") || user.getProfile_picture().matches(".*" + "thumb/missing" + ".*")) {
//                } else {
//                    multiPart.addFormDataPart("user[profile_picture]", "imagem.jpg", RequestBody.create(MEDIA_TYPE_PNG, new File(user.getProfile_picture())));
//                }
//            } catch (NullPointerException e) {
//            }
//        }
//
//
//        if (myCookie == null) {
//            myCookie = new PersistentCookieStore(context);
//        }
//        OkHttpClient client = new OkHttpClient();
//        client.setConnectTimeout(30, TimeUnit.SECONDS);
//        client.setWriteTimeout(30, TimeUnit.SECONDS);
//        client.setCookieHandler(new CookieManager(myCookie, CookiePolicy.ACCEPT_ALL));
//
//        RequestBody requestBody = multiPart
//                .build();
//        Request request = new Request.Builder()
//                .url(QUERY_UPDATE)
//                .put(requestBody)
//                .build();
//
//
//        final Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                mDialog.dismiss();
//                if (!call.isCanceled()) {
//                    cb.onFailure(null, e);
//                }
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                mDialog.dismiss();
//                final String value = response.body().string();
//                if (response.isSuccessful()) {
//                    cb.onSuccess(value);
//
//                } else {
//                    cb.onFailure(value, null);
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                JSONObject jsonErro = new JSONObject(value);
//                                if (jsonErro.getString("error").equals("Sua sessão foi iniciada em outro local.") ||
//                                        jsonErro.getString("error").equals("authentication error")) {
//                                    novoLogin();
//                                }
//                            } catch (JSONException e) {
//                                dialogMessage("Erro ao atualizar dados.");
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
//
//    public void recoverPassword(String email, final HttpCallback cb) {
//        mDialog.show();
//        final String json = "{\"user\":{" +
//                "\"email\":\"" + email + "\"" + "}}";
//
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, json);
//        if (myCookie == null) {
//            myCookie = new PersistentCookieStore(context);
//        }
//        OkHttpClient client = new OkHttpClient();
//        client.setConnectTimeout(30, TimeUnit.SECONDS);
//        client.setWriteTimeout(30, TimeUnit.SECONDS);
//        client.setCookieHandler(new CookieManager(myCookie, CookiePolicy.ACCEPT_ALL));
//        try {
//            client.setSslSocketFactory(new TLSSocketFactory());
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        Request request = new Request.Builder()
//                .url(QUERY_RECOVER_PASSWORD)
//                .post(body)
//                .build();
//
//        final Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                mDialog.dismiss();
//                if (!call.isCanceled()) {
//                    cb.onFailure(null, e);
//                }
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                mDialog.dismiss();
//                if (response.isSuccessful()) {
//                    cb.onSuccess(response.body().string());
//                } else {
//                    cb.onFailure(response.body().string(), null);
//                }
//            }
//        });
//    }
//
//
//    public void sendToken(String token) {
//        final String json = "{" +
//                "\"token\":\"" + token + "\"," +
//                "\"family\":\"" + "android-fcm-data" + "\"" +
//                "}";
//
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        RequestBody body = RequestBody.create(JSON, json);
//
//        if (myCookie == null) {
//            myCookie = new PersistentCookieStore(context);
//        }
//        OkHttpClient client = new OkHttpClient();
//        client.setConnectTimeout(30, TimeUnit.SECONDS);
//        client.setCookieHandler(new CookieManager(myCookie, CookiePolicy.ACCEPT_ALL));
//        try {
//            client.setSslSocketFactory(new TLSSocketFactory());
//        } catch (KeyManagementException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//
//        Request request = new Request.Builder()
//                .url(QUERY_SEND_TOKEN)
//                .post(body)
//                .build();
//
//        final Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, IOException e) {
//                if (!call.isCanceled()) {
//                }
//            }
//
//            @Override
//            public void onResponse(Response response) throws IOException {
//                final String value = response.body().string();
//                if (response.isSuccessful()) {
//                    Log.i("TOKEN", value);
//                } else {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            try {
//                                JSONObject jsonErro = new JSONObject(value);
//                                if (jsonErro.getString("error").equals("Sua sessão foi iniciada em outro local.") ||
//                                        jsonErro.getString("error").equals("authentication error")) {
//                                    novoLogin();
//                                }
//                            } catch (JSONException e) {
//                                dialogMessage("Erro ao atualizar dados.");
//                            }
//                        }
//                    });
//                }
//            }
//        });
//    }
//
//
//
//    public void dialog(final String message){
//        AlertDialog.Builder alert = new AlertDialog.Builder(context);
//        alert.setMessage(message);
//        alert.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                dialogInterface.dismiss();
//            }
//        });
//        alert.create();
//        alert.show();
//    }
//
//    void closeDlg(final AlertDialog dlg) {
//        Timer autoUpdate = new Timer();
//        autoUpdate.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        dlg.dismiss();
//                    }
//                });
//            }
//        }, 0, 10000);
//    }
//
//    public void isConnectionNetwork(final HttpCallback cb) {
//        OkHttpClient client = new OkHttpClient();
//        client.setConnectTimeout(4, TimeUnit.SECONDS);
//        client.setWriteTimeout(4, TimeUnit.SECONDS);
//
//        Request request = new Request.Builder()
//                .url(QUERY_PING)
//                .head()
//                .build();
//
//        final Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Request request, final IOException e) {
//                if (!call.isCanceled()) {
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            cb.onFailure(null, e);
//                        }
//                    });
//                }
//            }
//            @Override
//            public void onResponse(final Response response) throws IOException {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (response.isSuccessful()) {
//                            cb.onSuccess(""+response.code());
//                        } else {
//                            cb.onFailure(""+response.code(), null);
//                        }
//                    }
//                });
//            }
//        });
//    }





    public interface HttpCallback {
        void onSuccess(final String response);

        void onFailure(final String response, final Throwable throwable);
    }
}
