package com.sxp.assign1.controller;

import com.sxp.assign1.mapper.UserMapper;
import com.sxp.assign1.model.ResponseData;
import com.sxp.assign1.model.User;
import com.sxp.assign1.model.UserVO;
import com.sxp.assign1.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/test")
    public ResponseData test() {
        ResponseData responseData = new ResponseData();
        responseData.setCode(0);
        responseData.setData("test");
        responseData.setMsg("test2");
        return responseData;
    }

    @PostMapping("/login")
    public ResponseData login(@RequestBody  UserVO userVO, HttpSession session) {
        ResponseData responseData = new ResponseData();
        Integer uid = userService.checkUserLogin(userVO);
        if (uid != -1) {
            session.setAttribute("uid", uid);
            responseData.setCode(0);
            responseData.setMsg("登录成功");
        } else {
            responseData.setCode(1);
            responseData.setMsg("登录失败");
        }
        return responseData;
    }
}
