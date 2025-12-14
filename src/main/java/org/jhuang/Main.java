package org.jhuang;


import util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        Department department = new Department("Mathematics");
        Course discrete = new Course("discrete mathematics", 5, department);
        Assignment assignment1 = new Assignment("Assignment01", 25);
        Assignment assignment2 = new Assignment("Assignment02", 25);
        Assignment assignment3 = new Assignment("Assignment03", 25);
        Assignment assignment4 = new Assignment("Assignment04", 25);
        Address address = new Address(12, "abc", "Mtl", Address.Province.AB, "H4L3H6");
        Student student1 = new Student("Jacob1", Student.Gender.MALE, address, department);
        Student student2 = new Student("Jacob2", Student.Gender.MALE, address, department);
        Student student3 = new Student("Jacob33333333", Student.Gender.MALE, address, department);
        Student student4 = new Student("Jacob4", Student.Gender.MALE, address, department);
        Student[] students = {student1, student2, student3, student4};
        Assignment[] assignments = {assignment1, assignment2, assignment3, assignment4};
        for (Assignment assignment : assignments) {
            discrete.getAssignments().add(assignment);
        }
        for (Student student : students) {
            discrete.registerStudent(student);
        }
        discrete.generateScores();
        discrete.displayScores();
        System.out.println();
        System.out.println(discrete);
    }
}
