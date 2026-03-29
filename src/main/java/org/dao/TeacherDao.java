package org.dao;

import org.apache.ibatis.annotations.*;
import org.entity.teacher;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface TeacherDao {

	@Select("select* from teacher")
	public List<teacher> queryAllTeacher();

	@Select("select* from teacher where academy=#{academy}")
	public List<teacher> findTeacherOfAcademy(String academy);

	@Select("select* from teacher where idteacher= #{id}")
	public teacher selectTeacher(String id);

	@Select("select count(*) from teacher")
    public String countTeacher();

   @Insert("insert into teacher values(#{id},#{name},#{office},#{academy})")
	public void addTeacher(teacher teacher);

   @Delete("delete from teacher where idteacher= #{id}")
	public void deleteTeacher(String id);

   @Update("update teacher set nameteacher= #{name}, office= #{office}, academy= #{academy} where idteacher= #{id}")
	public void updateTeacherNameOfficeAcademy(teacher teacher);

   @Update("update teacher set nameteacher= #{name}, office= #{office} where idteacher= #{id}")
	public void updateTeacherNameOffice(teacher teacher);

}