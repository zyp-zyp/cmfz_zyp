package com.baizhi.service.impl;

import com.baizhi.dao.AdminDao;
import com.baizhi.dao.AlbumDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;

@Service
@Transactional(propagation = Propagation.SUPPORTS)
public class AdminServiceImpl implements AdminService {
    @Resource
    private AdminDao adminDao;

    @Override
    public String login(Admin admin, HttpSession session) {
        Admin admin1 = adminDao.selectByName(admin.getUsername());
        System.out.println("=============admin1"+admin1);
        if(admin1==null){
            return "用户名不存在";
        }else if(!admin1.getPassword().equals(admin.getPassword())){
            return "密码错误";
        }else {
            session.setAttribute("login",admin1);
        }
        return null;
    }

    @Override
    public List<Admin> select() {
        List<Admin> select = adminDao.select();
        return select;
    }
}
