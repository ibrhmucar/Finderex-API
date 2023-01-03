
package com.finderex.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class healthService extends credential {

    authService authService = new authService();

    @BeforeMethod

    public void apiSetUp() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/health";
    }

    @Test
    public void database() {


        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/database");


    }

    @Test

    public void healthService(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(ekipId, id)
                .and().headers(ekipSecret, secret)
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/service");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");
        System.out.println(response.asString());

    }


}
