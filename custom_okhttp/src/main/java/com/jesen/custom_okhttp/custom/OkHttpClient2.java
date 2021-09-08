package com.jesen.custom_okhttp.custom;

public class OkHttpClient2 {

    Dispatcher2 dispatcher2;
    boolean isCanceled;

    public OkHttpClient2() {
        this(new Builder2());
    }

    public OkHttpClient2(Builder2 builder2) {
        dispatcher2 = builder2.dispatcher2;
        isCanceled = builder2.isCanceled;
    }

    public Call2 newCall(Request2 request2) {
        return new RealCall2(this, request2);
    }

    public Dispatcher2 dispatcher2() {
        return dispatcher2;
    }

    public boolean getCanceled() {
        return isCanceled;
    }


    public final static class Builder2 {

        Dispatcher2 dispatcher2;
        boolean isCanceled;

        public Builder2() {
            dispatcher2 = new Dispatcher2();
        }

        // 用户取消请求
        public Builder2 cancel() {
            isCanceled = true;
            return this;
        }

        public Builder2 dispatcher(Dispatcher2 dispatcher2) {
            this.dispatcher2 = dispatcher2;
            return this;
        }

        public OkHttpClient2 build2() {
            return new OkHttpClient2(this);
        }
    }
}
