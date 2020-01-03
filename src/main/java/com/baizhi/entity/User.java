package com.baizhi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String id;
    private String phone_number; //电话
    private String password; //密码
    private String name;//姓名
    private String dharma;//法名
    private String head_img;//头像
    private String sex;//性别
    private String address;//地址
    private String sign;//个签
    private String guru_id;//上师id
    private Date last_date;//时间
    private Date create_date;
    private String status;//状态
    private String salt;//盐值
    private String other;
}
