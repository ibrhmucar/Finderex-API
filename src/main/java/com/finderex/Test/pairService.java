package com.finderex.Test;

import com.finderex.Utilties.FinAPIUtil;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class pairService extends credential {
    authService authService = new authService();

    @BeforeClass
    public void APItest() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/pairs/crypto";
    }

    @Test
    public void getPairs() {
        Response response = RestAssured.given()
                .accept("application/json")
                .and().contentType("application/json")
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get();

        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }



}
