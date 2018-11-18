package ohtu;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Arrays;
import org.apache.http.client.fluent.Request;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Main {

    public static void main(String[] args) throws IOException {
        // ÄLÄ laita githubiin omaa opiskelijanumeroasi
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "https://studies.cs.helsinki.fi/courses/students/"+studentNr+"/submissions";

        String bodyText = Request.Get(url).execute().returnContent().asString();
        
        url = "https://studies.cs.helsinki.fi/courses/courseinfo";
        
        String coursesBody = Request.Get(url).execute().returnContent().asString();

        url = "https://studies.cs.helsinki.fi/courses/ohtu2018/stats";

        String ohtustats = Request.Get(url).execute().returnContent().asString();

        url = "https://studies.cs.helsinki.fi/courses/rails2018/stats";

        String railsstats = Request.Get(url).execute().returnContent().asString();

        JsonParser parser = new JsonParser();
        JsonObject ohtu = parser.parse(ohtustats).getAsJsonObject();
        JsonObject rails = parser.parse(railsstats).getAsJsonObject();

        //System.out.println("json-muotoinen data:");
        //System.out.println( bodyText );

        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);
        Course[] courses = mapper.fromJson(coursesBody, Course[].class);
        
        Course[] newCourses = Arrays.stream(courses).map(c -> {
            
            c.addSubmissions(Arrays.stream(subs)
                   .filter(s -> c.getName().equals(s.getCourse())).toArray(Submission[]::new));
            return c;
            
        }).toArray(Course[]::new);

        newCourses = Arrays.stream(courses).map(c -> {
            
            try {
                c.setStats(getStats(c.getName()));
                return c;
            } catch(Exception e) {
            }

            return c;
            
        }).toArray(Course[]::new);

        /*System.out.println("Oliot:");
        for (Object submission : subs) {
            System.out.println((Submission) submission);
        } */
        
        for (Course course : newCourses) {
            System.out.println(course);
        }

    }

    private static JsonObject getStats(String course) throws IOException {
        JsonParser parser = new JsonParser();

        String url = "https://studies.cs.helsinki.fi/courses/" + course + "/stats";
        return parser.parse(Request.Get(url).execute().returnContent().asString()).getAsJsonObject();
    }
}
