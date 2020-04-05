package com.zsxk.online.subjectservice.entity.course;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 封装章节信息
 *
 * */
@Data
public class Chapter {
    private String id;

    private String title;

    List<Video> children = new ArrayList<>();
}
