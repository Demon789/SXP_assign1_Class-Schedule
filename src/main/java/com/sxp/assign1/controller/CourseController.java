package com.sxp.assign1.controller;

import com.sxp.assign1.model.Course;
import com.sxp.assign1.model.ResponseData;
import com.sxp.assign1.service.CourseService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseService courseService;

    @PostMapping("/getAllCourse")
    public ResponseData getAllCourse() {
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setData(courseService.getAllCourse());
        System.out.println(responseData);
        return responseData;
    }

    @PostMapping("/getCourseByUid")
    public ResponseData getCourseByUid(@RequestBody JSONObject src) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        Integer uid = src.getInt("uid");
        responseData.setData(courseService.getCourseByUid(uid));
        return responseData;
    }

    @PostMapping("/getCourseByCid")
    public ResponseData getCourseByCid(@RequestBody JSONObject src) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        Integer cid = src.getInt("cid");
        responseData.setData(courseService.getCourseByCid(cid));
        return responseData;
    }

    @PostMapping("/getCourseDetailByCid")
    public ResponseData getCourseDetailByCid(@RequestBody JSONObject src) {
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        Integer cid = src.getInt("cid");
        responseData.setData(courseService.getCourseDetailByCid(cid));
        return responseData;
    }

    @PostMapping("/getMyCourse")
    public ResponseData getMyCourse(HttpSession session) {
        ResponseData responseData = new ResponseData();
        Integer uid = (Integer) session.getAttribute("cid");
        if (uid == null) {
            responseData.setCode(1);
            responseData.setMsg("请登录");
            return responseData;
        }
        responseData.setCode(0);
        responseData.setData(courseService.getCourseByUid(uid));
        return responseData;
    }
}
