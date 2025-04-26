package com.fancode.requests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Users {

    private static final Double START_LAT= -40.0;
    private static final Double END_LAT = 5.0;
    private static final Double START_LAN= 5.0;
    private static final Double END_LAN = 100.0;

    /**
     * Filters and returns users located within the predefined Fancode geographical boundary.
     *
     * <p>This method takes a JSON-formatted string representing an array of users, each containing
     * an address with geographical coordinates (latitude and longitude). It checks if each user's
     * coordinates fall within a specified latitude and longitude range defined by constants
     * <code>START_LAT</code>, <code>END_LAT</code>, <code>START_LAN</code>, and <code>END_LAN</code>.</p>
     *
     * <p>If a user's location is within the defined bounds, their user ID and city name are added
     * to the resulting map.</p>
     *
     * @param json A JSON string representing an array of user objects.
     *             Each user must contain <code>id</code> and <code>address.geo</code> fields.
     * @return A map where the key is the user ID and the value is the city name of users
     *         located within the Fancode geographical area.
     */
    public Map<Integer,String> getFanCodeUsers(String json) {
        JSONArray users = new JSONArray(json);
        Map<Integer,String> fanCodeCities = new HashMap<>();
        Set<String> uniqueCities= new HashSet<>();

        for (int i = 0; i < users.length(); i++) {
            JSONObject user = users.getJSONObject(i);
            JSONObject geo = user.getJSONObject("address").getJSONObject("geo");

            double lat = Double.parseDouble(geo.getString("lat"));
            double lng = Double.parseDouble(geo.getString("lng"));

            if (lat >=START_LAT && lat <= END_LAT && lng >= START_LAN && lng <= END_LAN) {
                String city = user.getJSONObject("address").getString("city");
                fanCodeCities.put(user.getInt("id"),city);
                uniqueCities.add(city);
            }
            for(String city1 : uniqueCities){
                Reporter.log("Fanocde City : " + city1);
            }
        }
        return  fanCodeCities;
    }
}
