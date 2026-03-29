package org.service;


import org.entity.student;

import java.util.List;

public interface StudentService {

    /*增加学生*/
    public boolean addStudent(student student);

    /*删除学生*/
    public boolean deleteStudent(int id);

//    public boolean updateStudent();

    /*查询学生列表*/
    public List<student> queryAllStudent();

    /*查询学生信息*/
    public student queryStudentById(int id);

}
