package com.ydcjavashop.shop.network;

import android.util.Log;

import com.ydc.config.ApiConfig;
import com.ydc.config.Constant;
import com.ydcjavashop.shop.global.URLRoot;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @Description RX&Retrofit
 * @Author ydc
 * @CreateDate 2016/10/31
 * @Version 1.0
 */
public enum RxService {
    RETROFIT;
    private Retrofit mRetrofit;
    private static final int READ_TIMEOUT = 60;//读取超时时间,单位秒
    private static final int CONN_TIMEOUT = 50;//连接超时时间,单位秒
    /**
     * @description Head信息拦截
     * @author ydc
     * @createDate
     * @version 1.0
     */
    private Interceptor mHeadInterceptor = new Interceptor() {//头信息
        @Override
        public Response intercept(Chain chain) throws IOException {
            //这个chain里面包含了request和response，所以你要什么都可以从这里拿
            Request request = chain.request();
            long t1 = System.nanoTime();//请求发起的时间

            String method = request.method();
            if ("POST".equals(method)) {
                StringBuilder sb = new StringBuilder();
                if (request.body() instanceof FormBody) {
                    FormBody body = (FormBody) request.body();
                    for (int i = 0; i < body.size(); i++) {
                        sb.append(body.encodedName(i) + "=" + body.encodedValue(i) + ",");
                    }
                    sb.delete(sb.length() - 1, sb.length());
                    Log.d("NET_RELATIVE", String.format("发送请求 %s on %s %n%s %nRequestParams:{%s}",
                            request.url(), chain.connection(), request.headers(), sb.toString()));
                }
            } else {
                Log.d("NET_RELATIVE", String.format("发送请求 %s on %s%n%s",
                        request.url(), chain.connection(), request.headers()));
            }
            Response response = chain.proceed(initHead(null,chain));
            long t2 = System.nanoTime();//收到响应的时间
            //这里不能直接使用response.body().string()的方式输出日志
            //因为response.body().string()之后，response中的流会被关闭，程序会报错，我们需要创建出一
            //个新的response给应用层处理
            ResponseBody responseBody = response.peekBody(1024 * 1024);
            Log.d("NET_RELATIVE",
                    String.format("接收响应: [%s] %n返回json:【%s】 %.1fms %n%s",
                            response.request().url(),
                            responseBody.string(),
                            (t2 - t1) / 1e6d,
                            response.headers()
                    ));

            return response;
        }
    };

    /**
     * @description 创建Retrofit对象
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public Retrofit createRetrofit() {
        if(mRetrofit == null){
            OkHttpClient client = new OkHttpClient.Builder()//初始化一个client,不然retrofit会自己默认添加一个
                    .addInterceptor(mHeadInterceptor)
                    .connectTimeout(CONN_TIMEOUT, TimeUnit.MINUTES)//设置连接时间为50s
                    .readTimeout(READ_TIMEOUT, TimeUnit.MINUTES)//设置读取时间为一分钟
                    .build();

            mRetrofit = new Retrofit.Builder()
                    .client(client)
                    .baseUrl(ApiConfig.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())//返回值为Gson的支持(以实体类返回)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//返回值为Oservable<T>的支持
                    .build();
        }

        return mRetrofit;
    }

    /**
     * @description 头信息初始化
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public Request initHead(File file, Interceptor.Chain chain) {
        Request.Builder mBuilder = chain.request().newBuilder();
        Map<String, String> map = new HashMap<String, String>();
        //HeaderUtil.setHeader(map);//ydc 移植注释
        if (file != null) {
            //String mSize = MD5Util.MD5(file.length() + "");
            String mSize ="10";
            //SharedUtil.setFileSize(mSize);//andy.fang 文件大小MD5保存头信息，方便后台校验
            map.put("filesize", mSize);//上传下载文件的MD5值 不需要每个地方都加，在需要的地方加
        }
        Set keys = map.keySet();
        if (keys != null) {
            Iterator iterator = keys.iterator();
            while (iterator.hasNext()) {
                String key = (String) iterator.next();
                String value = (String) map.get(key);
                /*if(StrUtil.isNotBlank(key)&& StrUtil.isNotBlank(value)){
                    mBuilder.addHeader(key, value);
                }*/
            }
        }

        //系统级请求参数
        //mBuilder.addHeader("token", SharedUtil.getPreferStr("TOKEN"));//ydc 移植注释
//        mBuilder.addHeader("v", "1.0");
//        mBuilder.addHeader("format", "JSON");
//        mBuilder.addHeader("appKey", "00001");

        Request mRequest = mBuilder.build();
        return mRequest;
    }

    /**
     * @description 返回服务接口对象实例
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public <T> T createService(final Class<T> service) {
        validateServiceInterface(service);
        return (T) RxService.RETROFIT.createRetrofit().create(service);
    }

    /**
     * @description 校验接口合法性
     * @author ydc
     * @createDate
     * @version 1.0
     */
    public <T> void validateServiceInterface(Class<T> service) {
        if (service == null) {
            //Toast.ShowToast("服务接口不能为空！");
        }
        if (!service.isInterface()) {
            throw new IllegalArgumentException("API declarations must be interfaces.");
        }
        if (service.getInterfaces().length > 0) {
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
        }
    }

}
