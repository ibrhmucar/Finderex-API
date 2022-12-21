package com.finderex.Test;

import com.finderex.finderexAPI.Data;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class healthService extends credential {

    authService auth = new authService();

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
                .and().headers("Authorization", "Bearer " + accessToken)
                .when().get("/database");




    }
}
