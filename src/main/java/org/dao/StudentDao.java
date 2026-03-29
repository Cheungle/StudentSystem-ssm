package org.dao;

import org.apache.ibatis.annotations.*;
import org.entity.student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface StudentDao {
	@Select("select* from student")
	public List<student> queryAllStudent();

	@Select("select* from student where academy=#{academy}")
	public List<student> findStudentOfMajor(String major);

	@Select("select* from student where academy= #{academy} order by major")
	public List<student> OrderStudentOfAcademy(String academy);

	@Select("select major,count(idstudent) from student where academy= #{academy} group by major")
	public List<Integer> CountStudentOfMajor(String academy);

	@Select("select* from student where idstudent= #{id}")
	public student selectStudentById(String id);

	@Select("select count(*) from student")
    public String countStudent();

	@Insert("insert into student values(#{id},#{name},#{classstudent},#{major},#{academy},#{photo})")
	public void addStudent(student student);

	@Insert("insert into student values(#{id},#{name},#{classstudent},#{major},#{academy})")
	public void addStudentWithoutPhoto(student student);

	@Delete("delete from student where idstudent=#{id}")
	public void deleteStudent(String id);

	@Update("update student set namestudent=#{name},classstudent=#{classstudent}, major=#{major}, academy=#{academy}, photo=#{photo} where idstudent=#{id}")
	public void updateStudent(student student);

	@Update("update student set namestudent=#{name},classstudent=#{classstudent}, major=#{major}, academy=#{academy} where idstudent=#{id}")
	public void updateStudentWithoutPhoto(student student);

	@Select("select* from student where idstudent= #{id}")
	public student queryStudentById(int id);
    
}
