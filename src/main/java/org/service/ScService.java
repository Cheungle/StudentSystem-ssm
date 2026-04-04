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

    double getAverageGPA();

    /*按学生id和学期查找课程*/
    List<TimetableVO> getTimetableOfTermByStudent(QueryStudentDTO queryStudentDTO);

    /*按学生id和学期查找成绩*/
    List<courseChoose> getGradeOfOne(QueryStudentDTO queryStudentDTO);

    /*按课程id查找成绩*/
    List<courseChoose> getGradeByCourseID(int id);

    /*按学生id和学期计算课程数*/
    int countCourseOfOne(QueryStudentDTO queryStudentDTO);

    /*按学生id和学期计算学分*/
    int countCreditOfTerm(QueryStudentDTO queryStudentDTO);

    /*按学生id和学期计算以前学期学分*/
    int countBeforeCreditOfOne(QueryStudentDTO queryStudentDTO);

    /*展示今天的课表*/
    List<TimetableVO> getTodayCourse(QueryStudentDTO queryStudentDTO);

    /*学生选课*/
    void selectOneCourse(int idPlan, int idStudent);

    /*学生退课*/
    Result cancelOneCourse(int idPlan, int idStudent);
    /*判断该学生是否已选过该课程*/
//    boolean checkIfSelected(int courseId,int studentId);

    /*前端轮询查选课结果*/
    Result getSelectResult(int idPlan, int idStudent);

    List<GradeInfoVO> getCoursesGrade(QueryStudentDTO queryStudentDTO);

    Map<String,List<GradeInfoVO>> getCoursesPastGrade(QueryStudentDTO queryStudentDTO);
}
