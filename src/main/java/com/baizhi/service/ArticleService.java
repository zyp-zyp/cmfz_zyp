package com.baizhi.service;

import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;

import java.util.List;
import java.util.Map;

public interface ArticleService {
    //分页查
    public Map<String,Object> selectBypage(Integer rows, Integer page);
    //添加
    public void insert(Article article);
    //修改
    public void update(Article article);
    //删除
    public void delete(String[] delete);
    //id查
    public Article selectById(String id);
    //查询所有
    public List<Article> select();
}
