package org.service;


import org.entity.student;

import java.util.List;

public interface StudentService {

    /*增加学生*/
    boolean addStudent(student student);

    /*删除学生*/
    boolean deleteStudent(int id);

//    boolean updateStudent();

    /*查询学生列表*/
    List<student> queryAllStudent();

    /*查询学生信息*/
    student queryStudentById(int id);

}
