package org.entity.VO;

import lombok.Data;

@Data
public class CourseListVO {
    private int idPlan;
    private String courseName;
    private String teacherName;
    private String courseKind;
    private int credit;
    private int totalStock;
    private int remainderStock;
}
