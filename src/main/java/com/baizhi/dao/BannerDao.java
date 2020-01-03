package com.baizhi.dao;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Month12Dto;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

public interface BannerDao {
    //分页查
    public List<Banner> selectByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    //查询总条数
    public int selectCunt();
    //添加轮播图
    public void insert(Banner banner);
    //修改
    public void update(Banner banner);
    //删除
    public void delete(String[] id);
    //查询
    public Set<Banner> selectAll();
    //查询所有
    public List<Banner> select();
    //最近七天
    public int[] selectDay7();
    //12个月
    public Month12Dto selectMonth12();
}
