package com.example.z.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Window;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onResume() {
        super.onResume();
        Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                startLoginActivity();
//            }
//        },1000);

        handler.postDelayed(new Runnable() {
            public void run() {
                useOkHttp();
            }
        }, 1000);

    }

    public void startLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    //请求
    void useOkHttp(){
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder()
                .url("http://169.254.80.80:8080/membercenter/api/hello")
                .method("GET",null)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        startLoginActivity();
                    }
                });
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                Log.d("response", response.toString());
                MainActivity.this.runOnUiThread(new Runnable() {
                    public void run() {
                        try {
                            Toast.makeText(MainActivity.this, response.body().string(), Toast.LENGTH_SHORT).show();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        startLoginActivity();
                    }
                });
            }
        });
    }
}
