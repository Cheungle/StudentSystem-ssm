package org.entity.VO;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public class GradeInfoVO {
    private String courseName;
    private Integer grade;
    private int credit;
    private Double gpa;
    private String academicYear;
    private String semester;

    public void setGrade(Integer grade) {
        this.grade = grade;
        // 赋值 grade 时，自动计算 gpa
        if(grade!=null)this.gpa = calculateGpa(grade);
    }

    // 绩点算法
    private Double calculateGpa(Integer grade) {
        if (grade == null) return 0.0;
        if (grade >= 90) return 4.0;
        if (grade >= 80) return 3.0;
        if (grade >= 70) return 2.0;
        if (grade >= 60) return 1.0;
        return 0.0;
    }
}
