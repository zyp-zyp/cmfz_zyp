package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    //查询所有
    public List<Album> select();
    //分页查
    public Map<String,Object> selectBypage(Integer rows, Integer page);
    //增删改
    public void insert(Album album);
    public void update(Album album);
    public void delete(String[] id);
    //id查
    public Album selectById(String id);
}
