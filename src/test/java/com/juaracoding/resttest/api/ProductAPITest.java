package com.juaracoding.resttest.api;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ProductAPITest {
    private RequestSpecification requestSpecification;
    private Response response;
    private ValidatableResponse validatableResponse;

    @BeforeTest
    public void setup() {
        RestAssured.baseURI = "https://fakestoreapi.com";
        requestSpecification = RestAssured.given();
    }

    @Test
    public void myTestAPI() {
        response = requestSpecification.get("/products");

        validatableResponse = response.then();
        validatableResponse.statusCode(200);
        validatableResponse.statusLine("HTTP/1.1 200 OK");

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.getStatusLine(), "HTTP/1.1 200 OK");
    }

    @Test
    public void listTest() {
        // Request
        validatableResponse = RestAssured.given()
                .when()
                .get("/products")
                .then();

        // Validation
        validatableResponse
                .statusCode(200).statusLine("HTTP/1.1 200 OK")
                .body("size()", Matchers.greaterThan(0))
                .body("id", Matchers.everyItem(Matchers.instanceOf(Integer.class)))
                .body("title", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("price", Matchers.everyItem(Matchers.instanceOf(Number.class)))
                .body("description", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("category", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("image", Matchers.everyItem(Matchers.instanceOf(String.class)))
                .body("rating.rate", Matchers.everyItem(Matchers.instanceOf(Number.class)))
                .body("rating.count", Matchers.everyItem(Matchers.instanceOf(Integer.class)));

    }

    @Test
    public void getSingleProduct(){
        validatableResponse = RestAssured.given()
            .when()
            .get("/products/{id}",1)
            .then();
        
        validatableResponse
        .statusCode(200).statusLine("HTTP/1.1 200 OK")
                .body("id", Matchers.equalTo(1))
                .body("title", Matchers.instanceOf(String.class))
                .body("price", Matchers.instanceOf(Number.class))
                .body("description", Matchers.instanceOf(String.class))
                .body("category", Matchers.instanceOf(String.class))
                .body("image", Matchers.instanceOf(String.class))
                .body("rating.rate", Matchers.instanceOf(Number.class))
                .body("rating.count", Matchers.instanceOf(Integer.class));
    }

    @Test
    public void updateProductTest(){
        java.util.Map<String, Object> payload = new java.util.HashMap<>();
        payload.put("title","Samsung DDR3 4GB RAM");
        payload.put("price",99.9);
        payload.put("description","Samsung DDR3 RAM with 4GB Memory");
        payload.put("image","https://new-image.com/test.jpg");
        payload.put("category","electronic");

        validatableResponse = RestAssured.given()
            .contentType("application/json")
            .body(payload)
            .when()
            .put("/products/{id}", 21)
            .then();

        validatableResponse
            .statusCode(200)
            .body("id", Matchers.equalTo(21))
            .body("title", Matchers.equalTo("Samsung DDR3 4GB RAM"))
            .body("price", Matchers.equalTo(99.9f));

    }

    @Test
    public void deleteProductTest(){
        validatableResponse = RestAssured.given()
        .when()
        .delete("/products/{id}", 1)
        .then();

        validatableResponse
        .statusCode(200)
        .body("id",Matchers.equalTo(1));
    }
}
