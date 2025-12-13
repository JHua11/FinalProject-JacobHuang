package org.jhuang;

import lombok.Getter;

import java.util.List;
@Getter
public class Assignment {
    private String assignmentId;
    private String assignmentName;
    private double weight;
    private List<Integer> scores;
    private static int nextId = 1;
}
