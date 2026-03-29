package org.entity.DTO;

import lombok.Data;

@Data
public class QueryStudentDTO {
    private String id;
//    private String name;
    private String academicYear;
    private String semester;
    private String today;


    public String toString(){
        return "id:"+id+" semester:"+semester+" today:"+today+" academicyear:"+academicYear;
    }
}
