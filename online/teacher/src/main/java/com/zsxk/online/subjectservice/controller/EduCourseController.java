package com.zsxk.online.subjectservice.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsxk.online.common.response.Result;
import com.zsxk.online.subjectservice.entity.EduCourse;
import com.zsxk.online.subjectservice.entity.vo.CourseInfo;
import com.zsxk.online.subjectservice.entity.vo.CoursePublis;
import com.zsxk.online.subjectservice.service.EduCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

        String id =this.service.saveCouseInfo(info);
        return Result.ok().data("courseId",id);

    }


    @GetMapping("/{id}")
    public Result getCourseInfoById(@PathVariable("id") String id) {

        CourseInfo course;
        course = this.service.getCourseInfoById(id);

        return Result.ok().data("course",course);
    }
    @PutMapping()
    public Result updateCourseById(@RequestBody(required = true) CourseInfo courseInfo){

        this.service.updateCourse(courseInfo);

        return Result.ok();
    }
    @GetMapping("/publish/{id}")
    public Result getPublisInfo(@PathVariable("id") String id){

        CoursePublis coursePublis = this.service.publishInfo(id);

        return Result.ok().data("course",coursePublis);
    }
    /*
    * 课程的最终发布，就是把status值改为normal
    * */
    @PutMapping("/publish/{id}")
    public Result updatePubliStatus(@PathVariable("id") String id){

        EduCourse coursePublis = new EduCourse();
        coursePublis.setId(id);
        coursePublis.setStatus("Normal");
        this.service.updateById(coursePublis);
        return Result.ok();
    }

    @PostMapping("/publish/list/{currentPage}/{size}")
    public Result updatePubliStatus(@PathVariable("currentPage") long currentPage,
                                     @PathVariable("size") long size,
                                    @RequestBody(required = false) EduCourse course){
        QueryWrapper<EduCourse> eduCourseQueryWrapper = new QueryWrapper<>();
        Page<EduCourse> eduCoursePage = new Page<>(currentPage,size);
        if (course != null) {
            eduCourseQueryWrapper.eq("title", course.getTitle());
            eduCourseQueryWrapper.eq("status", course.getStatus());
        }
        IPage<EduCourse> page = this.service.page(eduCoursePage, eduCourseQueryWrapper);
        long total = page.getTotal();
        List<EduCourse> records = page.getRecords();
        return Result.ok().data("total",total).
                            data("records",records).
                            data("currentPage",currentPage);
    }


}

