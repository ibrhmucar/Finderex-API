package com.finderex.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class boosterService extends credential {

    authService authService = new authService();

    @BeforeClass
    public void APItest() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/boosters";
    }

    @Test

    public void booster_id_interval() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/45/5m");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
    }

    @Test
    public void booster() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/45/5m");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }
    @Test

    public void booster_delete() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/268");

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }
    @Test


    public void booster_indicator_delete() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().delete("/indicators/227/indicators?indicatorId=2965");

        System.out.println(response.asString());
        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void boosterIndicators() {

        String body = "{\n" +
                "  \"typeOf\": \"crypto\",\n" +
                "  \"assetId\": 27,\n" +
                "  \"assetSymbol\": \"BNBUSDT\",\n" +
                "  \"exchange\": \"binance\",\n" +
                "  \"intervals\": [\n" +
                "    \"5m\"\n" +
                "  ],\n" +
                "  \"indicators\": [\n" +
                "    1\n" +
                "  ]\n" +
                "}";

        Response response = RestAssured.given()
                .accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().body(body)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().post("/indicators");

        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
    }
}
