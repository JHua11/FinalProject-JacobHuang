package org.jhuang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DepartmentTest {

    @Test
    @DisplayName("nameCheck valid name input")
    void isDepartmentNameValid1() {
        String departmentName = "Compute Science and Math";
        boolean expected = true;
        boolean result = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("nameCheck invalid name input (symbol)")
    void isDepartmentNameValid2() {
        String departmentName = "Compute Science & Math";
        boolean expected = false;
        boolean result = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("nameCheck invalid name input (digit)")
    void isDepartmentNameValid3() {
        String departmentName = "Math 101";
        boolean expected = false;
        boolean result = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("nameCheck empty input")
    void isDepartmentNameValid4() {
        String departmentName = "";
        boolean expected = false;
        boolean result = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("nameCheck null input")
    void isDepartmentNameValid5() {
        String departmentName = null;
        boolean expected = false;
        boolean result = Department.isDepartmentNameValid(departmentName);
        Assertions.assertEquals(expected, result);
    }

    @Test
    @DisplayName("constructor valid department name (1)")
    void Department1() {
        String departmentName = "Math";
        String expectedId = "D01";
        Department result = new Department(departmentName);
        Assertions.assertEquals(departmentName, result.getDepartmentName());
        Assertions.assertEquals(expectedId, result.getDepartmentId());
    }
    @Test
    @DisplayName("constructor valid department name (2)")
    void Department2() {
        String departmentName = "Science";
        String expectedId = "D02";
        Department result = new Department(departmentName);
        Assertions.assertEquals(departmentName, result.getDepartmentName());
        Assertions.assertEquals(expectedId, result.getDepartmentId());
    }
    @Test
    @DisplayName("constructor invalid department name")
    void Department3() {
        String departmentName = "Math 101";
        String expectedId = null;
        Department result = new Department(departmentName);
        Assertions.assertEquals(null, result.getDepartmentName());
        Assertions.assertEquals(expectedId, result.getDepartmentId());
    }

    @Test
    @DisplayName("setter valid department name")
    void setDepartmentName1() {
        Department result = new Department("Science");
        String newDepartmentName = "math";
        String expectedName = "Math";
        result.setDepartmentName(newDepartmentName);
        Assertions.assertEquals(expectedName, result.getDepartmentName());
    }
    @Test
    @DisplayName("setter invalid department name")
    void setDepartmentName2() {
        Department result = new Department("Science");
        String newDepartmentName = "math 101";
        String expectedName = "Science";
        result.setDepartmentName(newDepartmentName);
        Assertions.assertEquals(expectedName, result.getDepartmentName());
    }
}