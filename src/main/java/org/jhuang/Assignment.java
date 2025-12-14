package org.jhuang;

import lombok.Getter;

import java.util.List;
import java.util.Random;

@Getter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private List<Integer> scores;
    private static int nextId = 1;

    public Assignment(String assignmentName, double weight) {
        this.assignmentId = String.format("Assignment%02d", nextId++);
        this.assignmentName = assignmentName;
        this.weight = weight;
    }

    /**
     * calculates the average score the assignment
     * @return the calculated average
     */
    public double calcAssignmentAvg() {
        double sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return sum / scores.size();
    }

    /**
     * generates a random score for every student in the assignment;
     * The scores are generated with the following rule:
     * Firstly generate a random number in range [0, 10], then
     * if the number is 0, then generate a random score in range [0, 60) for the student;
     * if the number is 1, 2, then generate a random score in range [60, 70) for the student;
     * if the number is 3, 4, then generate a random score in range [70, 80) for the student;
     * if the number is 5, 6, 7, 8, then generate a random score in range [80, 90) for the student;
     * if the number is 9, 10, then generate a random score in range [90, 100] for the student
     */
    public void generateRandomScore() {
        Random random = new Random();
        for (int i = 0; i < scores.size(); i++) {
            int determinant = random.nextInt(0, 11);
            scores.set(i, switch (determinant) {
                case 0 -> random.nextInt(0, 60);
                case 1, 2 -> random.nextInt(60, 70);
                case 3, 4 -> random.nextInt(70, 80);
                case 5, 6, 7, 8 -> random.nextInt(80, 90);
                case 9, 10 -> random.nextInt(90, 101);
                default -> null;
            });
        }
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "assignmentId='" + assignmentId + '\'' +
                ", assignmentName='" + assignmentName + '\'' +
                ", weight=" + weight +
                '}';
    }
}
