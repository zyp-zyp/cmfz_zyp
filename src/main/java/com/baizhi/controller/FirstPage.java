package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FirstPage {
    @Resource
    private BannerService bannerService;
    @Resource
    private AlbumService albumService;
    @Resource
    private ArticleService articleService;
    @ResponseBody
    @RequestMapping("first_page")
    public ArrayList<Map<String, List<Map<String, Object>>>> first_page(String uid,String type,String sub_type){

        //轮播图
        List<Map<String, Object>> list=null;
        if("all".equals(type)){
            list = new ArrayList<Map<String, Object>>();
            List<Banner> select = bannerService.select();
            for (Banner banner : select) {
                Map<String, Object> map = new HashMap<>();
                map.put("thumbnail","http://localhost:9001/cmfz_zyp/admin/img/"+banner.getImg());
                map.put("desc",banner.getTitle());
                map.put("id",banner.getId());
                list.add(map);
            }
        }

        //列表 闻 专辑
        List<Map<String, Object>> list1 = new ArrayList<>();
        List<Album> select1 = albumService.select();
        for (Album album : select1) {
            Map<String, Object> map = new HashMap<>();
            map.put("thumbnail","http://localhost:9001/cmfz_zyp/admin/img/"+album.getImg());
            map.put("title",album.getTitle());
            map.put("author",album.getAuthor());
            map.put("type","0");
            map.put("set_count",album.getCount());
            map.put("create_date",album.getCreate_date());
            list1.add(map);
        }
        //列表 思 文章
        List<Map<String, Object>> list2 = new ArrayList<>();
        List<Article> select2 = articleService.select();
        for (Article article : select2) {
            Map<String, Object> map = new HashMap<>();
            map.put("thumbnail","http://localhost:9001/cmfz_zyp/admin/img/");
            map.put("title",article.getTitle());
            map.put("author",article.getAuthor());
            map.put("type","1");
            map.put("set_count","1");
            map.put("create_date",article.getCreate_date());
            list2.add(map);
        }

        //最后返回集合
        ArrayList<Map<String, List<Map<String, Object>>>> maps = new ArrayList<>();
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        map.put("header",list);
        map.put("album",list1);
        map.put("artical",list2);
        return maps;
    }
}
