package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.BannerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import sun.java2d.pipe.SpanIterator;

import javax.annotation.Resource;
import javax.crypto.MacSpi;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("album")
public class AlbumContrllor {

    @Resource
    private AlbumService albumService;

    //分页查
    @RequestMapping("queryBypage")
    @ResponseBody
    public Map<String,Object> queryBypage(Integer rows, Integer page){
        /*
         * rows:每页展示条数
         * page：当前页
         *
         * */
        Map<String, Object> map = albumService.selectBypage(rows, page);
        return map;
    }

    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(String oper, Album album,String[] id){
        //添加
        if("add".equals(oper)){
            String id1 = UUID.randomUUID().toString();
            album.setId(id1);
            albumService.insert(album);
            Map<String, Object> map = new HashMap<>();
            map.put("albumId",id1);
            map.put("msg","添加成功");
            return map;
        }
        if("edit".equals(oper)){
            Map<String, Object> map = new HashMap<>();
            if("".equals(album.getImg())){
                album.setImg(null);
                albumService.update(album);
                map.put("albumId",null);
                map.put("msg","修改成功");
            }else {
                albumService.update(album);
                map.put("albumId",album.getId());
                map.put("msg","修改成功");
            }
            return map;
        }
        if("del".equals(oper)){
            albumService.delete(id);
            Map<String, Object> map = new HashMap<>();
            map.put("albumId",album.getId());
            map.put("msg","删除成功");
            return map;
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("albumUpload")
    public void albumUpload(MultipartFile img, String albumId,HttpSession session){
        if(!"".equals(albumId)){
            //1. 获得 upload的路径
            String realPath = session.getServletContext().getRealPath("/admin/img");
            //2. 判断文件夹是否存在
            File file1 = new File(realPath);
            if(!file1.exists()){
                file1.mkdirs();
            }
            //3.获取文件真实名字
            String originalFilename = img.getOriginalFilename();

            //4. 为了防止同一个文件多次上传发生覆盖  拼接时间戳
            String name = new Date().getTime()+"_"+originalFilename;

            //5.文件上传
            try {
                img.transferTo(new File(realPath,name));
            } catch (IOException e) {
                e.printStackTrace();
            }
            Album album = new Album();
            album.setId(albumId);
            album.setImg(name);
            albumService.update(album);
        }
    }
}
