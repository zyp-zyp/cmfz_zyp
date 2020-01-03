package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.AddressDto;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Resource
    private UserDao userDao;

    /*
    * 注册
    * */
    @Override
    public Map<String,String> insert(User user) {
        HashMap<String, String> map = new HashMap<>();
        User user1 = userDao.selectByPhone(user.getPhone_number());
        if(user1==null){
            map.put("errno","-200");
            map.put("error_msg","该手机号已经存在");
            return map;
        }else {
            String id = UUID.randomUUID().toString().replace("-", "");
            String md5 = UUID.randomUUID().toString().replace("-", "");
            user.setId(id);
            Date date = new Date();
            user.setCreate_date(date);
            user.setStatus("激活");
            //盐值
            user.setSalt(md5);
            //md5加密
            String password = DigestUtils.md5Hex(user.getPassword()+md5);
            user.setPassword(password);
            userDao.insert(user);
            map.put("password",password);
            map.put("uid",id);
            map.put("phone",user.getPhone_number());
            return map;
        }
    }

    /*
    * 登录
    * */
    @Override
    public Map<String, String> login(User user, HttpSession session) {
        HashMap<String, String> map = new HashMap<>();
        User user1 = userDao.selectByPhone(user.getPhone_number());
        if(user1==null){
            map.put("error","-200");
            map.put("errmsg","用户名输入错误");
            return map;
        }
        String password = DigestUtils.md5Hex(user.getPassword() + user1.getSalt());
        if(!user1.getPassword().equals(password)){
            map.put("error","-200");
            map.put("errmsg","密码输入错误");
            return map;
        }
        map.put("password",password);
        map.put("farmington",user1.getDharma());
        map.put("password",user1.getId());
        map.put("gender",user1.getSex());
        map.put("photo",user1.getHead_img());
        map.put("location",user1.getAddress());
        map.put("description",user1.getSign());
        map.put("phone ",user1.getPhone_number());
        session.setAttribute("user",user1);
        return map;
    }

    /*
    * 地址分布
    * */
    @Override
    public List<Map<String,Object>> selectAddress() {
        List<Map<String,Object>> list11 = new ArrayList<>();
        List<AddressDto> addressDtos = userDao.selectAddress();
        for (AddressDto addressDto : addressDtos) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("name",addressDto.getAddress());
            map.put("value",addressDto.getCount());
            list11.add(map);
        }
        return list11;
    }
}
