package com.baizhi.dao;

import com.baizhi.entity.AddressDto;
import com.baizhi.entity.User;

import java.util.List;

public interface UserDao {
    //增加
    public void insert(User user);
    //用户名查
    public User selectByPhone(String phone_number);
    //查询地址分布
    public List<AddressDto> selectAddress();
}
