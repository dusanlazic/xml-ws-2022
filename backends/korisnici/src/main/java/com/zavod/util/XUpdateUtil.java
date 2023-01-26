package com.zavod.util;

import static com.zavod.util.XUpdateTemplate.APPEND;

public class XUpdateUtil{

    public static String clipString(String s) {
        return s.substring(s.indexOf("?>") + 1);
    }

    public static String createAppendString(String xpath, String marshalled) {
        return String.format(APPEND, xpath, marshalled);
    }

    public static String clipStringTwo(String s) {
        return s.substring(s.indexOf("?>") + 2);
    }
}
