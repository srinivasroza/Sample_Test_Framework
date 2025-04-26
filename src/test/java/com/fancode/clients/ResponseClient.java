package com.fancode.clients;

import io.restassured.response.Response;
import java.util.logging.Logger;
import static org.apache.http.HttpStatus.*;

public class ResponseClient {
    private static final Logger logger = Logger.getLogger(ResponseClient.class.getName());

    /**
     * Validates and logs the HTTP response status code from a given API response.
     *
     * <p>This method checks the HTTP status code from the response and logs
     * a descriptive message using a logger based on standard HTTP status categories:</p>
     * <ul>
     *     <li><b>2xx</b> - Success (OK, Created, No Content)</li>
     *     <li><b>4xx</b> - Client errors (Bad Request, Unauthorized, Forbidden, Not Found)</li>
     *     <li><b>5xx</b> - Server errors (Internal Server Error)</li>
     *     <li><b>Other</b> - Unexpected response codes</li>
     * </ul>
     *
     * <p>Useful for debugging and verifying the outcome of API requests.</p>
     *
     * @param response The {@link io.restassured.response.Response} object whose status code is to be validated and logged.
     */
    public void responseHeaderValidation(Response response) {
        int httpStatus = response.getStatusCode();
        switch (httpStatus) {
            case SC_OK:
                logger.info("Success: OK");
                break;
            case SC_CREATED:
                logger.info("Success: Resource Created");
                break;
            case SC_NO_CONTENT:
                logger.info("No Content");
                break;
            case SC_BAD_REQUEST:
                logger.severe("Client Error: Bad Request");
                break;
            case SC_UNAUTHORIZED:
                logger.severe("Client Error: Unauthorized");
                break;
            case SC_FORBIDDEN:
                logger.severe("Client Error: Forbidden");
                break;
            case SC_NOT_FOUND:
                logger.severe("Client Error: Not Found");
                break;
            case SC_INTERNAL_SERVER_ERROR:
                logger.severe("Server Error: Internal Server Error");
                break;
            default:
                logger.info("Unexpected Response Code: " + httpStatus);
                break;
        }
    }
}