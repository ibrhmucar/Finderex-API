package com.finderex.Test;

import com.finderex.Utilties.BrowserUtils;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class referanceService extends credential {

    authService authService = new authService();

    @BeforeMethod

    public void apiSetUp(){RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/referances/check/";}

    @Test

    public void referanceService() {

        String UUID = "e887d1c4-eee1-5223-b80f-214978203a80";

        Response response = RestAssured.given().accept("application/json")
                .and().contentType("application/json")
                .and().headers(credential("ekipId"), credential("id"))
                .and().headers(credential("ekipSecret"), credential("secret"))
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get(UUID);

        System.out.println(response.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

    }

}
