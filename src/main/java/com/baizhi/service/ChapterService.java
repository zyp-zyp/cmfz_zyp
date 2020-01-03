package com.baizhi.service;


import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    //分页查
    public Map<String,Object> selectBypage(Integer rows, Integer page ,String album_id);
    //添加
    public void insert(Chapter chapter);
    //修改
    public void update(Chapter chapter);
    //id查
    public Chapter selectById(String id);
    //删除
    public void delete(String[] id);
    //通过专辑id查
    public List<Chapter> selectByalbumId(String id);
}
