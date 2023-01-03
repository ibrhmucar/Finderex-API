package com.finderex.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class layoutService extends credential {

    authService authService = new authService();

    @BeforeClass
    public void APItest() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/layouts";
    }

    @Test

    public void indicator() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/booster");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        System.out.println(response.asString());
    }

    @Test

    public void indicator_Post() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().post();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        System.out.println(response.asString());

    }
}