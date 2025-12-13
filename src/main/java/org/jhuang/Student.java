package org.jhuang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@EqualsAndHashCode
@Getter
@Setter
public class Student {
    private String studentId;
    private String studentName;
    private Gender gender;
    private Address address;
    private Department department;
    private List<Course> registeredCourses;
    private static int nextId = 1;

    /**
     * registers the student to a course; this method (1) adds the course to the
     * student's registeredCourses list, (2) adds the student to the course's
     * registeredStudents list, (3) appends a null for the scores of each assignment
     * of the course. If the course is already registered (exists in the student's
     * registeredCourses list), directly returns false
     * @param course the course the student is being registered to
     * @return whether the operation was successful (true) or not (false)
     */
    public boolean registerCourse(Course course) {
        if (registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.add(course);
        course.getRegisteredStudents().add(this);
        for (Assignment assignment : course.getAssignments()) {
            assignment.getScores().add(null);
        }
        return true;
    }

    /**
     * removes the course form the student's registeredCourses list
     * and removes the student from the course's registeredStudents list.
     * If the course is not registered yet, directly returns false
     * @param course the course to be dropped
     * @return whether the operation was successful (true) or not (false)
     */
    public boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);
        return true;
    }

    public Student(String studentName, Gender gender, Address address, Department department) {
        this.studentId = String.format("S%06d", nextId++);
        this.studentName = studentName;
        this.gender = gender;
        this.address = address;
        this.department = department;
        this.registeredCourses = new ArrayList<>();
    }

    public String toSimplifiedString() {
        return "Student{" +
                "department=" + department +
                ", studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                '}';
    }

    @Override
    public String toString() {
        String courseDisplay = "";
        for (Course course : registeredCourses) {
            courseDisplay += course.toSimplifiedString() + ", ";
        }
        courseDisplay = courseDisplay.substring(0,courseDisplay.length() - 2);
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", gender=" + gender +
                ", address=" + address +
                ", department=" + department +
                ", registeredCourses=" + courseDisplay +
                '}';
    }

    private enum Gender {
        MALE, FEMALE
    }
}
