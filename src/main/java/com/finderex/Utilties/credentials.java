package com.finderex.Utilties;

public abstract class credentials {


    public static String credential(String value) {

        String result = BrowserUtils.getInfo(value);

        return result;
    }

    public static String id = credential("id");
    public static String ekipId = credential("ekipId");
    public static String ekipSecret = credential("ekipSecret");
    public static String secret = credential("secret");
    public static String url1 = credential("url");

}

