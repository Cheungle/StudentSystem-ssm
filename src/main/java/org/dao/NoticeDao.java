package org.dao;

import org.apache.ibatis.annotations.*;
import org.entity.notice;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Mapper
public interface NoticeDao {
	@Select("select* from notice order by pubdate DESC")
	public List<notice> queryAllNotice();

	@Select("select* from notice where idnotice=#{id}")
	public notice selectNotice(String id);

	@Select("select count(*) from notice")
	public String countNotice();

	@Insert("insert into notice values(#{id},#{name},#{classroom},#{credit},#{teacher},#{date},#{starttime},#{sumclass})")
	public void addNotice(notice notice);

	@Delete("delete from notice where idnotice=#{id}")
	public void deleteNotice(String id);

	@Update("update notice set namestudent=#{name},classroom=#{classroom},credit=#{credit},teacher=#{teacher},date=#{date},starttime=#{starttime},sumclass=#{sumclass} where idnotice=#{id}")
	public void updateNotice(notice notice);
}
