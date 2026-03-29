package org.entity;

import lombok.Data;

@Data
public class exam {
    private int idExam;
    private String testPlace;
    private String testTime;
    private String testType;
    private String testDuration;
    private String testName;
    private int idPlan;
}
