package org.entity.VO;

import lombok.Data;

@Data
public class TimetableVO {
    private String weekday;
    private String startTime;
    private String sumClasses;
    private String classroom;
    private String courseName;
    private String teacherName;
}
