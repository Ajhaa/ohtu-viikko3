/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.google.gson.JsonObject;


/**
 *
 * @author ajhaarni
 */
public class Course {
    private String name;
    private int week;
    private String fullName;
    private String term;
    private int year;
    private int[] exercises;
    private List<Submission> submissions;
    private JsonObject stats;
    
    
    public int totalExercises() {
        return Arrays.stream(exercises).sum();
    }

    public int totalSubmissionExercises() {
        int sum = 0;

        if (submissions == null) {
            return 0;
        } 

        for (Submission sub: submissions) {
            sum += Arrays.stream(sub.getExercises()).count();
        }

        return sum;
    }

    public int totalHours() {
        if (submissions == null) {
            return 0;
        }

        return submissions.stream().mapToInt(s -> s.getHours()).sum();
    }
    
    public void addSubmission(Submission s) {
        if (submissions == null) {
            submissions = new ArrayList<>();
        }
        
        submissions.add(s);
    }
    
    public void addSubmissions(Submission[] s) {
        for (Submission sub : s) {
            addSubmission(sub);
        }
    }

    public int[] getExercises() {
        return exercises;
    }

    public String getFullName() {
        return fullName;
    }

    public String getName() {
        return name;
    }

    public int getWeek() {
        return week;
    }

    public String getTerm() {
        return term;
    }

    public int getYear() {
        return year;
    }

    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeek(int week) {
        this.week = week;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setStats(JsonObject stats) {
        this.stats = stats;
    }
    
    private String submissionString() {
        if (submissions == null) {
            return "\n";
        }
        String s = "\n";
        
        for (Submission sb : submissions) {
            s += "  " + sb + "\n";
        }
        
        return s;
        
    }

    private String statsString() {
        if (stats.getAsJsonObject("1") == null) {
            return "";
        }

        int totalEx = 0;
        int totalHrs = 0;
        int totalStds = 0;
        Integer i = 1;
        while(stats.getAsJsonObject(i.toString()) != null) {
            JsonObject week = stats.getAsJsonObject(i.toString());

            totalEx += week.get("exercise_total").getAsInt();
            totalHrs += week.get("hour_total").getAsInt();
            totalStds += week.get("students").getAsInt();

            i++;
        }

        return "kurssilla yhteensä " + totalStds + " palautusta, palautettuja tehtäviä " + totalEx + " kpl, aikaa käytetty yhteensä " + totalHrs + " tuntia";
    }
    

    @Override
    public String toString() {
        return fullName + " " + term + " " + year + " \n" + submissionString()
               + "  yhteensä: " +  totalSubmissionExercises() +" / " + totalExercises() + " tehtävää, " + totalHours() + " tuntia\n"
               + statsString() + "\n"; 
    }
    
    
}
