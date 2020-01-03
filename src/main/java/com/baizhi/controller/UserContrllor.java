package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("account")
public class UserContrllor {
    @Resource
    private UserService userService;
    /*
    * 注册
    * */
    @ResponseBody
    @RequestMapping("regist")
    public Map<String,String> regist(User user){
        Map<String, String> map = userService.insert(user);
        return map;
    }

    /*
    * 登录
    * */
    @ResponseBody
    @RequestMapping("login")
    public Map<String,String> login(User user, String code, HttpSession session){
        HashMap<String, String> map = new HashMap<String, String>();
        String code1 = (String) session.getAttribute("code");
        if(!code1.equals(code)){
            map.put("error","-200");
            map.put("errmsg","验证码输入错误");
            return map;
        }else {
            Map<String, String> login = userService.login(user, session);
            return login;
        }
    }


    /*
    *  地址分布
    * */
    @ResponseBody
    @RequestMapping("selectAddress")
    public List<Map<String,Object>> selectAddress(){
        List<Map<String,Object>> strings = userService.selectAddress();
        return strings;
    }
}
