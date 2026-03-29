package org.service;

import org.entity.VO.StudentInfoVO;
import org.entity.admin;

import java.util.List;
import java.util.Map;

public interface UserAuthService {

    /*登录验证身份并将个人信息存入redis*/
    admin login(int id, String password);

    /*获取用户信息*/
    StudentInfoVO getUserInfo(int id,String role);

}
