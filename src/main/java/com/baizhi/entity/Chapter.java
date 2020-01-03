package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//章节列表
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Chapter implements Serializable {
    public String id;
    public String title;
    public String album_Id;
    public String size;
    public String duration;
    public String src;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    public Date create_date;
    private String status;
    public String other;


}
