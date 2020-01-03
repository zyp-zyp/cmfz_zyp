package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("article")
public class ArticleContrllor {

    @Resource
    private ArticleService articleService;

    //分页查
    @RequestMapping("queryBypage")
    @ResponseBody
    public Map<String,Object> queryBypage(Integer rows, Integer page){
        /*
         * rows:每页展示条数
         * page：当前页
         *
         * */
        Map<String, Object> map = articleService.selectBypage(rows, page);
        return map;
    }

    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(String oper, Article article, String[] id){
        //添加
        if("add".equals(oper)){
            String id1 = UUID.randomUUID().toString();
            article.setId(id1);
            Date date = new Date();
            article.setCreate_date(date);
            article.setAuthor("作者");
            articleService.insert(article);
            Map<String, Object> map = new HashMap<>();
            map.put("articleId",id1);
            return map;
        }
        if("edit".equals(oper)){
            System.out.println(article);
            if(article.getContent().equals("")){
                article.setContent(null);
            }
            articleService.update(article);
            Map<String, Object> map = new HashMap<>();
            map.put("articleId",article.getId());
            return map;
        }
        if("del".equals(oper)){
            articleService.delete(id);
        }
        return null;
    }


    //查看详情
    @RequestMapping("details")
    @ResponseBody
    public String details(String id){
        Article s = articleService.selectById(id);
        return s.getContent();
    }

    //修改content回显
    //查看详情
    @RequestMapping("update")
    @ResponseBody
    public Article update(String id){
        Article s = articleService.selectById(id);
        return s;
    }


    /*
     * 上传
     * */
    @ResponseBody
    @RequestMapping("articleUpload")
    public Map<String, Object> articleUpload(MultipartFile img, HttpSession session, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();

        //1. 获得 upload的路径
        String realPath = session.getServletContext().getRealPath("/admin/img");
        //2. 判断文件夹是否存在
        File file1 = new File(realPath);
        if (!file1.exists()) {
            file1.mkdirs();
        }
        //3.获取文件真实名字
        String originalFilename = img.getOriginalFilename();

        //4. 为了防止同一个文件多次上传发生覆盖  拼接时间戳
        String name = new Date().getTime() + "_" + originalFilename;

        //5.文件上传
        try {
            img.transferTo(new File(realPath, name));

            /*
             *
             * {"error":0,"url":"\/ke4\/attached\/W020091124524510014093.jpg"}
             * {"error":0, "url":"http://localhost:80/cmfz/upload/img/xxx.png" }
             * */
            map.put("error",0);
            String scheme = request.getScheme();   //hhtp协议
            //PC-20190718ZLAM/192.168.1.156
            InetAddress localHost = InetAddress.getLocalHost();  //localhost
            //切割
            String host = localHost.toString().split("/")[1];  //切割后的localhost  ip地址
            int port = request.getServerPort();     //post  端口号
            ServletContext servletContext = request.getServletContext();  //项目名
            //拼接返回值
            String url=scheme+"://"+host+"/"+port+servletContext+"/admin/img/"+name;
            map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }


     /*
    *
    * 要求的返回的参数
    *
    * {
    "moveup_dir_path": "",
    "current_dir_path": "",
    "current_url": "/ke4/php/../attached/",
    "total_count": 5,
    "file_list": [
        {
            "is_dir": false,
            "has_file": false,
            "filesize": 208736,
            "dir_path": "",
            "is_photo": true,
            "filetype": "jpg",
            "filename": "1241601537255682809.jpg",
            "datetime": "2018-06-06 00:36:39"
        }
     ]
}
    * */
     @ResponseBody
    @RequestMapping("getAllImgs")
    public  Map<String, Object> getAllImgs(HttpSession session, HttpServletRequest request){
        //大的map集合
        Map<String, Object> map = new HashMap<>();
        //那到项目中的所有图片
        String realPath = session.getServletContext().getRealPath("/admin/img");
        File file = new File(realPath);
        String[] imgs = file.list();

        //大集合中的值是有list集合
        ArrayList<Object> list = new ArrayList<>();
        //遍历所有的图片
        for (String img : imgs) {
            //list集合中的小map集合
            Map<String, Object> hashMap = new HashMap<>();
            hashMap.put("is_dir", false);
            hashMap.put("has_file", false);
            //大小
            File file1 = new File(realPath, img);
            long length = file1.length();
            hashMap.put("filesize", length);
            hashMap.put("dir_path","");
            hashMap.put("is_photo",true);
            //返回 资源名的  后缀
            String extension = FilenameUtils.getExtension(img);
            hashMap.put("filetype",extension);
            hashMap.put("filename",img);
            //上传时间
            String s = img.split("_")[0];
            Long aLong = Long.valueOf(s);
            Date date = new Date(aLong);
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = simpleDateFormat.format(date);
            hashMap.put("datetime",format);
            //将小map放到list集合
            list.add(hashMap);
        }

        map.put("file_list",list);
        map.put("moveup_dir_path","");
        map.put("current_dir_path","");

        try {
            //协议
            String scheme = request.getScheme();
            //localhost
            InetAddress localHost = InetAddress.getLocalHost();
            String s = localHost.toString().split("/")[1];
            //端口号
            int serverPort = request.getServerPort();
            //项目名
            String contextPath = request.getContextPath();
            //拼接
            String current_url = scheme+"://"+s+":"+serverPort+contextPath+"/admin/img/";
            map.put("current_url",current_url);
            map.put("total_count",imgs.length);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }

}
