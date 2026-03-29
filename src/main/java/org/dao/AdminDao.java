package org.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.entity.admin;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@Mapper
public interface AdminDao {

	@Select("select count(*) from admin")
	public String countAdmin();

	@Select("select * from admin where idadmin=#{id} and password=#{password}")
    public admin loginAdmin(@Param("id") int id , @Param("password") String password);
}
