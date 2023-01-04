package com.finderex.Utilties;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class Credentials {


    public static String getInfo(String value) {

        Dotenv dotenv = Dotenv.configure()
                .filename("datas.env")
                .load();

        return dotenv.get(value);

    }


}

