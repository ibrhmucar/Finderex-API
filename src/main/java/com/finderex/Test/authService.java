package com.finderex.Test;

import com.finderex.finderexAPI.Data;
import com.finderex.finderexAPI.login;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class authService extends credential {

    login login = new login();
    Data data = new Data();

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

    }


    @Test
    public void sendEmail() {
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


    }

    @Test
    public void refreshToken() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + refreshToken)
                .when().post("/refresh-token");


        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }
    @Test
    public void registery() {

        String body = "{\n" +
                "  \"email\": \"ibrahim.u@ekip.co\",\n" +
                "  \"password\": \"Abcd1234.\",\n" +
                "  \"firstName\": \"ibrahim\",\n" +
                "  \"lastName\": \"ucar\",\n" +
                "  \"phoneNumber\": \"+905076095085\",\n" +
                "  \"photoURL\": \"https://www.shutterstock.com/image-photo/haifa-israel-feb-17-2021-storm-1920356939\",\n" +
                "  \"username\": \"ibrhmucar10\"\n" +
                "}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().body(body)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().post("/register");

        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void logOut() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().post("/logout");


        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");


    }



}
