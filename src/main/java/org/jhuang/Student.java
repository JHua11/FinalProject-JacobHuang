package org.jhuang;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

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
    boolean registerCourse(Course course) {
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
    boolean dropCourse(Course course) {
        if (!registeredCourses.contains(course)) {
            return false;
        }
        registeredCourses.remove(course);
        course.getRegisteredStudents().remove(this);
        return true;
    }

    private enum Gender {
        MALE, FEMALE
    }
}
