package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface AdminService {
    public String login(Admin admin, HttpSession session);
    public List<Admin> select();
}
