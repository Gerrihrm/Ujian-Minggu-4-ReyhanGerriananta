package com.juaracoding.resttest.provider;

import java.util.List;
import java.util.Map;

import org.testng.annotations.DataProvider;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class UserProvider {
  private final String JSON_DATA = """
              [
        {
          "username": "johnd",
          "password": "m38rmF$"
        },
        {
          "username": "mor_2314",
          "password": "83r5^_"
        },
        {
          "username": "kevinryan",
          "password": "kev02937@"
        },
        {
          "username": "donero",
          "password": "ewedon"
        },
        {
          "username": "derek",
          "password": "jklg*_56"
        },
        {
          "username": "david_r",
          "password": "3478*#54"
        },
        {
          "username": "snyder",
          "password": "f238&@*$"
        },
        {
          "username": "hopkins",
          "password": "William56$hj"
        },
        {
          "username": "kate_h",
          "password": "kfejk@*_"
        },
        {
          "username": "jimmie_k",
          "password": "klein*#%*"
        }
      ]
              """;

  @DataProvider(name = "userCredentials")
  public Object[][] getUserCredentials() throws JsonMappingException, JsonProcessingException {

    ObjectMapper mapper = new ObjectMapper();
    List<Map<String, String>> list = mapper.readValue(JSON_DATA, List.class);

    Object[][] data = new Object[list.size()][2];

    for (int i = 0; i < list.size(); i++) {
      Map<String, String> user = list.get(i);

      data[i][0] = user.get("username");
      data[i][1] = user.get("password");
    }

    return data;

  }
}
