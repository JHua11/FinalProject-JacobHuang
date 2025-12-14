package org.jhuang;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AssignmentTest {

    // Note: generateRandomScores() method is already tested through testing of Course.generateScores

    @Test
    @DisplayName("calcAssignmentAverage for valid score list")
    void calcAssignmentAvg1() {
        Assignment assignment = new Assignment("Assignment", 100);
        assignment.getScores().add(90);
        assignment.getScores().add(80);
        assignment.getScores().add(70);
        double expected = 80;
        double result = assignment.calcAssignmentAvg();
        Assertions.assertEquals(expected, result);
    }
    @Test
    @DisplayName("calcAssignmentAverage for empty score list")
    void calcAssignmentAvg2() {
        Assignment assignment = new Assignment("Assignment", 100);
        double expected = 0;
        double result = assignment.calcAssignmentAvg();
        Assertions.assertEquals(expected, result);
    }
}