package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("admin")
public class AdminContrllor {

    @Resource
    private AdminService adminService;

    /*登录*/
    @RequestMapping("login")
    @ResponseBody
    public String login(Admin admin,String code, HttpSession session){
        String code1 =(String) session.getAttribute("code");
        if(!code1.equals(code)){
            return "验证码输入错误";
        }
        String login = adminService.login(admin, session);
        return login;
    }


}
