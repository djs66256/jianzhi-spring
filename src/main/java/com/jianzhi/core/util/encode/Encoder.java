package com.jianzhi.core.util.encode;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.Base64;

/**
 * Created by daniel on 15/6/18.
 */
@Component
public class Encoder {

    /// password 为 MD5 后 base64
    // 加密算法：base64(MD5("[password]{username}"))
    public String encode(String username, String password) throws Exception{
        String saltPassword = "[" + password + "]" + "{" + username + "}";
        byte[] passMd5 = MD5.MD5(saltPassword);
        return Base64.getEncoder().encodeToString(passMd5);
    }
}
