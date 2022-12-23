package com.finderex.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class marketService extends credential {

    @BeforeMethod
    public void apiSetUp() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/markets";
    }

    @Test
    public void long_short() {
        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().get("/long-short");

        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void long_short_id() {

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().get("/long-short/531");


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void world_indices() {

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().get("/world-indices");


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void world_indices_post() {

        String body = "{\n" +
                "  \"indexes\": [\n" +
                "    \"AEX\"\n" +
                "  ],\n" +
                "  \"typeOf\": \"short\"\n" +
                "}";

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().body(body)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().post("/world-indices");


        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test

    public void world_indices_id() {

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().get("/world-indices/5");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test

    public void season_index_type() {

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().pathParam("id",30)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().get("/season-indices/{id}");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");


    }

    @Test

    public void season_indices() {

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().get("/season-indices");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.get("data.content[0].value").toString());


        List<String> list = jsonPath.getList("data.content[0].findAll{it.value == \"100\"}.time");

        System.out.println(list);





    }


}
