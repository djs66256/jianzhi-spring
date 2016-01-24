package com.jianzhi.util.baiduMap;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianzhi.core.location.model.Location;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.Map;


@Component
public class BaiduMapApi {
    @Value("${baidu.api.ak}")
    private String ak;

    @Value("${baidu.api.sk}")
    private String sk;

    private HttpClient httpClient = HttpClients.createDefault();

    public Location getGeocode(String address) throws Exception {
        Map<String, String> params = new LinkedHashMap<>();
        params.put("address", address);
        String url = getUri("geocoder/v2/", params);

        HttpGet httpGet = new HttpGet(url);
        HttpResponse httpResponse = httpClient.execute(httpGet);
        String result = EntityUtils.toString(httpResponse.getEntity());

        ObjectMapper objectMapper = new ObjectMapper();
        Map map = objectMapper.readValue(result, Map.class);

        if ((Integer)map.get("status") == 0) {
            Map addr = (Map)((Map)map.get("result")).get("location");
            if (!addr.isEmpty()) {
                double lng = (double) addr.get("lng");
                double lat = (double) addr.get("lat");
                Location addressObj = new Location();
                addressObj.setLongitude(lng);
                addressObj.setLatitude(lat);
                return addressObj;
            }
            else {
                throw new Exception("地址不存在");
            }
        }
        else {
            throw new Exception("地址不存在");
        }
    }

    private String getUri(String api, Map<String, String> params) throws UnsupportedEncodingException {
        Map<String, String> map = new LinkedHashMap<>(params);
        params.put("output", "json");
        params.put("ak", ak);
        String wholeStr = "/" + api + "?" + getQueryString(params);
        String tempString = URLEncoder.encode(wholeStr+sk, "UTF-8");
        String sn = MD5(tempString);

        String fullParams = wholeStr + "&sn=" + sn;

        return "http://api.map.baidu.com" + fullParams;
    }

    // 对Map内所有value作utf8编码，拼接返回结果
    private String getQueryString(Map<String, String> data)
            throws UnsupportedEncodingException {
        StringBuilder queryString = new StringBuilder();
        for (Map.Entry<String, String> pair : data.entrySet()) {
            queryString.append(pair.getKey() + "=");
            queryString.append(URLEncoder.encode(pair.getValue(),
                    "UTF-8") + "&");
        }
        if (queryString.length() > 0) {
            queryString.deleteCharAt(queryString.length() - 1);
        }
        return queryString.toString();
    }

    // 来自stackoverflow的MD5计算方法，调用了MessageDigest库函数，并把byte数组结果转换成16进制
    private String MD5(String md5) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest
                    .getInstance("MD5");
            byte[] array = md.digest(md5.getBytes());
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100)
                        .substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        }
        return null;
    }
}
