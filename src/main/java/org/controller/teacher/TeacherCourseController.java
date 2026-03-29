package org.controller.teacher;

import org.entity.coursePlan;
import org.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherCourseController {

    @Autowired
    public CoursePlanService coursePlanService;

    @RequestMapping("/getCourseByTeacherID")
    public List<coursePlan> getCourseByTeacherID(int id){
        return coursePlanService.queryCourseByTeacherId(id);
    }
}
