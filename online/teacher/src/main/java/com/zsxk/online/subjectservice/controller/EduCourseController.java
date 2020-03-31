package com.zsxk.online.subjectservice.controller;


import com.zsxk.online.common.response.Result;
import com.zsxk.online.subjectservice.entity.vo.CourseInfo;
import com.zsxk.online.subjectservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author 张在森
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/subjectservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    EduCourseService service;
    @PostMapping
    public Result saveCourseInfo(@RequestBody CourseInfo info) {

        this.service.saveCouseInfo(info);
        return Result.ok();

    }

}

