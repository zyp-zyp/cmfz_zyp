package com.baizhi.dao;

import com.baizhi.entity.Article;
import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    //分页查
    public List<Article> selectByPage(@Param("start") Integer start, @Param("rows") Integer rows);
    //查询总条数
    public int selectCunt();
    //添加
    public void insert(Article article);
    //修改
    public void update(Article article);
    //id查
    public Article selectById(String id);
    //删除
    public void delete(String[] id);
    //查询所有
    public List<Article> select();
}
