
package com.finderex.Test;

import com.finderex.Utilties.BrowserUtils;
import com.finderex.Utilties.Credentials;
import com.finderex.Utilties.FinAPIUtil;
import com.finderex.finderexAPI.Data;
import com.finderex.finderexAPI.login;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class authService  {

    login login = new login();
    Data data = new Data();

    public static  String accessToken= null;

    public static  String refreshToken = null;

    @BeforeMethod

    public static String getAccessToken() {

        String body = "{\n" +
                "  \"userData\": \"ibrhmucar@gmail.com\",\n" +
                "  \"password\": \"Mngr1234.\",\n" +
                "  \"isRequiredIdToken\": true\n" +
                "}";

        String url = "https://api.sandbox.finderex.com/v1/auth/login";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().body(body)
                .when().post(url);

        accessToken = response.jsonPath().getString("data.accessToken");
        return accessToken;
    }

    public String getRefreshToken() {

        String body = "{\n" +
                "  \"userData\": \"ibrhmucar@gmail.com\",\n" +
                "  \"password\": \"Mngr1234.\",\n" +
                "  \"isRequiredIdToken\": true\n" +
                "}";

        String url = "https://api.sandbox.finderex.com/v1/auth/login";
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().body(body)
                .when().post(url);

        refreshToken = response.jsonPath().getString("data.refreshToken");
        System.out.println(refreshToken);
        return refreshToken;
    }


    @BeforeMethod

    public void ApisetUp() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/auth";
    }

    @Test(priority = 1)

    public void login() {
        String body = "{\n" +
                "  \"userData\": \"ibrhmucar@gmail.com\",\n" +
                "  \"password\": \"Mngr1234.\",\n" +
                "  \"isRequiredIdToken\": true\n" +
                "}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().body(body)
                .when().post("/login");


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }


    @Test(priority = 2)
    public void sendEmail() {
        String body = "{\n" +
                "  \"userData\": \"ibrhmucar@gmail.com\",\n" +
                "  \"type\": \"forgotPassword\"\n" +
                "}\n";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().body(body)
                .when().post("/send-email");


        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");


    }

    @Test(priority = 3)
    public void refreshToken() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().headers("Authorization", "Bearer " + getRefreshToken())
                .when().post("/refresh-token");

        System.out.println(refreshToken);
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test(priority = 4)
    public void registery() {
        //email and username must be changed before running the test
        String body = "{\n" +
                "  \"email\": \"ibrahimmmmtesterogğlu@ekip.co\",\n" +
                "  \"password\": \"Abcd1234.\",\n" +
                "  \"firstName\": \"ibrahim\",\n" +
                "  \"lastName\": \"ucar\",\n" +
                "  \"phoneNumber\": \"+905076095085\",\n" +
                "  \"photoURL\": \"https://www.shutterstock.com/image-photo/haifa-israel-feb-17-2021-storm-1920356939\",\n" +
                "  \"username\": \"ibrhmucarrrrr\"\n" +
                "}";

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().body(body)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().post("/register");
        System.out.println(accessToken);
        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test(priority = 5)
    public void logOut() {

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().post("/logout");

        System.out.println(accessToken);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");


    }


}
