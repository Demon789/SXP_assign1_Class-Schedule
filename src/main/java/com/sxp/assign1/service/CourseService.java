package com.sxp.assign1.service;

import com.sxp.assign1.mapper.CourseMapper;
import com.sxp.assign1.model.Course;
import com.sxp.assign1.model.CourseDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    @Autowired
    CourseMapper courseMapper;

    public List<Course> getAllCourse() {
        return courseMapper.getAllCourse();
    }

    public List<Course> getCourseByUid(Integer uid) {
        return courseMapper.getCourseByUid(uid);
    }

    public Course getCourseByCid(Integer cid) {
        return courseMapper.getCourseByCid(cid);
    }
    public CourseDetail getCourseDetailByCid(Integer cid){
        return courseMapper.getCourseDetailByCid(cid);
    }
}
