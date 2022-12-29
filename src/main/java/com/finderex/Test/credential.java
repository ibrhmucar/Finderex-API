package com.finderex.Test;

import com.finderex.Utilties.BrowserUtils;

public abstract class credential {


    public static String credential(String value) {

        String result = BrowserUtils.getInfo(value);

        return result;
    }

    String id = credential("id");
    String ekipId = credential("ekipId");
    String ekipSecret = credential("ekipSecret");
    String secret = credential("secret");

}

