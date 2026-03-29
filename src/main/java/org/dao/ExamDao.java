package org.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.entity.DTO.QueryStudentDTO;
import org.entity.VO.TestInfoVO;

import java.util.List;
import java.util.Set;

@Mapper
public interface ExamDao {
    @Select("<script>" +
            "SELECT course_name,test_time,test_place,test_duration FROM exam " +
            "<where>" +
            "<if test='courseIds != null and courseIds.size() > 0'>" +
            "AND id_plan IN " +
            "<foreach collection='courseIds' item='item' open='(' separator=',' close=')'>" +
            "#{item}" +
            "</foreach>" +
            "</if>" +
            "<if test='courseIds == null or courseIds.size() == 0'>" +
            "1 = 0" +
            "</if>" +
            "</where>" +
            "</script>")
    List<TestInfoVO> getCoursesTest(@Param("courseIds")Set<Integer> courseIds);

    @Select("select exam.course_name,test_time,test_place,test_duration,academic_year,semester " +
            "from exam,course_choose " +
            "where id_student=#{id} and (academic_year!= #{academicYear} or semester!= #{semester}) " +
            "and exam.id_plan = course_choose.id_plan ")
    List<TestInfoVO> getCoursesPastTest(QueryStudentDTO queryStudentDTO);
}
