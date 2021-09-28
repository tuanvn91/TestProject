package com.tadfas.testproject.instaparse;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.ResponseHandlerInterface;


public class ServiceHandler {
    public static final int TIMEOUT = 10000;
    private static final String TAG = "ServiceHandler";
    private static AsyncHttpClient client = new AsyncHttpClient(true, 80, 443);


    public static void get(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        client.setConnectTimeout(TIMEOUT);
        client.setEnableRedirects(true);
        client.get(str, requestParams, (ResponseHandlerInterface) asyncHttpResponseHandler);
    }

    public static void get(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler, boolean hasheader, String... cookie) {
        if (hasheader) {//
            String[] cookie1 = cookie;
            if (cookie1 != null) {
                client.addHeader("Cookie", cookie1[0]);
                if (cookie.length > 1 && cookie[1] != null)
                    client.addHeader("User-Agent", cookie[1]);
            }
        }
        client.setConnectTimeout(TIMEOUT);
        client.setEnableRedirects(true);
        client.get(str, requestParams, (ResponseHandlerInterface) asyncHttpResponseHandler);
    }

    public static void post(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        if (requestParams == null) {
            requestParams = new RequestParams();
        }
        requestParams.put("url", "2");
        client.setConnectTimeout(TIMEOUT);
        client.post(str, requestParams, asyncHttpResponseHandler);
    }

    public static void mxpost(String str, RequestParams requestParams, AsyncHttpResponseHandler asyncHttpResponseHandler) {
        if (requestParams == null) {
            requestParams = new RequestParams();
        }
        client.setConnectTimeout(TIMEOUT);
        client.post(str, requestParams, asyncHttpResponseHandler);
    }

}
