package com.jesen.custom_okhttp.custom.chain;

import com.jesen.custom_okhttp.custom.Request2;
import com.jesen.custom_okhttp.custom.Response2;

import java.io.IOException;

public interface Chain2 {

    Request2 getRequest();

    Response2 getResponse(Request2 request2) throws IOException;

}
