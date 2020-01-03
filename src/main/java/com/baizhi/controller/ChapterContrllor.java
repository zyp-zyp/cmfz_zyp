package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.apache.commons.io.FileUtils;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("chapter")
public class ChapterContrllor {

    @Resource
    private ChapterService chapterService;

    //分页查
    @RequestMapping("queryBypage")
    @ResponseBody
    public Map<String,Object> queryBypage(Integer rows, Integer page,String albumId){
        /*
         * rows:每页展示条数
         * page：当前页
         *
         * */
        Map<String, Object> map = chapterService.selectBypage(rows, page, albumId);
        return map;
    }

    //增删改
    @RequestMapping("edit")
    @ResponseBody
    public Map<String,Object> edit(String oper, Chapter chapter, String[] id){
        //添加
        if("add".equals(oper)){
            String id1 = UUID.randomUUID().toString();
            chapter.setId(id1);
            chapterService.insert(chapter);
            Map<String, Object> map = new HashMap<>();
            map.put("chapterId",id1);
            map.put("msg","添加成功");
            return map;
        }
        //修改
        if("edit".equals(oper)){
            Map<String, Object> map = new HashMap<>();
            if("".equals(chapter.getSrc())){
                chapter.setSrc(null);
                chapterService.update(chapter);
                map.put("chapterId",null);
                map.put("msg","修改成功");
            }else{
                chapterService.update(chapter);
                map.put("msg","修改成功");
                map.put("chapterId",chapter.getId());
            }

            return map;
        }
        //删除
        if("del".equals(oper)){
            chapterService.delete(id);
            Map<String, Object> map = new HashMap<>();
            map.put("chapterId",chapter.getId());
            map.put("msg","删除成功");
            return map;
        }
        return null;
    }





    /*
    * 上传
    * */
    @ResponseBody
    @RequestMapping("chapterUpload")
    public void bannerUpload(MultipartFile src, String chapterId,String albumId, HttpSession session){
        if(!chapterId.equals("")){
            //1. 获得 upload的路径
            String realPath = session.getServletContext().getRealPath("/admin/MP3");
            //2. 判断文件夹是否存在
            File file1 = new File(realPath);
            if(!file1.exists()){
                file1.mkdirs();
            }
            //3.获取文件真实名字
            String originalFilename = src.getOriginalFilename();

            //4. 为了防止同一个文件多次上传发生覆盖  拼接时间戳
            String name = new Date().getTime()+"_"+originalFilename;

            //5.文件上传
            try {
                src.transferTo(new File(realPath,name));
            } catch (IOException e) {
                e.printStackTrace();
            }

            //获得时长
            try {
                AudioFile read = AudioFileIO.read(new File(realPath, name));
                AudioHeader audioHeader = read.getAudioHeader();
                int trackLength = audioHeader.getTrackLength();
                String second = trackLength % 60 + "秒";
                String minute = trackLength / 60 + "分";
                //大小
                String size = src.getSize() / 1024 / 1024 + "MB";
                //set进去
                Chapter chapter = new Chapter();
                chapter.setSize(size);
                chapter.setDuration(minute+second);
                chapter.setAlbum_Id(albumId);
                chapter.setId(chapterId);
                System.out.println(name);
                chapter.setSrc(name);
                chapterService.update(chapter);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //文件下载
    @ResponseBody
    @RequestMapping("download")
    public void download(String fileName, HttpSession session, HttpServletResponse response) {
        //获取路径
        String Path = session.getServletContext().getRealPath("/admin/MP3");
        File file = new File(Path, fileName);
       //分割
        String name = fileName.split("_")[1];
        //附件形式下载  设置响应头
        try {
            String encode = URLEncoder.encode(name, "utf-8");
            response.setHeader("content-disposition", "attachment;filename=" + encode);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //new 一个输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //FileUtils是commons的包
            FileUtils.copyFile(file, outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
