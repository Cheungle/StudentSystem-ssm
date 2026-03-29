package org.service.impl;

import org.dao.ExamDao;
import org.dao.ScDao;
import org.entity.DTO.QueryStudentDTO;
import org.entity.VO.TestInfoVO;
import org.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ExamDao examDao;
    @Autowired
    private ScDao scDao;

    @Override
    public Map<String, List<TestInfoVO>> getCoursesPastTest(QueryStudentDTO queryStudentDTO) {
        List<TestInfoVO> testInfoVOS = examDao.getCoursesPastTest(queryStudentDTO);
        Map<String, List<TestInfoVO>> groupMap = testInfoVOS.stream()
                .collect(Collectors.groupingBy(vo ->
                        vo.getAcademicYear() + " " + vo.getSemester()
                ));
        return groupMap;
    }

    @Override
    public List<TestInfoVO> getCoursesTest(QueryStudentDTO queryStudentDTO) {
        Set<Integer> courseIds = scDao.getSelectedCoursesByStudentId(queryStudentDTO);
        return examDao.getCoursesTest(courseIds);
    }
}
