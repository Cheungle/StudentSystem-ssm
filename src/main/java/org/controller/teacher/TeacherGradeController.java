package org.controller.teacher;

import org.service.ScService;
import org.entity.courseChoose;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherGradeController {
    @Autowired
    public ScService scService;

    @RequestMapping("/getGradeOfClass")
    public List<courseChoose> getGradeOfClass(int id){
    	return scService.getGradeByCourseID(id);
    }

}
