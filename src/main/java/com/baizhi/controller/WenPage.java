package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import com.baizhi.service.ChapterService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("detail")
public class WenPage {
    @Resource
    private AlbumService albumService;
    @Resource
    private ChapterService chapterService;
    @RequestMapping("wenPage")
    public ArrayList<Map<String,Object>> wenPage(String uid,String id){
        //详情简介
        HashMap<String, Object> map = new HashMap<>();
        Album album = albumService.selectById(id);
        map.put("thumbnail",album.getImg());
        map.put("title",album.getTitle());
        map.put("score",album.getScore());
        map.put("author",album.getAuthor());
        map.put("broadcast",album.getBroadcaster());
        map.put("set_count",album.getCount());
        map.put("brief",album.getBrief());
        map.put("create_date",album.getCreate_date());

        //章节列表
        ArrayList<Map<String, String>> list1 = new ArrayList<>();
        List<Chapter> chapters = chapterService.selectByalbumId(id);
        for (Chapter chapter : chapters) {
            HashMap<String, String> map1 = new HashMap<>();
            map1.put("title",chapter.getTitle());
            map1.put("download_url",chapter.getSrc());
            map1.put("size",chapter.getSize());
            map1.put("duration",chapter.getDuration());
            list1.add(map1);
        }
        //将详情和章节列表装近map集合
        HashMap<String, Object> map2 = new HashMap<>();
        map.put("introduction",map);
        map.put("list",list1);
        //最终返回集合
        ArrayList<Map<String,Object>> list = new ArrayList<>();
         list.add(map2);
         return list;
    }
}
