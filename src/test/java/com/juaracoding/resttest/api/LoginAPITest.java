package com.juaracoding.resttest.api;

import java.util.HashMap;
import java.util.Map;

import org.hamcrest.Matchers;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.juaracoding.resttest.provider.UserProvider;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;

public class LoginAPITest {

  @BeforeTest
  public void setup() {
    RestAssured.baseURI = "https://fakestoreapi.com";
  }

  @Test(dataProvider = "userCredentials", dataProviderClass = UserProvider.class)
  public void loginTest(String username, String password) {
    Map<String, String> payload = new HashMap<>();
    payload.put("username", username);
    payload.put("password", password);

    ValidatableResponse validatableResponse = RestAssured.given()
        .contentType("application/json")
        .body(payload)
        .when()
        .post("/auth/login")
        .then();

    validatableResponse.assertThat().statusCode(201)
        .body("token", Matchers.instanceOf(String.class))
        .body("token", Matchers.matchesPattern("^[\\w-]+\\.[\\w-]+\\.[\\w-]+$"));

  }
}