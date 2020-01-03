package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    Admin selectByName(String username);
    //查所有
    List<Admin> select();
}
