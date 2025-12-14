package org.jhuang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import util.Util;

import java.util.List;
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
            averages[i] = (int) (gradeSum / weightSum);
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
            assignment.generateRandomScore();
        }
        for (int i = 0; i < finalScores.size(); i++) {
            finalScores.set(i, calcStudentsAverages()[i]);
        }
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
