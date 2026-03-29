package org.controller.student;

import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.common.Result;
import org.entity.DTO.QueryStudentDTO;
import org.entity.VO.CourseListVO;
import org.entity.VO.GradeInfoVO;
import org.entity.VO.TestInfoVO;
import org.entity.VO.TimetableVO;
import org.entity.coursePlan;
import org.entity.courseChoose;
import org.service.CoursePlanService;
import org.service.ExamService;
import org.service.ScService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/student")
@Slf4j
public class StudentCourseController {

    @Autowired
    public ScService scService;

    @Autowired
    public CoursePlanService coursePlanService;

    @Autowired
    public ExamService examService;

    @RequestMapping("/getGradeOfOne")
    public List<courseChoose> getGradeOfOne(@RequestBody QueryStudentDTO queryStudentDTO) {
        return scService.getGradeOfOne(queryStudentDTO);
    }

    @RequestMapping("/countCourseOfOne")
    public int countCourseOfOne(@RequestBody QueryStudentDTO queryStudentDTO) {
        return scService.countCourseOfOne(queryStudentDTO);
    }

    /*计算这学期已选的总学分*/
    @RequestMapping("/countCreditOfTerm")
    public int countCreditOfTerm(@RequestBody QueryStudentDTO queryStudentDTO) {
        return scService.countCreditOfTerm(queryStudentDTO);
    }

    /*计算已修学分*/
    @RequestMapping("/countBeforeCreditOfOne")
    public int countBeforeCreditOfOne(@RequestBody QueryStudentDTO queryStudentDTO) {
        return scService.countBeforeCreditOfOne(queryStudentDTO);
    }

    @RequestMapping("/getTodayCourse")
    public List<TimetableVO> getTodayCourse(@RequestBody QueryStudentDTO queryStudentDTO) {
        return scService.getTodayCourse(queryStudentDTO);
    }

    @RequestMapping("/getCourseSelectionPermission")
    public boolean isCanSelectCourse(@RequestParam String academicYear, @RequestParam String semester) {
        return coursePlanService.isCanSelectCourse(academicYear, semester);
    }

    @RequestMapping("/getTimetableOfTerm")
    public List<TimetableVO> getTimetableOfTerm(@RequestBody QueryStudentDTO queryStudentDTO) {
        return scService.getTimetableOfTermByStudent(queryStudentDTO);
    }

    @RequestMapping("/getCourseSelectionList")
    public PageInfo<CourseListVO> getCourseSelectionList(@RequestParam(defaultValue = "1") int pageNum,
                                                         @RequestParam(defaultValue = "8") int pageSize,
                                                         @RequestParam String academicYear,
                                                         @RequestParam String semester) {
        return coursePlanService.findCoursePlanByPage(pageNum, pageSize, academicYear, semester);
    }
    @RequestMapping("/selectOneCourse")
    public Result selectOneCourse(@RequestParam int idPlan, @RequestParam int idStudent) {
        try {
            // 直接丢给异步线程池，立即返回
            scService.selectOneCourse(idPlan, idStudent);
            return Result.success();
        } catch (Exception e) {
            // 线程池满 → 抛出拒绝异常 → 返回繁忙
            throw new RuntimeException("系统繁忙，请稍后再试");
        }
    }
    @RequestMapping("/cancelOneCourse")
    public Result cancelOneCourse(@RequestParam int idPlan, @RequestParam int idStudent) {
        return scService.cancelOneCourse(idPlan, idStudent);
    }
    @RequestMapping("/getSelectResult")
    public Result getSelectResult(@RequestParam int idPlan, @RequestParam int idStudent) {
        return scService.getSelectResult(idPlan, idStudent);
    }
    @RequestMapping("/getCoursesTest")
    public List<TestInfoVO> getCoursesTest(@RequestBody QueryStudentDTO queryStudentDTO){
        return examService.getCoursesTest(queryStudentDTO);
    }

    @RequestMapping("/getCoursesPastTest")
    public Map<String, List<TestInfoVO>> getCoursesPastTest(@RequestBody QueryStudentDTO queryStudentDTO){
        return examService.getCoursesPastTest(queryStudentDTO);
    }

    @RequestMapping("/getCoursesGrade")
    public List<GradeInfoVO> getCoursesGrade(@RequestBody QueryStudentDTO queryStudentDTO){
        return scService.getCoursesGrade(queryStudentDTO);
    }

    @RequestMapping("/getCoursesPastGrade")
    public Map<String,List<GradeInfoVO>> getCoursesPastGrade(@RequestBody QueryStudentDTO queryStudentDTO){
        return scService.getCoursesPastGrade(queryStudentDTO);
    }
}
