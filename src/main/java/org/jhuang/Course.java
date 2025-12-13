package org.jhuang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
@EqualsAndHashCode
@Getter
@Setter
public class Course {
    private String courseId;
    private String courseName;
    private double credits;
    private Department department;
    private List<Assignment> assignments;
    private List<Student> registeredStudents;
    private static int nextId = 1;

    public boolean isAssignmentWeightValid() {
        double weightSum = 0;
        for (Assignment assignment : assignments) {
            weightSum += assignment.getWeight();
        }
        return weightSum == 100;
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
