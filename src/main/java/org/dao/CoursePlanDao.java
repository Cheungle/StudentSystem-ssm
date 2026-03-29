package org.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.entity.DTO.TermDTO;
import org.entity.VO.CourseListVO;
import org.entity.coursePlan;

import java.util.List;

@Mapper
public interface CoursePlanDao {

    @Select("select id_plan,course_name,teacher_name,course_kind,credit,total_stock,remainder_stock from course_plan " +
            "where academic_year=#{academicYear} and semester=#{semester} and status=1 " +
            "order by id_plan")
    List<CourseListVO> findAllCoursePlan(@Param("academicYear") String academicYear, @Param("semester") String semester);

    @Select(
            "<script>" +
                    "select remainder_stock from course_plan " +
                    "<where>" +
                    "academic_year = #{academicYear} " +
                    "and semester = #{semester} " +
                    "and status = 1 " +
                    "and id_plan in " +
                    "<foreach collection='idList' item='id' open='(' separator=',' close=')'>" +
                    "#{id}" +
                    "</foreach>" +
                    "</where> " +
                    "order by id_plan" +
                    "</script>"
    )
    List<Integer> findCoursePlanStockBetween(@Param("academicYear") String academicYear, @Param("semester") String semester,
                                             @Param("idList") List<Integer> idList);

    @Select("select count(*) from course_plan where academic_year=#{academicYear} and semester=#{semester}")
    int countTotalPlan(@Param("academicYear") String academicYear, @Param("semester") String semester);

    @Update("update course_plan set remainder_stock=#{stock} where id_plan=#{idPlan}")
    void updateStock(@Param("idPlan")int idPlan,@Param("stock")int stock);

    @Select("select id_plan from course_plan where academic_year= #{academicYear} and semester= #{semester} and status=1")
    List<Integer> findThisTermValidCourseIds(@Param("academicYear") String academicYear, @Param("semester") String semester);

    @Select("select academic_year,semester from course_selection_permission where permission=1")
    TermDTO getTermWithOpenSelection();

    @Select("select total_stock from course_plan where id_plan=#{idPlan}")
    int getTotalStockById(@Param("idPlan") int idPlan);

    @Update("update course_plan set remainder_stock=remainder_stock+1 where id_plan=#{idPlan}")
    void addStock(@Param("idPlan") int idPlan);
}
