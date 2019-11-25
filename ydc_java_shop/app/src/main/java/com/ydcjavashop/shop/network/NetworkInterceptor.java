package com.ydcjavashop.shop.network;

import android.util.Log;

import java.io.EOFException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Locale;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;

public class NetworkInterceptor implements Interceptor {
    public static final String TAG = "Network!!!";
    private static final String V = "1.0";
    private static final String FORMAT = "JSON";
    private static final String APP_KEY = "00001";
    private boolean DEBUG=true;

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = null;
        Charset charset = Charset.defaultCharset();
        MediaType contentType = null;
        String url = request.url().toString();
        String method = request.method();
        long t1 = System.nanoTime();
        Log.d(TAG, String.format(Locale.getDefault(), "Sending %s request [url = %s]", method, url));

        RequestBody body = request.body();
        contentType = body.contentType();
        if (contentType != null) {
            charset = contentType.charset(charset);
        }

        if (body != null && body instanceof FormBody) {
            body = addSysParams((FormBody) body);
        }

        Request newRequest = request;

        if (body != null) {
            newRequest = request.newBuilder()
                    .url(url)
                    .method(method, body)
                    .build();
        }

        if(DEBUG) {
            printRequestBody(body);
        }
        response = chain.proceed(newRequest);
        long t2 = System.nanoTime();

        Log.d(TAG,
                String.format(Locale.getDefault(),
                        "Received response for [url = %s] in %.1fms",
                        url,
                        (t2 - t1) / 1e6d));

        Log.d(TAG,
                String.format(Locale.CHINA,
                        "Received response is %s ,message[%s],code[%d]",
                        response.isSuccessful() ? "success" : "fail",
                        response.message(),
                        response.code()));

        if(DEBUG) {
            printResponseBody(response, charset);
        }

        return response;
    }

    private void printResponseBody(Response response, Charset charset) {
        try {
            ResponseBody respBody = response.body();
            BufferedSource source = respBody.source();
            source.request(Long.MAX_VALUE);
            Buffer buffer = source.buffer();

            String bodyString = buffer.clone().readString(charset);
            Log.d(TAG, String.format("Received response json string [%s]", bodyString));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printRequestBody(RequestBody newBody) {
        try {
            StringBuilder sb = new StringBuilder("Request Body [");
            Buffer buffer = new Buffer();
            newBody.writeTo(buffer);

            Charset charset = Charset.forName("UTF-8");
            MediaType contentType = newBody.contentType();
            if (contentType != null) {
                charset = contentType.charset(charset);
            }

            if (isPlaintext(buffer)) {
                sb.append(buffer.readString(charset));
                sb.append(" (Content-Type = ")
                        .append(contentType.toString()).append(",")
                        .append(newBody.contentLength()).append("-byte body)");

            } else {
                sb.append(" (Content-Type = ")
                        .append(contentType.toString())
                        .append(",binary ")
                        .append(newBody.contentLength())
                        .append("-byte body omitted)");

            }
            sb.append("]");

            Log.d(TAG, String.format(Locale.getDefault(), "%s", sb.toString()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @Description 添加系统级参数
     * @Author
     * @CreateDate 2016/11/22
     * @Version 1.0
     */
    private FormBody addSysParams(FormBody body) {
        FormBody.Builder builder = new FormBody.Builder();
        Boolean flag = true;

        for (int i = 0; i < body.size(); i++) {
            if (body.encodedName(i).equals("noSysParams")) {
                flag = false;
            }

            builder.addEncoded(body.encodedName(i), body.encodedValue(i));
        }

        if (flag) {
            builder.add("v", V);
            builder.add("format", FORMAT);
            builder.add("appKey", APP_KEY);
            //builder.add("token", SharedUtil.getPreferStr("TOKEN"));//ydc 移植注释
        }

        return builder.build();
    }

    static boolean isPlaintext(Buffer buffer) {
        try {
            Buffer prefix = new Buffer();
            long byteCount = buffer.size() < 64 ? buffer.size() : 64;
            buffer.copyTo(prefix, 0, byteCount);
            for (int i = 0; i < 16; i++) {
                if (prefix.exhausted()) {
                    break;
                }
                int codePoint = prefix.readUtf8CodePoint();
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException e) {
            return false; // Truncated UTF-8 sequence.
        }
    }

}
