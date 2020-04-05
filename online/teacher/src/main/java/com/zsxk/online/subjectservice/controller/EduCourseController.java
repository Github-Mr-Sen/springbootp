package com.zsxk.online.subjectservice.controller;


import com.zsxk.online.common.response.Result;
import com.zsxk.online.subjectservice.entity.EduCourse;
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

}

