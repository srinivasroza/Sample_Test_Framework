package com.fancode.requests;

import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Reporter;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Tasks {

    private static final Logger logger = Logger.getLogger(Tasks.class.getName());

    /**
     * Parses a JSON array of tasks and logs user IDs who have completed at least 50% of their tasks.
     *
     * <p>This method processes the given JSON string representing a list of task objects.
     * Each task object is expected to contain the fields <code>userId</code> and <code>completed</code>.</p>
     *
     * <p>For each user, it counts the total number of tasks and the number of completed tasks.
     * After processing, it logs the user IDs of those who have completed 50% or more of their tasks.</p>
     *
     * <p>This method is intended for informational logging and does not return data or throw exceptions.</p>
     *
     * @param json A JSON-formatted string representing an array of tasks,
     *             where each task contains at least a <code>userId</code> (int)
     *             and <code>completed</code> (boolean) field.
     */
    public void getCompletedTasks(String json) {
        JSONArray tasks = new JSONArray(json);

        // Maps to count total and completed tasks per user
        Map<Integer, Integer> totalTasks = new HashMap<>();
        Map<Integer, Integer> completedTasks = new HashMap<>();

        for (int i = 0; i < tasks.length(); i++) {
            JSONObject task = tasks.getJSONObject(i);
            int userId = task.getInt("userId");
            boolean completed = task.getBoolean("completed");

            totalTasks.put(userId, totalTasks.getOrDefault(userId, 0) + 1);
            if (completed) {
                completedTasks.put(userId, completedTasks.getOrDefault(userId, 0) + 1);
            }
        }
        for (Map.Entry<Integer, Integer> entry : totalTasks.entrySet()) {
            int userId = entry.getKey();
            int total = entry.getValue();
            int completed = completedTasks.getOrDefault(userId, 0);

            if (((double) completed / total) >= 0.5) {
                logger.info("User Id Who has Completed 50 % Task " + userId);
                Reporter.log("User Id Who has Completed 50 % Task " + userId);
            }
        }
    }
}
