package com.sxp.assign1.model;


import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class Course {

    private int cid;
    private List<Map<Integer, Integer>> time;
}
