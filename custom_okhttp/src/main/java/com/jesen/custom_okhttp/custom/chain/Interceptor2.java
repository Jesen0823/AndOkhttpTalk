package com.jesen.custom_okhttp.custom.chain;

import com.jesen.custom_okhttp.custom.Response2;

import java.io.IOException;

public interface Interceptor2 {

    Response2 doNext(Chain2 chain2) throws IOException;

}
