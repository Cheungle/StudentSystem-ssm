package org.entity;

import lombok.Data;

@Data
public class coursePlan {
	private int idPlan;
	private int idTeacher;
	private String courseName;
	private int credit;
	private String courseKind;
	private String academicYear;
	private String semester;
	private int totalStock;
	private int remainderStock;
	private String sumClasses;
	private int status;
	private String teacherName;

}
