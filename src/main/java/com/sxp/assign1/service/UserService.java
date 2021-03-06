package com.sxp.assign1.service;

import com.sxp.assign1.mapper.UserMapper;
import com.sxp.assign1.model.User;
import com.sxp.assign1.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public Integer checkUserLogin(UserVO userVO) {
        User user = userMapper.getUserByUsername(userVO.getUsername());
        if (user != null && user.getPassword().equals(userVO.getPassword())) return user.getUid();
        return -1;
    }

    public boolean userInsert(User user) {
        if (userMapper.getUserByUsername(user.getUsername()) == null)
            return userMapper.insertUser(user);
        else return false;
    }
}
