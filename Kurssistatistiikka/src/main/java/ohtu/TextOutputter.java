/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ohtu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author samisaukkonen
 */
public class TextOutputter {

    private Submission[] submissions;
    private CourseInfo[] courseInfos;

    public TextOutputter(Submission[] submissions, CourseInfo[] courseInfos) {
        this.submissions = submissions;
        this.courseInfos = courseInfos;
    }

    public void printAllInfo(String studentNumber) throws IOException{
        System.out.println("opiskelijanumero " + studentNumber);

        List<CourseInfo> correctInfos = removeUnusedCourses();
        for (CourseInfo info : correctInfos) {
            System.out.println("\n" + info.getFullName() + " " + info.getTerm() + " " + info.getYear() + "\n");

            List<Submission> courseSubmissions = getSubmissionsRelatedToCourse(info);
            for (Submission sub : courseSubmissions) {
                printWeekInfo(sub, info);
            }

            System.out.println("\nyhteensä: " + getTotalNumberOfExercisesDone(courseSubmissions) + "/" + getTotalNumberOfExercises(info)
                    + " tehtävää " + getTotalNumberOfHoursWasted(courseSubmissions) + " tuntia\n");
            
            System.out.println(getCourseOverview(info.getName()));
        }
    }

    private void printWeekInfo(Submission sub, CourseInfo info) throws IOException{
        System.out.println("viikko " + sub.getWeek() + ":" + "\n "
                + "tehtyjä tehtäviä " + sub.getExercises().size() + "/" + info.getExercises().get(sub.getWeek()) + sub.toString());
    }

    private List<CourseInfo> removeUnusedCourses() {
        List<CourseInfo> toReturn = new ArrayList<>();

        for (CourseInfo info : courseInfos) {
            boolean hasBeenOnCourse = false;

            for (Submission sub : submissions) {
                if (sub.getCourse().equals(info.getName())) {
                    hasBeenOnCourse = true;
                    break;
                }
            }

            if (hasBeenOnCourse) {
                toReturn.add(info);
            }
        }

        return toReturn;
    }

    private List<Submission> getSubmissionsRelatedToCourse(CourseInfo info) {
        List<Submission> toReturn = new ArrayList<>();

        for (Submission sub : submissions) {
            if (sub.getCourse().equals(info.getName())) {
                toReturn.add(sub);
            }
        }

        return toReturn;
    }

    private int getTotalNumberOfExercises(CourseInfo info) {
        int sum = 0;

        for (int points : info.getExercises()) {
            sum += points;
        }

        return sum;
    }

    private int getTotalNumberOfExercisesDone(List<Submission> submissions) {
        int sum = 0;

        for (Submission sub : submissions) {
            sum += sub.getExercises().size();
        }

        return sum;
    }

    private int getTotalNumberOfHoursWasted(List<Submission> submissions) {
        int sum = 0;

        for (Submission sub : submissions) {
            sum += sub.getHours();
        }

        return sum;
    }
    
    private String getCourseOverview(String courseName) throws IOException {
        CourseStats courseStats = CourseStatReader.getCourseStats(courseName);
        
        return "kurssilla yhteensä " + courseStats.returnsTotal + " palautusta, "
                + "palautettuja tehtäviä " + courseStats.exerciseTotal + " kpl, "
                + "aikaa käytetty yhteensä " + courseStats.hourTotal + " tuntia";
    }
}
