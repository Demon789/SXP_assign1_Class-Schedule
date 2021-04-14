package com.sxp.assign1.mapper;

import com.sxp.assign1.model.Course;
import com.sxp.assign1.model.CourseDetail;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CourseMapper {
    @Select("select * from course where cid=#{cid}")
    @Results(id = "getTime", value = {
            @Result(id = true, column = "cid", property = "cid"),
            @Result(column = "cid", property = "time", many = @Many(select = "com.sxp.assign1.mapper.CourseMapper.getCourseTimeByCid"))
    })
    public Course getCourseByCid(@Param("cid") Integer cid);

    @Select("select course_num, weekday from course_time where cid=#{cid}")
    public List<Map<String, String>> getCourseTimeByCid(@Param("cid") Integer cid);

    @Select("SELECT course.cid cid, course.course_name course_name, course.address address FROM course NATURAL JOIN user_course WHERE uid=#{uid}")
    @ResultMap("getTime")
    public List<Course> getCourseByUid(@Param("uid") Integer uid);

    @Select("select * from course")
    @ResultMap("getTime")
    public List<Course> getAllCourse();

    @Select("select * from course_detail where cid=#{cid}")
    @Results({
            @Result(column = "course_num",property = "courseNum")
    })
    public CourseDetail getCourseDetailByCid(@Param("cid") Integer cid);
}
