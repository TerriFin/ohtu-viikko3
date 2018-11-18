package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import org.apache.http.client.fluent.Request;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if (args.length > 0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/" + studentNr + "/submissions";

        String studentResults = Request.Get(url).execute().returnContent().asString();

        url = "https://studies.cs.helsinki.fi/courses/courseinfo";

        String courseInfo = Request.Get(url).execute().returnContent().asString();

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(studentResults, Submission[].class);
        CourseInfo[] infos = mapper.fromJson(courseInfo, CourseInfo[].class);

        TextOutputter printer = new TextOutputter(subs, infos);
        printer.printAllInfo(studentNr);
    }
}
