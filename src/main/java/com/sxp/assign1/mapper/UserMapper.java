package com.sxp.assign1.mapper;

import com.sxp.assign1.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where uid=#{uid}")
    public User getUserById(Integer uid);
}
