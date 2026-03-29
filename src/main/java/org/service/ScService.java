package org.service;

import org.common.Result;
import org.entity.DTO.QueryStudentDTO;
import org.entity.VO.GradeInfoVO;
import org.entity.VO.TimetableVO;
import org.entity.courseChoose;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Map;


public interface ScService {

    public double getAverageGPA();

    /*按学生id和学期查找课程*/
    public List<TimetableVO> getTimetableOfTermByStudent(QueryStudentDTO queryStudentDTO);

    /*按学生id和学期查找成绩*/
    public List<courseChoose> getGradeOfOne(QueryStudentDTO queryStudentDTO);

    /*按课程id查找成绩*/
    public List<courseChoose> getGradeByCourseID(int id);

    /*按学生id和学期计算课程数*/
    public int countCourseOfOne(QueryStudentDTO queryStudentDTO);

    /*按学生id和学期计算学分*/
    public int countCreditOfTerm(QueryStudentDTO queryStudentDTO);

    /*按学生id和学期计算以前学期学分*/
    public int countBeforeCreditOfOne(QueryStudentDTO queryStudentDTO);

    /*展示今天的课表*/
    public List<TimetableVO> getTodayCourse(QueryStudentDTO queryStudentDTO);

    /*学生选课*/
    public void selectOneCourse(int idPlan, int idStudent);

    /*学生退课*/
    public Result cancelOneCourse(int idPlan, int idStudent);
    /*判断该学生是否已选过该课程*/
//    public boolean checkIfSelected(int courseId,int studentId);

    /*前端轮询查选课结果*/
    public Result getSelectResult(int idPlan, int idStudent);

    public List<GradeInfoVO> getCoursesGrade(QueryStudentDTO queryStudentDTO);

    public Map<String,List<GradeInfoVO>> getCoursesPastGrade(QueryStudentDTO queryStudentDTO);
}
