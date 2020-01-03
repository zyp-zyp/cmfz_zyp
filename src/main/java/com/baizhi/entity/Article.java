package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//文章
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article implements Serializable {
    private String id;
    private String title;    //标题
    private String author;     //作者
    private String content;     //简介
    private String guru_id;     //
    private String status;    //状态
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    private Date create_date;  //发布时间
    private String other;
}
