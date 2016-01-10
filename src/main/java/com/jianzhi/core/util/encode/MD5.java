package com.jianzhi.core.util.encode;

import java.security.MessageDigest;

/**
 * Created by daniel on 15/6/18.
 */
public class MD5 {
    public static byte[] MD5(byte[] content) throws Exception{
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        md5.update(content);
        return md5.digest();
    }

    public static byte[] MD5(String content) throws Exception {
        return MD5(content.getBytes());
    }
}
