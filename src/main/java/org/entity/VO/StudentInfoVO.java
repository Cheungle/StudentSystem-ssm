package org.entity.VO;

import lombok.Data;
import org.entity.student;

import java.util.HashMap;
import java.util.Map;

// 页面导航栏用户信息
// 学生
@Data
public class StudentInfoVO {
    private String userName;
    private String userPhoto;
    private String userMajor;
    private int userId;

    public Map<String,String> toMap(){
        Map<String,String> map = new HashMap<>();
        map.put("userName",userName);
        map.put("userPhoto",userPhoto);
        map.put("userMajor",userMajor);
        map.put("userId",String.valueOf(userId));
        return map;
    }
    public StudentInfoVO(){
    }
    public StudentInfoVO(Map<Object,Object> map){
        userName = (String)map.get("userName");
        userPhoto = (String)map.get("userPhoto");
        userMajor = (String)map.get("userMajor");
    }
    public StudentInfoVO(student student){
        userName = student.getNamestudent();
        userPhoto = student.getPhoto();
        userMajor = student.getMajor();
    }
}
