package org.service.impl;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.common.BaseContext;
import org.common.JWTContent;
import org.dao.AdminDao;
import org.dao.StudentDao;
import org.dao.TeacherDao;
import org.entity.VO.StudentInfoVO;
import org.entity.admin;
import org.entity.student;
import org.service.StudentService;
import org.service.UserAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.util.JWTUtil;
import org.util.RedisUtil;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class UserAuthServiceImpl implements UserAuthService {

    @Autowired
    public AdminDao adminDao;
    @Autowired
    public StudentDao studentDao;
    @Autowired
    public TeacherDao teacherDao;
    @Autowired
    private StudentService studentService;
    @Autowired
    public RedisUtil redisUtil;

    @Override
    public admin login(int id, String password) {
        admin admin = adminDao.loginAdmin(id,password);
        if (admin != null) {
            log.info("用户登录成功");
            // 存储个人信息到redis中
            if(admin.getRole() == "student"){
                student student = studentDao.queryStudentById(id);
                if(student!=null){
                    StudentInfoVO studentInfoVO = new StudentInfoVO(student);
                    redisUtil.setUserInfo(id,studentInfoVO);
                }
            }
        }
        return admin;
    }

    @Override
    public StudentInfoVO getUserInfo(int id, String role) {
        if(!redisUtil.isHashEmpty(String.valueOf(id))){
            log.info("从redis中获取用户信息");
            Map<Object,Object> info = redisUtil.getUserInfo(id);
            StudentInfoVO studentInfoVO = new StudentInfoVO(info);
            studentInfoVO.setUserId(id);
            return studentInfoVO;
        }else{
            log.info("从数据库中获取用户信息");
            if("student".equals(role)){
                log.info("学生信息");
                student student = studentService.queryStudentById(id);
                StudentInfoVO studentInfoVO = new StudentInfoVO(student);
                studentInfoVO.setUserId(id);
                redisUtil.setUserInfo(id,studentInfoVO);
                return studentInfoVO;
            }
        }
        return null;
    }
}
