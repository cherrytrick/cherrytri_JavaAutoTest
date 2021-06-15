package com.testol.auto_cher.Client;



import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.*;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;



public class Client {

    final static Logger Log = Logger.getLogger(Client.class);
    public static Map<String, String> cookies = new HashMap<String, String>();
    public static CookieStore cookieStore = new BasicCookieStore();

    @Resource
    CookieManager cookieManager = new CookieManager();

    /**
     * 不带请求头的get方法封装
     *
     * @param url
     * @return 返回响应对象
     * @throws ClientProtocolException
     * @throws IOException
     */
    public CloseableHttpResponse get(String url) throws IOException {

        //创建一个可关闭的Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建一个Httpget的请求对象
        HttpGet httpget = new HttpGet(url);
        //执行请求，发送request请求数据，然后赋值给HttpResponse对象接收
        Log.info("开始发送get请求...");
        cookieManager.addCookieInRequestHeaderBeforeRequest(httpget);
        CloseableHttpResponse httpResponse = httpClient.execute(httpget);
        cookieManager.getAndStoreCookieFromResponseHeader(httpResponse);
        Log.info("发送请求成功！开始得到响应对象。");
        return httpResponse;
    }

    /**
     * 带header的get方法封装
     *
     * @param url
     * @return 返回响应对象
     * @throws ClientProtocolException
     * @throws IOException
     */
    public CloseableHttpResponse getApi(String url, HashMap<String, String> headerMap) throws IOException {
        //创建一个可关闭的Httpclient对象,设置自动跟踪重定向.可以复用。
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).setRedirectStrategy(new LaxRedirectStrategy()).build();
        //创建一个Httpget的请求对象
        HttpGet httpget = new HttpGet(url);
        //加载请求头到Httpget对象
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpget.addHeader(entry.getKey(), entry.getValue());
        }
        //执行请求
        cookieManager.addCookieInRequestHeaderBeforeRequest(httpget);
        CloseableHttpResponse httpResponse = httpclient.execute(httpget);
        cookieManager.getAndStoreCookie(cookieStore);
        return httpResponse;


    }

    /**
     * 封装post方法不含cookie
     *
     * @param url
     * @param entityString，其实就是设置请求json参数
     * @param headerMap                   带请求头
     * @return 返回响应对象
     * @throws ClientProtocolException
     * @throws IOException
     */
    public CloseableHttpResponse postJson(String url, String entityString, HashMap<String, String> headerMap) throws IOException {
        //创建一个可关闭的Httpclient对象,设置自动跟踪重定向
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).setRedirectStrategy(new LaxRedirectStrategy()).build();
//        CloseableHttpClient httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();
        //创建一个Httppost的请求对象
        HttpPost httppost = new HttpPost(url);
        //设置payload
        httppost.setEntity(new StringEntity(entityString));

        //加载请求头到httppost对象
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httppost.addHeader(entry.getKey(), entry.getValue());

        }
        //发送post请求
        cookieManager.addCookieInRequestHeaderBeforeRequest(httppost);
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);
        //通过cookieStore获取持久化cookie
        cookieManager.getAndStoreCookie(cookieStore);
        Log.info("开始发送post请求");
        return httpResponse;
    }


    /**
     * 封装post方法【】含有cookie
     *
     * @param url
     * @param entityString，其实就是设置请求json参数
     * @param headerMap                   带请求头
     * @return 返回响应对象
     * @throws ClientProtocolException
     * @throws IOException
     */
    public CloseableHttpResponse postKeyValue(String url, Map<String, String> entityString, HashMap<String, String> headerMap) throws IOException {
        //创建一个可关闭的Httpclient对象,设置自动跟踪重定向，并且自动持久化使用cookiestore
        CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookieStore).setRedirectStrategy(new LaxRedirectStrategy()).build();
//        CloseableHttpClient httpclient = HttpClientBuilder.create().setRedirectStrategy(new LaxRedirectStrategy()).build();

//        CloseableHttpClient httpclient = HttpClientBuilder.create().build();
        //创建一个Httppost的请求对象
        HttpPost httppost = new HttpPost(url);
        List<BasicNameValuePair> parameters = new ArrayList<BasicNameValuePair>();
        //去除map中所有的参数名
        Set<String> keys = entityString.keySet();
        //通过循环将参数保存到list集合
        for (String name : keys) {
            String value = entityString.get(name);
            parameters.add(new BasicNameValuePair(name, value));
        }
        httppost.setEntity(new UrlEncodedFormEntity(parameters, "utf-8"));

        //加载请求头到httppost对象
        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httppost.addHeader(entry.getKey(), entry.getValue());
        }
        //发送post请求
        cookieManager.addCookieInRequestHeaderBeforeRequest(httppost);
        CloseableHttpResponse httpResponse = httpclient.execute(httppost);
        //通过cookieStore获取持久化cookie
        cookieManager.getAndStoreCookie(cookieStore);

        //从response set-cookie中获取cookie信息【资管产品不适用】
        //getAndStoreCookieFromResponseHeader(httpResponse);

        Log.info("开始发送post请求");
        return httpResponse;
    }


    /**
     * 封装put请求方法，参数和post方法一样
     *
     * @param url
     * @param entityString，这个主要是设置payload，一般来说就是json串
     * @param headerMap,带请求的头信息，格式是键值对，所以这里使用hashmap
     * @return 返回响应对象
     * @throws ClientProtocolException
     * @throws IOException
     */
    public CloseableHttpResponse put(String url, String entityString, HashMap<String, String> headerMap) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpput = new HttpPut(url);
        httpput.setEntity(new StringEntity(entityString));

        for (Map.Entry<String, String> entry : headerMap.entrySet()) {
            httpput.addHeader(entry.getKey(), entry.getValue());
        }
        //发送put请求
        CloseableHttpResponse httpResponse = httpclient.execute(httpput);
        Log.info("开始发送Put请求");
        return httpResponse;
    }

    /**
     * 封装 delete请求方法
     *
     * @param url
     * @return 返回一个response对象，方便进行得到状态码和json解析
     * @throws IOException
     */
    public CloseableHttpResponse delete(String url) throws IOException {

        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpDelete httpdelete = new HttpDelete(url);

        //发送delete请求
        CloseableHttpResponse httpResponse = httpclient.execute(httpdelete);
        return httpResponse;
    }

    /**
     * 获取响应状态码，常用来和TestBase中定义的状态码常量去测试断言使用
     *
     * @param response
     * @return
     */
    public int getStatusCode(CloseableHttpResponse response) {

        int statusCode = response.getStatusLine().getStatusCode();
        Log.info("解析，得到响应状态码" + statusCode);
        return statusCode;
    }

    /**
     * @param response 任何请求返回的响应对象
     * @return
     * @throws IOException
     */
    public String getResponseJson(CloseableHttpResponse response) throws IOException {
        String responseString = EntityUtils.toString(response.getEntity(), "UTF-8");
        Log.info("得到响应对象的String格式");
        Log.info(responseString);
        return responseString;

    }
}
