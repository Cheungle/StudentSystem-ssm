package org.service;

import org.entity.teacher;

import java.util.List;

public interface TeacherService {

    /*查询教师列表*/
    List<teacher> queryAllTeacher();

    /*增加教师*/
    boolean addTeacher(teacher teacher);

    /*删除教师*/
    boolean deleteTeacher(int id);
}
