package com.jianzhi.core.util.validate;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by daniel on 15/6/19.
 */
public class Validate {
    public static boolean isEmail(String str) {
        Pattern pattern = Pattern.compile("^[\\w_-]+@[\\w]+.[\\w]+$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }

    public static boolean isPhone(String str) {
        Pattern pattern = Pattern.compile("^[1]([3][0-9]{1}|59|58|88|89)[0-9]{8}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.find();
    }
}
