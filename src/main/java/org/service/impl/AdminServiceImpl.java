package org.service.impl;

import org.entity.admin;
import org.service.AdminService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {
    @Override
    public List<admin> queryAllAdmins() {
        return null;
    }

    @Override
    public boolean addAdmin(admin admin) {
        return false;
    }

    @Override
    public boolean deleteAdmin(int id) {
        return false;
    }
}
