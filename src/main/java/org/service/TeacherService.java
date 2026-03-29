package org.service;

import org.entity.teacher;

import java.util.List;

public interface TeacherService {

    /*查询教师列表*/
    public List<teacher> queryAllTeacher();

    /*增加教师*/
    public boolean addTeacher(teacher teacher);

    /*删除教师*/
    public boolean deleteTeacher(int id);
}
