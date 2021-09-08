package com.jesen.custom_okhttp.custom;

import android.util.Log;

import com.jesen.custom_okhttp.custom.chain.ChainManager;
import com.jesen.custom_okhttp.custom.chain.Interceptor2;
import com.jesen.custom_okhttp.custom.chain.ReRequestInterceptor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class RealCall2 implements Call2 {

    // 标记位
    private boolean executed;
    private OkHttpClient2 client2;
    private Request2 request2;

    public RealCall2(OkHttpClient2 okHttpClient2, Request2 request2) {
        this.client2 = okHttpClient2;
        this.request2 = request2;
    }

    @Override
    public void enqueue2(Callback2 responseCallback) {
        synchronized (this) {
            if (executed) {
                executed = true;
                throw new IllegalStateException("不允许重复执行");
            }
        }

        client2.dispatcher2().enqueue(new AsyncCall2(responseCallback));
    }

    public OkHttpClient2 getOkHttpClient2() {
        return client2;
    }

    final class AsyncCall2 implements Runnable {
        private Callback2 callback2;

        public AsyncCall2(Callback2 responseCallback) {
            callback2 = responseCallback;
        }

         public Request2 getRequest2() {
             return RealCall2.this.request2;
         }

        @Override
        public void run() {
            // 执行耗时操作
            boolean signalledCallback = false;
            try {
                Response2 response2 = getResponseWithInterceptorChain();
                // 如果用户取消了请求，回调给用户，说失败了
                if (client2.getCanceled()) {
                    signalledCallback = true;
                    callback2.onFailure2(RealCall2.this, new IOException("用户取消了 Canceled"));
                } else {
                    signalledCallback = true;
                    callback2.onResponse2(RealCall2.this, response2);
                }

            } catch (Exception e){
                // 责任划分
                if (signalledCallback){
                    Log.d("OKHTTP","用户使用错误");
                }else {
                    callback2.onFailure2(RealCall2.this, new IOException("OKHTTP getResponseWithInterceptorChain err:"+e));
                }
            }finally {
                // 回收处理
                client2.dispatcher2().finished(this);
            }
        }
    }

    private Response2 getResponseWithInterceptorChain() throws Exception{

        List<Interceptor2> interceptors = new ArrayList<>();
        interceptors.add(new ReRequestInterceptor());

        ChainManager chainManager = new ChainManager(interceptors,0,request2, RealCall2.this);

        return chainManager.getResponse(request2);

    }
}
