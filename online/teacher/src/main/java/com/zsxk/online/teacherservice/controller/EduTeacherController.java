package com.zsxk.online.teacherservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsxk.online.common.Result;
import com.zsxk.online.teacherservice.entity.EduTeacher;
import com.zsxk.online.teacherservice.entity.QueryTeacher;
import com.zsxk.online.teacherservice.service.EduTeacherService;
import org.apache.commons.lang3.StringUtils;
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

/*
* 分页查询EduTeacher表
* */
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
/*
* 不定条件查询EduTeacher表
*
* */
    @PostMapping("/{current}/{size}")
    public Result conditonPageTeachers(@PathVariable("current") long current,
                                       @PathVariable("size") long size,
                                      @RequestBody(required = false) QueryTeacher query) {

        Page<EduTeacher> page = new Page<>(current, size);
        service.condionQuery(page,query);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        return  Result.ok().data("total",total).data("records",records);
    }
//这里其实可以直接
//    wrapper.like(StringUtils.isNotEmpty(queryTeacher.getName()),"name",queryTeacher.getName());
}

