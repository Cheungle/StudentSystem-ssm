package org.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.dao.StudentDao;
import org.entity.student;
import org.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    @Autowired
    public StudentDao studentDao;

    @Override
    public boolean addStudent(student  student) {
        return false;
    }

    @Override
    public boolean deleteStudent(int id) {
        return false;
    }

    @Override
    public List<student> queryAllStudent() {
        return null;
    }

    @Override
    public student queryStudentById(int id) {
    return studentDao.queryStudentById(id);
    }
}
