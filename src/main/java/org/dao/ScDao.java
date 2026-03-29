package org.dao;

import org.apache.ibatis.annotations.*;
import org.entity.*;
import org.entity.DTO.QueryStudentDTO;
import org.entity.VO.TimetableVO;

import java.util.List;
import java.util.Set;

@Mapper
public interface ScDao {

    @Select("select count(*) from course_choose where id_student= #{id} and academic_year= #{academicYear} and semester=#{semester}")
    public int countCourseOfOne(QueryStudentDTO queryStudentDTO);

    @Select("select count(credit) from course_choose " +
            "where id_student= #{id} and academic_year= #{academicYear} and semester=#{semester}")
    int countCreditOfTerm(QueryStudentDTO queryStudentDTO);

    @Select("select count(credit) from course_choose " +
            "where id_student= #{id} and (academic_year != #{academicYear} or semester != #{semester})")
    int countBeforeCreditOfOne(QueryStudentDTO queryStudentDTO);

    @Select("select course_name,weekday,start_time,sum_classes,classroom,teacher_name from course_schedule " +
            "join course_choose on course_choose.id_plan = course_schedule.id_plan and course_choose.id_student = #{id} " +
            "and academic_year= #{academicYear} and semester= #{semester} and weekday= #{today}")
    List<TimetableVO> getTodayCourse(QueryStudentDTO queryStudentDTO);

    @Select("select course_name,weekday,start_time,sum_classes,classroom,teacher_name from course_schedule " +
            "join course_choose on course_choose.id_plan = course_schedule.id_plan " +
            "and course_choose.id_student = #{id} and academic_year= #{academicYear} and semester= #{semester}")
    List<TimetableVO> getTimetableOfTermByStudent(QueryStudentDTO queryStudentDTO);

    @Select("select id_student from course_choose where id_plan = #{courseId}")
    Set<Integer> getSelectedStudentsByCourseId(int courseId);

    @Insert("INSERT INTO course_choose (id_student, id_plan, course_name, credit, academic_year, semester) " +
            "SELECT #{studentId}, cp.id_plan, cp.course_name, cp.credit, cp.academic_year, cp.semester " +
            "FROM course_plan cp WHERE cp.id_plan = #{courseId}")
    void addSelectRecord(@Param("courseId") int courseId, @Param("studentId") int studentId);

    @Select("SELECT COUNT(*) FROM course_choose WHERE id_plan = #{courseId}")
    int countByCourseId(int courseId);

    @Delete("DELETE FROM course_choose WHERE id_plan = #{courseId} AND id_student = #{studentId}")
    void cancelCourseSelection(@Param("courseId") int courseId, @Param("studentId") int studentId);
}
