package org.service;

import org.entity.admin;

import java.util.List;

public interface AdminService {

    /*查询管理员列表*/
    public List<admin> queryAllAdmins();

    /*增加管理员*/
    public boolean addAdmin(admin admin);

    /*删除管理员*/
    public boolean deleteAdmin(int id);
}
