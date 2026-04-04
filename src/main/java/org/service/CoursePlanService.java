package org.service;

import com.github.pagehelper.PageInfo;
import org.entity.VO.CourseListVO;
import org.entity.coursePlan;

import java.util.List;

public interface CoursePlanService {
    /*查询是否能够进入选课页面*/
    boolean isCanSelectCourse(String academicYear, String semester);

    PageInfo<CourseListVO> findCoursePlanByPage(int pageNum, int pageSize, String academicYear, String semester);

    boolean addCourse(coursePlan coursePlan);

    boolean updateCourse(coursePlan coursePlan);

    boolean deleteCourse(int idPlan);

    int countCourse();

    List<coursePlan> queryCourseByTeacherId(int id);
}
