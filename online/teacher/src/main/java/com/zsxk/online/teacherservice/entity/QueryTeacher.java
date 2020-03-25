package com.zsxk.online.teacherservice.entity;

import lombok.Data;
/*
* 封装不定条件查询参数
* */
@Data
public class QueryTeacher {

    private String name ;

    private String level;

    private String begin;

    private  String end;
}
