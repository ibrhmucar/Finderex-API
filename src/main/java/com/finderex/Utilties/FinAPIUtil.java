package com.finderex.Utilties;

import com.finderex.Test.authService;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.HashMap;
import java.util.Map;

public class FinAPIUtil {


    public static Map<String,String > getSwaggerCredentials(){
        Map <String,String > swaggerCredentials =  new HashMap<>();
        swaggerCredentials.put(Credentials.getInfo("ekipId"),Credentials.getInfo("id"));
        swaggerCredentials.put(Credentials.getInfo("ekipSecret"),Credentials.getInfo("secret"));

        return swaggerCredentials;

    }


    public static String apiTest_Get(String url, String json) {


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(Credentials.getInfo("ekipId"), Credentials.getInfo("id"))
                .and().headers(Credentials.getInfo("ekipSecret"), Credentials.getInfo("secret"))
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get(url);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        System.out.println(response.asString());
        String list = response.path("data." + json + "").toString();
        System.out.println(list);
        return list;


    }

    public static String apiTest_Post(String url, String json) {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(Credentials.getInfo("ekipId"), Credentials.getInfo("id"))
                .and().headers(Credentials.getInfo("ekipSecret"), Credentials.getInfo("secret"))
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().post(url);

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        String list = response.path("data." + json + "").toString();
        System.out.println(list);
        return list;

    }
}
