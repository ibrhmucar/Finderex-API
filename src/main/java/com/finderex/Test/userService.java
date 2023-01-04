package com.finderex.Test;

import com.finderex.Utilties.BrowserUtils;
import com.finderex.Utilties.Credentials;
import com.finderex.Utilties.FinAPIUtil;
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
                .and().headers(Credentials.getInfo("ekipId"), Credentials.getInfo("id"))
                .and().headers(Credentials.getInfo("ekipSecret"), Credentials.getInfo("secret"))
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get("/users");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

    @Test

    public void user(){

        FinAPIUtil.apiTest_Get(Credentials.getInfo("url"), "id");

    }



}
