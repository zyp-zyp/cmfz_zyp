package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import com.baizhi.entity.Month12Dto;
import com.baizhi.service.AdminService;
import com.baizhi.service.BannerService;
import org.apache.catalina.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("banner")
public class BannerContrllor {

    @Resource
    private BannerService bannerService;

    //分页查
    @RequestMapping("queryBypage")
    @ResponseBody
    public Map<String,Object> queryBypage(Integer rows, Integer page){
        /*
         * rows:每页展示条数
         * page：当前页
         *
         * */
        Map<String, Object> map = bannerService.selectBypage(rows, page);
        return map;
    }

    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(String oper, Banner banner,String[] id){
        //添加
        if("add".equals(oper)){
            String id1 = UUID.randomUUID().toString();
            banner.setId(id1);
            bannerService.insert(banner);
            Map<String, Object> map = new HashMap<>();
            map.put("bannerId",id1);
            return map;
        }
        if("edit".equals(oper)){
            bannerService.update(banner);
            Map<String, Object> map = new HashMap<>();
            map.put("bannerId",banner.getId());
            return map;
        }
        if("del".equals(oper)){
            bannerService.delete(id);
        }
        return null;
    }
    @ResponseBody
    @RequestMapping("bannerUpload")
    public void bannerUpload(MultipartFile img, String bannerId,HttpSession session){
        if(img!=null){
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
            Banner banner = new Banner();
            banner.setId(bannerId);
            banner.setImg(name);
            bannerService.update(banner);
        }
    }

    @ResponseBody
    @RequestMapping("selectDay7")
    public int[] selectDay7(){
        int[] ints = bannerService.selectDay7();
        return ints;
    }
    @ResponseBody
    @RequestMapping("selectMonth12")
    public int[] selectMonth12(){
        int[] ints = bannerService.selectMonth12();
        return ints;
    }
}
