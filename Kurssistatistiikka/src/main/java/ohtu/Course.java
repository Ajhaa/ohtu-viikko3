/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
    
    
    public int totalExercises() {
        return Arrays.stream(exercises).sum();
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
    
    private String submissionString() {
        if (submissions == null) {
            return "";
        }
        String s = "\n";
        
        for (Submission sb : submissions) {
            s += sb + "\n";
        }
        
        return s;
        
    }
    

    @Override
    public String toString() {
        return fullName + " " + term + " " + year + " " + submissionString(); 
    }
    
    
}
