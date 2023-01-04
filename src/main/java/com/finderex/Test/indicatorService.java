package com.finderex.Test;

import com.finderex.Utilties.FinAPIUtil;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class indicatorService  {

    authService authService = new authService();

    @BeforeClass
    public void APItest() {
        RestAssured.baseURI = "https://api.sandbox.finderex.com/v1/indicators";
    }

    @Test

    public void indicator() {
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().headers(FinAPIUtil.getSwaggerCredentials())
                .and().headers("Authorization", "Bearer " + authService.getAccessToken())
                .when().get();

        Assert.assertEquals(response.statusCode(), 200);
        Assert.assertEquals(response.contentType(), "application/json; charset=utf-8");

        List<String> listAll = response.jsonPath().getList("data.name");
        System.out.println("listAll = " + listAll);

        int countMax = 0;
        for (String ind : listAll) {
            if(ind.length()> countMax) {
                countMax = ind.length();
            }
        }
        System.out.println("Max Length Size " + countMax);

        int countMin = 100;
        for (String ind : listAll) {
            if(ind.length()< countMin) {
                countMin = ind.length();
            }
        }
        System.out.println("Min Length Size " + countMin);


        JsonPath jsonPath = response.jsonPath();
        List<String> list1 = jsonPath.getList("data.findAll{it.id == 2}.name");
        List<String> list2 = jsonPath.getList("data.findAll{it.name == \"EMA 50\"}.name");
        List<String> list3 = jsonPath.getList("data.findAll{(it.name).length() == 32}.name");
        List<String> list4 = jsonPath.getList("data.findAll{(it.name).length() == 3}.name");
        System.out.println("list4 = " + list4);
        System.out.println("list3 = " + list3);
        System.out.println("list2 = " + list2);
        System.out.println("list1 = " + list1);



    }

}
