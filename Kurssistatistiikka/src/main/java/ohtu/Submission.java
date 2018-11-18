package ohtu;

public class Submission {
    private int week;
    private int hours;
    private int[] exercises;
    private String course;
    private Course courseObject;

    public void setWeek(int week) {
        this.week = week;
    }

    /**
     * @param course the course to set
     */
    public void setCourse(String course) {
        this.course = course;
    }

    /**
     * @param exercises the exercises to set
     */
    public void setExercises(int[] exercises) {
        this.exercises = exercises;
    }

    /**
     * @param hours the hours to set
     */
    public void setHours(int hours) {
        this.hours = hours;
    }

    public void setCourseObject(Course courseObject) {
        this.courseObject = courseObject;
    }
    
    

    /**
     * @return the course
     */
    public String getCourse() {
        return course;
    }

    /**
     * @return the exercises
     */
    public int[] getExercises() {
        return exercises;
    }

    /**
     * @return the hours
     */
    public int getHours() {
        return hours;
    }

    public int getWeek() {
        return week;
    }

    private String exercisesAsString() {
        String ret = "";
        for (int e : exercises ) {
            ret += e +" ";
        }

        return ret;
    }
    
    

    @Override
    public String toString() {
        return "viikko " + week + "\n     aikaa kului "+ hours + " tehtäviä tehty " + exercises.length + " tehtävät " + exercisesAsString() ;
    }

}