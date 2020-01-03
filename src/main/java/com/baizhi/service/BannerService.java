package com.baizhi.service;

import com.baizhi.entity.Banner;
import com.baizhi.entity.Month12Dto;

import java.util.List;
import java.util.Map;

public interface BannerService {
    //分页查
    public Map<String, Object> selectBypage(Integer rows, Integer page);

    //查所有
    public List<Banner> select();

    //添加轮播图
    public void insert(Banner banner);

    //修改
    public void update(Banner banner);

    //删除
    public void delete(String[] delete);

    //最近七天
    public int[] selectDay7();

    //12个月
    public int[] selectMonth12();
}