package com.baizhi.dao;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //查所有
    public List<Album> select();
    //分页查
    public List<Album> selectByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    //查询总条数
    public int selectCunt();
    //增删改
    public void insert(Album album);
    public void update(Album album);
    public void delete(String[] id);
    //id查
    public Album selectById(String id);

}
