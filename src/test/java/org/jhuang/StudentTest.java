package org.jhuang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentTest {

    @Test
    @DisplayName("register valid course")
    void registerCourse1() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 100);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        boolean result = student.registerCourse(course);
        boolean expectedReturn = true;
        Assertions.assertEquals(expectedReturn, result);
        Assertions.assertEquals(1, course.getRegisteredStudents().size());
        Assertions.assertEquals(1, student.getRegisteredCourses().size());
        Assertions.assertEquals(1, course.getAssignments().get(0).getScores().size());
        Assertions.assertEquals(1, course.getFinalScores().size());
    }
    @Test
    @DisplayName("register invalid course")
    void registerCourse2() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 100);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student.registerCourse(course);
        boolean expectedReturn = false;
        boolean result = student.registerCourse(course);
        Assertions.assertEquals(expectedReturn, result);
        Assertions.assertEquals(1, course.getRegisteredStudents().size());
        Assertions.assertEquals(1, student.getRegisteredCourses().size());
        Assertions.assertEquals(1, course.getAssignments().get(0).getScores().size());
        Assertions.assertEquals(1, course.getFinalScores().size());
    }

    @Test
    @DisplayName("drop valid course")
    void dropCourse1() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 100);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student.registerCourse(course);
        boolean expectedReturn = true;
        boolean result = student.dropCourse(course);
        Assertions.assertEquals(expectedReturn, result);
        Assertions.assertEquals(0, course.getRegisteredStudents().size());
        Assertions.assertEquals(0, student.getRegisteredCourses().size());
        Assertions.assertEquals(0, course.getAssignments().get(0).getScores().size());
        Assertions.assertEquals(0, course.getFinalScores().size());
    }
    @Test
    @DisplayName("drop invalid course")
    void dropCourse2() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 100);
        Student student0 = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student0.registerCourse(course);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        boolean expectedReturn = false;
        boolean result = student.dropCourse(course);
        Assertions.assertEquals(expectedReturn, result);
        Assertions.assertEquals(1, course.getRegisteredStudents().size());
        Assertions.assertEquals(0, student.getRegisteredCourses().size());
        Assertions.assertEquals(1, course.getAssignments().get(0).getScores().size());
        Assertions.assertEquals(1, course.getFinalScores().size());
    }
}