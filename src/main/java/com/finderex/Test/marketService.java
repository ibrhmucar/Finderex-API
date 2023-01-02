package com.finderex.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class marketService extends credential {

    authService authService = new authService();

    @BeforeMethod
    public void apiSetUp() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/markets";
    }

    @Test
    public void long_short() {
        Response response = RestAssured.given().accept("application/json").and().contentType("application/json").and().headers("x-ekip-client-id", "b0a98831-9c7d-44fc-ba71-74accd989061").and().headers("x-ekip-client-secret", "03284666f38d76a38b2a8f5a348c5db04cf8d59f58250e65aab914af286f998c58572f8fc586f404ae81c34b005bd7b7").and().headers("Authorization", "Bearer " + authService.getAccessToken()).when().get("/long-short");

        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void long_short_id() {

        Response response = RestAssured.given()
                .accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/long-short/254");


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void world_indices() {

        Response response = RestAssured.given().accept("application/json").and().contentType("application/json").and().headers(ekipId, id).and().headers(ekipSecret, secret).and().headers("Authorization", "Bearer " + authService.getAccessToken()).when().get("/world-indices");


        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test
    public void world_indices_post() {

        String body = "{\n" + "  \"indexes\": [\n" + "    \"AEX\"\n" + "  ],\n" + "  \"typeOf\": \"short\"\n" + "}";

        Response response = RestAssured.given().accept("application/json").and().contentType("application/json").and().headers(ekipId, id).and().headers(ekipSecret, secret).and().body(body).and().headers("Authorization", "Bearer " + authService.getAccessToken()).when().post("/world-indices");


        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test

    public void world_indices_id() {

        Response response = RestAssured.given().accept("application/json").and().contentType("application/json").and().headers(ekipId, id).and().headers(ekipSecret, secret).and().headers("Authorization", "Bearer " + authService.getAccessToken()).when().get("/world-indices/5");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test

    public void season_index_type() {

        Response response = RestAssured.given().accept("application/json").and().contentType("application/json").and().headers(ekipId, id).and().headers(ekipSecret, secret).and().pathParam("id", 30).and().headers("Authorization", "Bearer " + authService.getAccessToken()).when().get("/season-indices/{id}");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");


    }

    @Test

    public void season_indices() {

        Response response = RestAssured.given().accept("application/json").and().contentType("application/json").and().headers(ekipId, id).and().headers(ekipSecret, secret).and().headers("Authorization", "Bearer " + authService.getAccessToken()).when().get("/season-indices");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        JsonPath jsonPath = response.jsonPath();

        System.out.println(jsonPath.get("data.content[0].value").toString());


        List<String> list = jsonPath.getList("data.content[0].findAll{it.value == \"100\"}.time");

        System.out.println(list);


    }

    @Test

    public void fear_greed() {

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id).and()
                .headers(ekipSecret, secret).and()
                .headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/fear-greed");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        System.out.println(response.asString());

    }

    @Test

    public void fear_greed_post() {

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(ekipId, id).and()
                .headers(ekipSecret, secret).and()
                .headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/fear-greed/now");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        System.out.println(response.asString());

    }


}
