package com.sxp.assign1.mapper;

import com.sxp.assign1.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from user where uid=#{uid}")
    public User getUserById(@Param("uid") Integer uid);

    @Select("select * from user")
    public List<User> getAllUser();

    @Select("select * from user where username=#{username}")
    public User getUserByUsername(@Param("username") String username);
}
