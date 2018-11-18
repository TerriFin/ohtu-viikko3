/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.client.fluent.Request;

/**
 *
 * @author samisaukkonen
 */
public class CourseStatReader {
    public static CourseStats getCourseStats(String course) throws IOException {
        String url = "https://studies.cs.helsinki.fi/courses/" + course + "/stats";
        String courseStatsResults = Request.Get(url).execute().returnContent().asString();
        
        JsonParser parser = new JsonParser();
        JsonObject parsedData = parser.parse(courseStatsResults).getAsJsonObject();
        
        CourseStats courseStats = new CourseStats();
        for (String key : parsedData.keySet()) {
            JsonObject keyData = parsedData.getAsJsonObject(key);
            
            courseStats.returnsTotal += Double.parseDouble(keyData.get("students").toString());
            courseStats.hourTotal += Double.parseDouble(keyData.get("hour_total").toString());
            courseStats.exerciseTotal += Double.parseDouble(keyData.get("exercise_total").toString());
        }
        
        return courseStats;
    }
}
