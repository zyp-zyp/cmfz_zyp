package com.baizhi;

import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.apache.poi.hssf.usermodel.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class PoiTests {

    @Resource
    private BannerDao bannerDao;
    @Test
    public void contextLoads() {
        //创建excle文件
        HSSFWorkbook sheets = new HSSFWorkbook();

        //创建字体对象
        HSSFFont font = sheets.createFont();
            //字体大小
        font.setFontHeightInPoints((short) 20);
            //加粗
        font.setBold(true);
            //
        font.setColor((short)5555);

        //自定义日期类型
        HSSFDataFormat dataFormat = sheets.createDataFormat();
        short format = dataFormat.getFormat("yyyy年MM月dd日");

            //单元格样式
        HSSFCellStyle cellStyle = sheets.createCellStyle();

        cellStyle.setDataFormat(format);
        cellStyle.setFont(font);


        //创建工作薄
        HSSFSheet sheet = sheets.createSheet();
        //创建行
        HSSFRow row = sheet.createRow(0);
        //创建单元格
        HSSFCell cell = row.createCell(0);
        HSSFCell cell1 = row.createCell(1);
        HSSFCell cell2 = row.createCell(2);
        //为单元格设值
        cell.setCellValue("你好");
        cell1.setCellValue(new Date());
        //指定excle 输出的位置 以及文件名
        try {
            sheets.write(new File("D:/test.xls"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testSelectDay7(){
        int[] ints = bannerDao.selectDay7();
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
