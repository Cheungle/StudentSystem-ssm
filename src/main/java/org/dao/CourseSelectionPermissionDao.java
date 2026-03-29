package org.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CourseSelectionPermissionDao {
    @Select("select permission from course_selection_permission where academic_year=#{academicYear} and semester=#{semester}")
    public boolean getCourseSelectionPermission(@Param("academicYear")String academicYear, @Param("semester")String semester);
}
