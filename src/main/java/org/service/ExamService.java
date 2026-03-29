package org.service;

import org.entity.DTO.QueryStudentDTO;
import org.entity.VO.TestInfoVO;

import java.util.List;
import java.util.Map;

public interface ExamService {

    /* 查找该学生该学期的考试信息 */
    List<TestInfoVO> getCoursesTest(QueryStudentDTO queryStudentDTO);

    /* 查找该学生以前的考试信息 */
    Map<String, List<TestInfoVO>> getCoursesPastTest(QueryStudentDTO queryStudentDTO);
}
