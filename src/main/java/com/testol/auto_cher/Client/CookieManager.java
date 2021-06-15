package com.testol.auto_cher.Client;


import org.apache.http.Header;
import org.apache.http.HttpRequest;
import org.apache.http.client.CookieStore;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CookieManager {

    final static Logger Log = Logger.getLogger(Client.class);
    public static Map<String, String> cookies = new HashMap<String, String>();
    public static CookieStore cookieStore = new BasicCookieStore();


    public void addCookieInRequestHeaderBeforeRequest(HttpRequest request) {
        String jsessionIdCookie = cookies.get("JSESSIONID");
        if (jsessionIdCookie != null) {
            request.addHeader("Cookie", jsessionIdCookie);

        }
    }

    public static void getAndStoreCookieFromResponseHeader(CloseableHttpResponse httpResponse) {
        //从响应头里取出名字为"Set-Cookie"的响应头
        Header setCookieHeader = httpResponse.getFirstHeader("Set-Cookie");
        Log.info("setCookieHeader=" + setCookieHeader);

        //如果不为空
        if (setCookieHeader != null) {
            //取出此响应头的值
            String cookiePairsString = setCookieHeader.getValue();
            if (cookiePairsString != null && cookiePairsString.trim().length() > 0) {
                //以“;”来切分
                String[] cookiePairs = cookiePairsString.split(";");
                if (cookiePairs != null) {
                    for (String cookiePair : cookiePairs) {
                        //如果包含JSESSIONID，则意味着响应头里有会话ID这个数据
                        if (cookiePair.contains("JSESSIONID")) {
                            //保存到map
                            cookies.put("JSESSIONID", cookiePair);

                        }
                    }
                }
            }
        }
    }

    public static void getAndStoreCookie(CookieStore cookieStore) {
        String JSESSIONID = null;
        String result = null;
        List<Cookie> cookie = cookieStore.getCookies();
        for (int i = 0; i < cookie.size(); i++) {
            if (cookie.get(i).getName().equals("JSESSIONID")) {
                JSESSIONID = cookie.get(i).getValue();
            }
        }
        if (JSESSIONID != null) {
            result = "JSESSIONID="+JSESSIONID;
        }
        Log.info(result);
        cookies.put("JSESSIONID", result);
    }
}

