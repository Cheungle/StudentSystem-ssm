package org.service.impl;

import org.entity.teacher;
import org.service.TeacherService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
//    @Override
//    public Integer countTeacher() {
//        return 0;
//    }
//
//    @Override
//    public List<teacher> queryTeacherInfo() {
//        return List.of();
//    }

    @Override
    public List<teacher> queryAllTeacher() {
        return null;
    }

    @Override
    public boolean addTeacher(teacher teacher) {
        return false;
    }

    @Override
    public boolean deleteTeacher(int id) {
        return false;
    }
}
