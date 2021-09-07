package com.jesen.andokhttptalk;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void okHttpUse(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();

        Request request = new Request.Builder().url("https://www.baidu.com").build();

        Call call = okHttpClient.newCall(request);

        // 同步方法需要开启子线程
        try {
            Response response = call.execute();
            String string = response.body().string();
            InputStream stream = response.body().byteStream();
            Reader charStream = response.body().charStream();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 异步方法
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String string = response.body().string();
                InputStream stream = response.body().byteStream();
                Reader charStream = response.body().charStream();
            }
        });
    }
}