package com.baizhi.controller;

import com.baizhi.util.ValidateImageCodeUtils;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;

@RestController
@RequestMapping("code")
public class ImageCodeController {

    @RequestMapping("getCode")
    public void getCode(HttpSession session,HttpServletResponse response){
        // 1.回执验证码中的字符
        String code = ValidateImageCodeUtils.getSecurityCode();
        session.setAttribute("code",code);
        // 2. 通过生成的字符回执图片
        BufferedImage image = ValidateImageCodeUtils.createImage(code);

        // 3.通过图片的输出流,写到页面
        //     参数1 图片  参数2 格式 参数3 输出流
        ServletOutputStream outputStream = null;
        try {
            outputStream  = response.getOutputStream();
            ImageIO.write(image,"png",outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(outputStream!=null){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
