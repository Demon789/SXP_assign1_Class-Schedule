package com.sxp.assign1.mapper;

import com.sxp.assign1.model.Course;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface CourseMapper {
    @Select("select * from course where cid=#{id}")
    @Results({
            @Result(id = true, column = "cid", property = "cid"),
            @Result(column = "cid", property = "time", many = @Many(select = "com.sxp.assign1.mapper.CourseMapper.getCourseTimeById"))
    })
    public Course getCourseById(@Param("id") Integer id);

    @Select("select course_num,weekday from course_time where cid=#{id}")
    public List<Map<String, String>> getCourseTimeById(@Param("id") Integer id);
}
