package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface UserService {
    //注册
    public Map<String,String> insert(User user);
    //登录
    public Map<String,String> login(User user, HttpSession session);
    //查询地图分布
    public List<Map<String,Object>> selectAddress();


}
