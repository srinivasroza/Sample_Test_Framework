package com.fancode.scripts;

import com.fancode.config.EndPoints;
import com.fancode.requests.Tasks;
import com.fancode.requests.Users;
import com.fancode.clients.RequestClient;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class FancodeTests {

    /**
     * Test to verify that Fancode users have completed approximately half of their assigned todos.
     *
     * <p>This test performs the following steps:
     * <ul>
     *     <li>Fetches all users from the USERS service.</li>
     *     <li>Filters out the users that belong to the "Fancode" group.</li>
     *     <li>For each Fancode user, retrieves their todos from the TODOS service.</li>
     *     <li>Calculates the completion ratio of tasks for each user.</li>
     *     <li>Asserts that the ratio of completed tasks is approximately 50% (±5%).</li>
     * </ul>
     *
     * <p>This helps in validating user engagement or behavior patterns based on task completion.
     *
     * @author Srinivas
     * @since 2025-04-26
     */
    @Test(priority = 1, groups = {"Fancode"})
    public void testFanCodeUserWhoCompletedHalfTodos() {
        Response response= new RequestClient().get(EndPoints.USERS);
        Users users =  new Users();
        Map<Integer,String> todoUsers= users.getFanCodeUsers(response.getBody().asString());
        for(Map.Entry<Integer,String> entry : todoUsers.entrySet()) {
            Map<String, Integer> queryParmas = new HashMap<>();
            queryParmas.put("userId",entry.getKey());
            Response response1= new RequestClient().getWithQueryParams(EndPoints.TODOS,queryParmas);
            Tasks tasks=new Tasks();
            tasks.getCompletedTasks(response1.getBody().asString());
        }
    }
}
