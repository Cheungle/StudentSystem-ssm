package org.service;

import com.github.pagehelper.PageInfo;
import org.entity.VO.CourseListVO;
import org.entity.coursePlan;

import java.util.List;

public interface CoursePlanService {
    /*查询是否能够进入选课页面*/
    public boolean isCanSelectCourse(String academicYear, String semester);

    public PageInfo<CourseListVO> findCoursePlanByPage(int pageNum, int pageSize, String academicYear, String semester);

    public boolean addCourse(coursePlan coursePlan);

    public boolean updateCourse(coursePlan coursePlan);

    public boolean deleteCourse(int idPlan);

    public int countCourse();

    public List<coursePlan> queryCourseByTeacherId(int id);
}
