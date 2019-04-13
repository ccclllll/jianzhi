package com.shnu.part.web.util;

import org.springframework.http.HttpHeaders;

public class HeaderUtil {

    public static HttpHeaders createHeader(String message, String params){
        HttpHeaders headers = new HttpHeaders();
        headers.add("message",message);
        headers.add("params",params);
        return headers;
    }
}
