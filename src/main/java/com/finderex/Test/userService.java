package com.finderex.Test;

import com.finderex.Utilties.BrowserUtils;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class userService extends credential {

    authService authService = new authService();

    @BeforeMethod
    public void apiSetUp() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1";
    }

    @Test

    public void users(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/users");
        System.out.println(ekipId);
        System.out.println(ekipSecret);
        System.out.println(id);
        System.out.println(secret);
        System.out.println(authService.getAccessToken());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test

    public void user(){

         BrowserUtils.apiTest_Get(userGet, "id");

    }



}
