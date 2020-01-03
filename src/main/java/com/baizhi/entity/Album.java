package com.baizhi.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

//专辑
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Album implements Serializable {
    public String id;
    public String title;
    public String img;
    public String score;
    public String author;
    public String broadcaster;
    public String count;
    public String brief;
    @DateTimeFormat(pattern = "yyyy-MM-dd" )
    @JsonFormat(pattern = "yyyy-MM-dd" ,timezone = "GMT+8")
    public Date create_date;
    public String status;
    public String other;
}
