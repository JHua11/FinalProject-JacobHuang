package org.jhuang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

@EqualsAndHashCode
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private ArrayList<Assignment> assignments;
    private ArrayList<Student> registeredStudents;
    private ArrayList<Integer> finalScores;
    private static int nextId = 1;

    public Course(String courseName, double credits, Department department) {
        this.courseId = String.format("C-%s-%02d", department.getDepartmentId(), nextId++);
        this.courseName = Util.toTitleCase(courseName);
        this.credits = credits;
        this.department = department;
        this.assignments = new ArrayList<>();
        this.registeredStudents = new ArrayList<>();
        this.finalScores = new ArrayList<>();
    }

    /**
     * checks if the sum of weights of all assignments of that course equals to 100%
     * @return whether the sum is 100 (true) or not (false)
     */
    public boolean isAssignmentWeightValid() {
        double weightSum = 0;
        for (Assignment assignment : assignments) {
            weightSum += assignment.getWeight();
        }
        return weightSum == 100;
    }

    /**
     * adds a student to the student list of the course
     * @param student the student to be added
     * @return whether the operation was successful (true) or not (false)
     */
    public boolean registerStudent(Student student) {
        if (registeredStudents.contains(student)) {
            return false;
        }
        registeredStudents.add(student);
        student.getRegisteredCourses().add(this);
        for (Assignment assignment : assignments) {
            assignment.getScores().add(null);
        }
        finalScores.add(null);
        return true;
    }

    /**
     * calculates the weighted average score of each student in a course
     * @return the average score
     */
    public int[] calcStudentsAverages() {
        int[] averages = new int[registeredStudents.size()];
        for (int i = 0; i < registeredStudents.size(); i++) {
            double gradeSum = 0;
            double weightSum = 0;
            for (Assignment assignment : assignments) {
                gradeSum += assignment.getScores().get(i) * assignment.getWeight();
                weightSum += assignment.getWeight();
            }
            if (weightSum == 0) {
                Arrays.fill(averages, 0);
                return averages;
            }
            averages[i] = (int) Math.round(gradeSum / weightSum);
        }
        return averages;
    }

    /**
     * adds a new assignment to the course
     * @param assignmentName the name of the assignment to be added
     * @param weight the weight of the assignment to be added
     * @return true
     */
    public boolean addAssignment(String assignmentName, double weight) {
        assignments.add(new Assignment(assignmentName, weight));
        return true;
    }

    /**
     * generates random scores for each assignment and student
     * and calculate the final score for each student
     */
    public void generateScores() {
        Random random = new Random();
        for (Assignment assignment : assignments) {
            assignment.generateRandomScores();
        }
        for (int i = 0; i < finalScores.size(); i++) {
            finalScores.set(i, calcStudentsAverages()[i]);
        }
    }

    /**
     * finds the longest string in an ArrayList
     * @param strs the ArrayList
     * @return the length of the longest string
     */
    private int longestStringLen(ArrayList<String> strs) {
        int len = 0;
        for (String str : strs) {
            if (str == null) {
                continue;
            }
            if (str.length() > len) {
                len = str.length();
            }
        }
        return len;
    }

    /**
     * extracts the names of students in a student arraylist
     * @param students the arraylist of students
     * @return an arraylist of the names of the students
     */
    private ArrayList<String> getNames(ArrayList<Student> students) {
        ArrayList<String> names = new ArrayList<>();
        for (Student student : students) {
            names.add(student.getStudentName());
        }
        return names;
    }

    /**
     * formats a line for a student to be printed in displayScores();
     * includes the name and scores of a student
     * @param student the student
     * @param maxNameLen the maximum name length in all the students in the course
     * @return the formatted line
     */
    private String studentLine(Student student, int maxNameLen) {
        int studentIdx = registeredStudents.indexOf(student);
        String format = "%-" + maxNameLen + "s";
        String line = String.format("%8s" + format, "", student.getStudentName());
        for (Assignment assignment : assignments) {
            int score = assignment.getScores().get(studentIdx);
            line += String.format("%14d", score);
        }
        line += String.format("%14d\n", finalScores.get(studentIdx));
        return line;
    }

    /**
     * formats a line for the assignment names to be printed in displayScores()
     * @param distance the distance between the left bound and the first assignment name
     * @return the formatted line
     */
    private String assignmentNamesLine(int distance) {
        String format = "%" + distance + "s";
        String line = String.format(format, "");
        for (Assignment assignment : assignments) {
           line += String.format("%14s", assignment.getAssignmentId());
        }
        line += String.format("%14s", "Final Score");
        return line;
    }

    /**
     * calculates the final average of all the course's students
     * @return the average
     */
    private int calcFinalScoresAvg() {
        if (finalScores.isEmpty()) {
            return 0;
        }
        double sum = 0;
        for (int score : finalScores) {
            sum += score;
        }
        return (int) Math.round(sum / finalScores.size());
    }

    /**
     * formats a line for the "Average" row to be printed in displayScores()
     * @param maxNameLen the maximum name length in all the students in the course
     * @return the formatted line
     */
    private String averageLine(int maxNameLen) {
        String format = "%-" + maxNameLen + "s";
        String line = String.format("%8s" + format, "", "Average");
        for (Assignment assignment : assignments) {
            line += String.format("%14d", (int) Math.round(assignment.calcAssignmentAvg())); // the example shows integers
        }
        line += String.format("%14d\n", calcFinalScoresAvg());
        return line;
    }

    /**
     * displays the scores of a course in a table, with the assignment averages and student weighted average
     */
    public void displayScores() {
        int longestNameLen = longestStringLen(getNames(registeredStudents));
        int assignmentTitlesDistance = 8 + longestNameLen;
        String format = "%" + assignmentTitlesDistance + "s\n";
        System.out.printf("Course: %s(%s)\n", courseName, courseId);
        System.out.printf(format, assignmentNamesLine(assignmentTitlesDistance));
        for (Student student : registeredStudents) {
            System.out.printf(studentLine(student, longestNameLen));
        }
        System.out.printf(averageLine(longestNameLen));
    }

    public String toSimplifiedString() {
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department=" + department.getDepartmentName() +
                '}';
    }

    @Override
    public String toString() {
        String studentDisplay = "";
        for (Student student : registeredStudents) {
            studentDisplay += student.toSimplifiedString() + ", ";
        }
        studentDisplay = studentDisplay.substring(0,studentDisplay.length() - 2);
        return "Course{" +
                "courseId='" + courseId + '\'' +
                ", courseName='" + courseName + '\'' +
                ", credits=" + credits +
                ", department=" + department.getDepartmentName() +
                ", assignments=" + assignments +
                ", registeredStudents=" + studentDisplay +
                ", currentAssignmentWeightValid=" + isAssignmentWeightValid() +
                '}';
    }
}
