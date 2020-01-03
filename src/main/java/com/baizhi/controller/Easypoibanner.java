package com.baizhi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@RequestMapping("easypoibanner")
@Controller
public class Easypoibanner {
    @Resource
    private BannerService bannerService;
    @RequestMapping("outEasypoi")
    @ResponseBody
    public void outEasypoi(HttpServletResponse response){
        List<Banner> select = bannerService.select();
        for (Banner banner : select) {
            /*F:\后期项目\last_project\cmfz-180\src\main\webapp\img\*/
            banner.setImg("E:\\feiqiu\\SPRING_BOOT\\cmfz_zyp\\src\\main\\webapp\\admin\\img\\"+banner.getImg());
        }
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("持名法州中的轮播图","轮播图"),Banner.class, select);
        try {
            //设置下载格式
            response.setHeader("content-disposition","attachment;filename=Banner.xls");
            ServletOutputStream outputStream = response.getOutputStream();
            workbook.write(outputStream);
            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
