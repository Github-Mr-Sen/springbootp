package com.zsxk.online.teacherservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsxk.online.common.Result;
import com.zsxk.online.teacherservice.entity.EduTeacher;
import com.zsxk.online.teacherservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author 张在森
 * @since 2020-03-25
 */
@RestController
@RequestMapping("/teacherservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService service;
    @GetMapping()
    public Result getAllTeachers() {
        //返回所有的老师信息
        List<EduTeacher> list = service.list(null);

        return Result.ok().data("item",list);

    }
@DeleteMapping("/{id}")
    public boolean delTeacherByid(@PathVariable("id") String id) {

    boolean b = service.removeById(id);
    return b;

}


    @GetMapping("/{current}/{size}")
    public Result getAllPageTeachers(@PathVariable("current") long current,
                                     @PathVariable("size") long size) {
        Map<String, Object> data = new HashMap<>();
        Page<EduTeacher> page =new Page<>(current, size);
        service.page(page, null);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        data.put("records", records);
        data.put("total", total);
        System.out.println(records.size());
        return Result.ok().data(data);
    }

}

