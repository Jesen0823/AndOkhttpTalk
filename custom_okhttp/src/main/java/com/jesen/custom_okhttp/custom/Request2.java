package com.jesen.custom_okhttp.custom;

import java.util.HashMap;
import java.util.Map;

public class Request2 {

    public static final String GET = "GET";
    public static final String POST ="POST";

    private String url;

    // 默认是get方法
    private String requestMethod = GET;

    // 请求头
    private Map<String,String> mHeadList = new HashMap<>();

    public void setUrl(String url) {
        this.url = url;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public void setHeadList(Map<String, String> mHeadList) {
        this.mHeadList = mHeadList;
    }

    public Request2() {
        this(new Builder2());
    }

    public Request2(Builder2 builder2) {
        this.url = builder2.url;
    }

    public final static class Builder2 {

        private String url;
        private String requestMethod = GET;
        private Map<String,String> mHeadList = new HashMap<>();

        public Builder2 url2(String url) {
            this.url = url;
            return this;
        }

        public Request2 build2() {
            return new Request2(this);
        }

        public Builder2 get(){
            requestMethod = GET;
            return this;
        }

        public Builder2 post(){
            requestMethod = POST;
            return this;
        }

        public Builder2 addRequestHeader(String key, String value){
            mHeadList.put(key,value);
            return this;
        }
    }
}
