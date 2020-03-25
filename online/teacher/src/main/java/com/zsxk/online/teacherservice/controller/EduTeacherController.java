package com.zsxk.online.teacherservice.controller;


import com.zsxk.online.common.Result;
import com.zsxk.online.teacherservice.entity.EduTeacher;
import com.zsxk.online.teacherservice.service.EduTeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        List<EduTeacher> list = service.list(null);

        return Result.ok().data("item",list);

    }
@DeleteMapping("/{id}")
    public boolean delTeacherByid(@PathVariable("id") String id) {

    boolean b = service.removeById(id);
    return b;

}

}

