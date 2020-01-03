package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.RowIdLifetime;
import java.util.List;

@Controller
@RequestMapping("poiAdmin")
public class PoiAdmin {
    @Resource
    private AdminService adminService;

    /*
    * 导出
    * */
    @RequestMapping("outPoi")
    @ResponseBody
    public void outPoi(HttpServletResponse response){

        //创建文件
        HSSFWorkbook sheets = new HSSFWorkbook();
        //创建一个字体对象
        HSSFFont font = sheets.createFont();
            //设置字体大小
        font.setFontHeightInPoints((short) 10);
            //设置字体
        font.setFontName("微软雅黑");
            //加粗
        font.setBold(true);
        //创建一个样式
        HSSFCellStyle cellStyle  = sheets.createCellStyle();
        cellStyle.setFont(font);
        //创建工作薄
        HSSFSheet sheet = sheets.createSheet();
        //创建行
        HSSFRow row = sheet.createRow(0);
        //自定义标题行
        String[] titles = {"编号","用户名","密码","状态"};
        for (int i = 0; i < titles.length; i++) {
            //某个单元格
            HSSFCell cell = row.createCell(i);
            //为单元格添赋值
            cell.setCellValue(titles[i]);;
            //赋予其样式
            cell.setCellStyle(cellStyle);
        }

        //在数据库差查速据
        List<Admin> select = adminService.select();
        for (int i = 0; i < select.size(); i++) {
            HSSFRow row1 = sheet.createRow(i + 1);
            row1.createCell(0).setCellValue(select.get(i).getId());
            row1.createCell(1).setCellValue(select.get(i).getUsername());
            row1.createCell(2).setCellValue(select.get(i).getPassword());
            row1.createCell(3).setCellValue(select.get(i).getOther());
            HSSFCell cell = row1.createCell(4);
        }
        //设置下载格式
        response.setHeader("content-disposition","attachment;filename=admin.xls");
        //打应
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            sheets.write(outputStream);
            outputStream.close();
            sheets.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
