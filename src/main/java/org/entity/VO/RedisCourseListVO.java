package org.entity.VO;

import lombok.Data;

@Data
public class RedisCourseListVO {
    private int idPlan;
    private String courseName;
    private String teacherName;
    private String courseKind;
    private int credit;
    private int totalStock;
}
