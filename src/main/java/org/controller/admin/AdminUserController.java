package org.controller.admin;

import org.entity.admin;
import org.entity.student;
import org.entity.teacher;
import org.service.AdminService;
import org.service.StudentService;
import org.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminUserController {

    @Autowired
    public StudentService studentService;

    @Autowired
    public TeacherService teacherService;

    @Autowired
    public AdminService adminService;

    /*学生管理*/
    @RequestMapping("/queryAllStudent")
    public List<student> queryAllStudent(){
        return studentService.queryAllStudent();
    }

    @RequestMapping("/addStudent")
    public boolean addStudent(student student){
        return studentService.addStudent(student);
    }

    @RequestMapping("/deleteStudent")
    public boolean deleteStudent(int id){
        return studentService.deleteStudent(id);
    }


    /*教师管理*/
    @RequestMapping("/queryAllTeacher")
    public List<teacher> queryAllTeacher(){
        return teacherService.queryAllTeacher();
    }

    @RequestMapping("/addTeacher")
    public boolean addTeacher(@RequestBody teacher teacher){
        return teacherService.addTeacher(teacher);
    }

    @RequestMapping("/deleteTeacher")
    public boolean deleteTeacher(int id){
        return teacherService.deleteTeacher(id);
    }


    /*管理员*/
    @RequestMapping("/queryAllAdmin")
    public List<admin> queryAllAdmin(){
        return adminService.queryAllAdmins();
    }

}
