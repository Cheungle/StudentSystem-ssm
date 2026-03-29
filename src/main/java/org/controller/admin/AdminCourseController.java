package org.controller.admin;

import org.entity.coursePlan;
import org.service.CoursePlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminCourseController {

    @Autowired
    public CoursePlanService coursePlanService;


    @RequestMapping("/countCourse")
    public Integer countCourse(){
        return coursePlanService.countCourse();
    }

    @RequestMapping("/addCourse")
    public boolean addCourse(coursePlan course){
    	return coursePlanService.addCourse(course);
    }

    @RequestMapping("/deleteCourse")
    public boolean deleteCourse(int courseId){
    	return coursePlanService.deleteCourse(courseId);
    }

    @RequestMapping("/updateCourse")
    public boolean updateCourse(coursePlan course){
    	return coursePlanService.updateCourse(course);
    }

}
