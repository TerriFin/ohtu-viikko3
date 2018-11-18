package ohtu;

import java.util.List;

public class Submission {

    private int week;
    private int hours;
    private List<Integer> exercises;
    private String course;

    public void setWeek(int week) {
        this.week = week;
    }

    public int getWeek() {
        return week;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public int getHours() {
        return hours;
    }

    public void setExercises(List<Integer> exercises) {
        this.exercises = exercises;
    }

    public List<Integer> getExercises() {
        return exercises;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getCourse() {
        return course;
    }

    @Override
    public String toString() {
        return " aikaa kului " + hours + " tehdyt tehtävät: " + getDoneExercises();
    }
    
    private String getDoneExercises() {
        StringBuilder toReturn = new StringBuilder();
        
        for (int i = 0; i < exercises.size(); i++) {
            if (i == exercises.size() - 1) {
                toReturn.append(exercises.get(i));
            } else {
                toReturn.append(exercises.get(i) + ", ");
            }
        }
        
        return toReturn.toString();
    }
}
