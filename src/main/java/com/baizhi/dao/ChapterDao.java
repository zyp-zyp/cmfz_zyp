package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;
import java.util.List;

public interface ChapterDao {
    //分页查
    public List<Chapter> selectByPage(@Param("start") Integer start, @Param("rows") Integer rows,@Param("album_Id")String album_Id);
    //查询总条数
    public int selectCunt();
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
