package com.example.finalproject;

import com.example.finalproject.Body;
import com.google.gson.annotations.SerializedName;
import com.squareup.okhttp.internal.framed.Header;

public class Message
{
    @SerializedName("header")
    private Header header;

    @SerializedName("body")
    private Body body;

    public Header getHeader() {
        return header;
    }

    public Body getBody() {
        return body;
    }
}
