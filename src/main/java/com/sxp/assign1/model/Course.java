package com.sxp.assign1.model;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Course {

    private Integer cid;
    private String courseName;
    private String address;
    private String teacher;
    private List<Map<String, Integer>> time;
}
