package com.jesen.custom_okhttp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.jesen.custom_okhttp.custom.Call2;
import com.jesen.custom_okhttp.custom.Callback2;
import com.jesen.custom_okhttp.custom.OkHttpClient2;
import com.jesen.custom_okhttp.custom.Request2;
import com.jesen.custom_okhttp.custom.Response2;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    private static final String URL_TEST = "http://restapi.amap.com/v3/weather/weatherInfo?\n" +
            "city=110101&key=13cb58f5884f9749287abbead9c658f2";

    private Button okHttpBtn, customOkHttpBtn;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        okHttpBtn = findViewById(R.id.okhttp);
        customOkHttpBtn = findViewById(R.id.custom_okhttp);
        result = findViewById(R.id.result);

        // 官方实现
        okHttpBtn.setOnClickListener(view -> {
            OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

            Request request = new Request.Builder().url(URL_TEST).build();
            Call call = okHttpClient.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d("OKHTTP", "onFailure e:" + e.toString());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String ret = response.body().string();

                    Log.d("OKHTTP", "onResponse, body:" + ret);
                    updateUI(ret);
                }
            });
        });

        // 使用自实现
        customOkHttpBtn.setOnClickListener(view -> {
            OkHttpClient2 okHttpClient2 = new OkHttpClient2.Builder2().build2();

            Request2 request2 = new Request2.Builder2().url2(URL_TEST).build2();
            Call2 call2 = okHttpClient2.newCall(request2);
            call2.enqueue2(new Callback2() {
                @Override
                public void onFailure2(Call2 call, IOException e) {
                    Log.d("OKHTTP", "onFailure e:" + e.toString());
                }

                @Override
                public void onResponse2(Call2 call, Response2 response) {
                    String ret = response.string();

                    Log.d("OKHTTP", "onResponse, body:" + ret);
                    updateUI(ret);
                }
            });
        });
    }


    private void updateUI(String ret) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                result.setText(ret);
            }
        });
    }
}