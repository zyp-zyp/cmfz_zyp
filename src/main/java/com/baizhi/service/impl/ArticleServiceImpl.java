package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Resource
    private ArticleDao articleDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> selectBypage(Integer rows, Integer page) {
        /*
         * jqGrid要求必须返回：
         * page：当前页
         * rows:数据
         * total:总页数
         * records：总条数
         *
         * */

        /*
         * 计算起始
         *   rows:每页展示条数
         *   page：当前页
         *
         * */

        //开始条数
        Integer start = (page - 1) * rows;

        //返回的数据
        List<Article> articles = articleDao.selectByPage(start,rows);

        //数据条数
        Integer count = articleDao.selectCunt();

        //计算总页数
        Integer totalpage = count%rows == 0 ? count/rows:count/rows+1;
        HashMap<String, Object> map = new HashMap<>();
        map.put("page",page);
        map.put("rows",articles);
        map.put("total",totalpage);
        map.put("records",count);
        return map;
    }


    @Override
    public void insert(Article article) {
        articleDao.insert(article);
    }

    @Override
    public void update(Article article) {
        articleDao.update(article);
    }

    @Override
    public void delete(String[] delete) {
        articleDao.delete(delete);
    }

    @Override
    public Article selectById(String id) {
        Article article = articleDao.selectById(id);
        return article;
    }

    /*查所有*/
    @Override
    public List<Article> select() {
        List<Article> select = articleDao.select();
        return select;
    }
}
