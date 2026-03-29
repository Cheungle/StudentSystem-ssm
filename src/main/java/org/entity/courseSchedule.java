package org.entity;

import lombok.Data;

@Data
public class courseSchedule {
    private int idSchedule;
    private int idPlan;
    private String classroom;
    private String weekday;
    private String startTime;
    private String sumClasses;

}
