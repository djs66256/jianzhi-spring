package com.jianzhi.util.socket;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jianzhi.core.location.model.Location;
import com.jianzhi.util.message.ReturnMessage;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.omg.CORBA.NameValuePair;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class SocketApi {
    @Value("${socket.base.url}")
    private String baseUrl;

    private HttpClient httpClient = HttpClients.createDefault();


    public ReturnMessage postJob(String from, String to, String jid, String text) throws Exception {
        Map<String, Object> params = new LinkedHashMap<>();
        params.put("from", from);
        params.put("to", to);
        params.put("jid", jid);
        if (text != null) {
            params.put("text", text);
        }

        List<BasicNameValuePair> nameValuePairs = new ArrayList<>();
        nameValuePairs.add(new BasicNameValuePair("from", from));
        nameValuePairs.add(new BasicNameValuePair("to", to));
        nameValuePairs.add(new BasicNameValuePair("jid", jid));
        if (text != null) {
            nameValuePairs.add(new BasicNameValuePair("text", text));
        }
        String uuid = UUID.randomUUID().toString().replace("-", "");
        nameValuePairs.add(new BasicNameValuePair("uuid", uuid));

        String url = baseUrl + "/post/job";

        HttpPost httpPost = new HttpPost(url);
        HttpEntity entity = new UrlEncodedFormEntity(nameValuePairs, "UTF-8");
        httpPost.setEntity(entity);
        HttpResponse httpResponse = httpClient.execute(httpPost);
        String result = EntityUtils.toString(httpResponse.getEntity());

        ObjectMapper objectMapper = new ObjectMapper();
        Map ret = objectMapper.readValue(result, Map.class);

        return new ReturnMessage((int)ret.get("retCode"), ret.get("content"));
    }
}
