package com.sxp.assign1.service;

import com.sxp.assign1.mapper.UserMapper;
import com.sxp.assign1.model.ResponseData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Service
public class SessionInterceptor implements HandlerInterceptor {
    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        Integer uid = (Integer) session.getAttribute("uid");
        if (userMapper.getUserById(uid) != null) return true;
        ResponseData responseData=new ResponseData();
        responseData.setCode(1);
        responseData.setMsg("请登录");
        response.setCharacterEncoding("utf-8");
        response.getWriter().print(responseData);
        return false;
    }
}
