package org.jhuang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    @DisplayName("assignmentWeightCheck valid")
    void isAssignmentWeightValid1() {
        Course course = new Course("Course", 3, new Department("Department"));
        course.addAssignment("assignment", 40);
        course.addAssignment("assignment", 60);
        boolean expected = true;
        boolean result = course.isAssignmentWeightValid();
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("assignmentWeightCheck invalid (over)")
    void isAssignmentWeightValid2() {
        Course course = new Course("Course", 3, new Department("Department"));
        course.addAssignment("assignment", 40);
        course.addAssignment("assignment", 61);
        boolean expected = false;
        boolean result = course.isAssignmentWeightValid();
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("assignmentWeightCheck invalid (under)")
    void isAssignmentWeightValid3() {
        Course course = new Course("Course", 3, new Department("Department"));
        course.addAssignment("assignment", 40);
        course.addAssignment("assignment", 59);
        boolean expected = false;
        boolean result = course.isAssignmentWeightValid();
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("register valid student")
    void registerStudent1() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 100);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        boolean result = course.registerStudent(student);
        boolean expectedReturn = true;
        Assertions.assertEquals(expectedReturn, result);
        Assertions.assertEquals(1, course.getRegisteredStudents().size());
        Assertions.assertEquals(1, student.getRegisteredCourses().size());
        Assertions.assertEquals(1, course.getAssignments().get(0).getScores().size());
        Assertions.assertEquals(1, course.getFinalScores().size());
    }

    @Test
    @DisplayName("register invalid student")
    void registerStudent2() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 100);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student.registerCourse(course);
        boolean result = course.registerStudent(student);
        boolean expectedReturn = false;
        Assertions.assertEquals(expectedReturn, result);
        Assertions.assertEquals(1, course.getRegisteredStudents().size());
        Assertions.assertEquals(1, student.getRegisteredCourses().size());
        Assertions.assertEquals(1, course.getAssignments().get(0).getScores().size());
        Assertions.assertEquals(1, course.getFinalScores().size());
    }

    @Test
    @DisplayName("regular input, 2 students")
    void calcStudentsAverages1() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 60);
        course.addAssignment("Assignment02", 40);
        Student student0 = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student0.registerCourse(course);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student.registerCourse(course);
        course.getAssignments().get(0).getScores().set(0, 60);
        course.getAssignments().get(0).getScores().set(1, 70);
        course.getAssignments().get(1).getScores().set(0, 60);
        course.getAssignments().get(1).getScores().set(1, 50);
        int[] expected = {60, 62};
        int[] result = course.calcStudentsAverages();
        Assertions.assertArrayEquals(expected, result);
    }
    @Test
    @DisplayName("weightless input, 2 students")
    void calcStudentsAverages2() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 0);
        course.addAssignment("Assignment02", 0);
        Student student0 = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student0.registerCourse(course);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student.registerCourse(course);
        course.getAssignments().get(0).getScores().set(0, 60);
        course.getAssignments().get(0).getScores().set(1, 70);
        course.getAssignments().get(1).getScores().set(0, 60);
        course.getAssignments().get(1).getScores().set(1, 50);
        int[] expected = {0, 0};
        int[] result = course.calcStudentsAverages();
        Assertions.assertArrayEquals(expected, result);
    }

    @Test
    @DisplayName("generateScores for 2 students")
    void generateScores() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 50);
        course.addAssignment("Assignment02", 50);
        Student student0 = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student0.registerCourse(course);
        Student student = new Student("J", Student.Gender.MALE,
                new Address(1, "Street", "Mtl", Address.Province.AB, "A1B2C3"), department);
        student.registerCourse(course);
        course.generateScores();
        Assertions.assertEquals(2, course.getAssignments().get(0).getScores().size());
        Assertions.assertEquals(2, course.getAssignments().get(1).getScores().size());
        Assertions.assertEquals(2, course.getFinalScores().size());
        Assertions.assertNotEquals(null, course.getAssignments().get(0).getScores().get(0));
        Assertions.assertNotEquals(null, course.getAssignments().get(0).getScores().get(1));
        Assertions.assertNotEquals(null, course.getAssignments().get(1).getScores().get(0));
        Assertions.assertNotEquals(null, course.getAssignments().get(1).getScores().get(1));
        Assertions.assertNotEquals(null, course.getFinalScores().get(0));
        Assertions.assertNotEquals(null, course.getFinalScores().get(1));
    }

    @Test
    void addAssignment() {
        Department department = new Department("Mathematics");
        Course course = new Course("Math", 5, department);
        course.addAssignment("Assignment01", 50);
        course.addAssignment("Assignment02", 50);
        Assertions.assertEquals(2, course.getAssignments().size());
    }
}