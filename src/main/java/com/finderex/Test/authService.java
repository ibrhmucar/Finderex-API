package com.finderex.Test;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.LinkedHashSet;


public class authService {

    String id = "b0a98831-9c7d-44fc-ba71-74accd989061";
    String ekipId = "x-ekip-client-id";
    String secret = "03284666f38d76a38b2a8f5a348c5db04cf8d59f58250e65aab914af286f998c58572f8fc586f404ae81c34b005bd7b7";
    String ekipSecret = "x-ekip-client-secret";

    @BeforeMethod

    public void ApisetUp() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/auth";
    }

    @Test

    public void login() {
        String body = "{\n" +
                "  \"userData\": \"ibrahim.ucar@ekip.co\",\n" +
                "  \"password\": \"Mngr1234.\",\n" +
                "  \"isRequiredIdToken\": true\n" +
                "}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().body(body)
                .when().post("/login");


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        String accessToken = response.path("data.accessToken");
        System.out.println(accessToken);

    }
    @Test
    public void sendEmail(){
        String body = "{\n" +
                "  \"userData\": \"ibrahim.ucar@ekip.co\",\n" +
                "  \"type\": \"forgotPassword\"\n" +
                "}\n";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().body(body)
                .when().post("/send-email");


        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        Object bodyMessage = response.path("");
        System.out.println(bodyMessage);

    }


}
