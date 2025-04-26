package com.fancode.clients;

import com.fancode.config.BaseURI;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

import java.util.Map;

public class RequestClient {

    /**
     * Sends a simple HTTP GET request to the specified API endpoint.
     *
     * <p>This method builds a request with JSON content type using RestAssured,
     * hits the full URL formed by the base URL and provided endpoint,
     * validates the response headers, and returns the response.</p>
     *
     * @param endpoint The API endpoint (relative path) to which the GET request is sent.
     * @return The {@link io.restassured.response.Response} object containing the response data.
     */
   public Response get(String endpoint) {
        Response response= RestAssured.given().spec(new RequestSpecBuilder().setContentType("application/json").build()).when().get(BaseURI.getBaseURL() + endpoint).then().extract().response();
        ResponseClient responseApiUtils = new ResponseClient();
        responseApiUtils.responseHeaderValidation(response);
        return response;
    }

    /**
     * Sends an HTTP GET request with query parameters to the specified API endpoint.
     *
     * <p>This method constructs the full request URL using the base URL and the given endpoint,
     * appends the provided query parameters, validates the response headers,
     * and returns the full response.</p>
     *
     * @param endpoint The API endpoint (relative path) to which the GET request is sent.
     * @param queryParmas A map of query parameters to include in the request.
     * @return The {@link io.restassured.response.Response} object containing the response data.
     */
    public Response getWithQueryParams(String endpoint, Map<String, Integer> queryParmas) {
        Response response= RestAssured.given().spec(new RequestSpecBuilder().setContentType("application/json").build().queryParams(queryParmas)).when().get(BaseURI.getBaseURL() + endpoint).then().extract().response();
        ResponseClient responseApiUtils = new ResponseClient();
        responseApiUtils.responseHeaderValidation(response);
        return response;
    }
}
