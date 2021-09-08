package com.jesen.custom_okhttp.custom;

import java.io.IOException;

public interface Callback2 {
    void onFailure2(Call2 call, IOException e);

    void onResponse2(Call2 call, Response2 response);
}
