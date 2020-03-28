package com.zsxk.online.teacherservice.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsxk.online.common.response.Result;
import com.zsxk.online.teacherservice.entity.EduTeacher;
import com.zsxk.online.teacherservice.entity.QueryTeacher;
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
@CrossOrigin
@RestController
@RequestMapping("/teacherservice/teacher")
public class EduTeacherController {

    @Autowired
    private EduTeacherService service;

    /**
     * 获取所有记录
     */
    @GetMapping()
    public Result getAllTeachers() {
        //返回所有的老师信息
        List<EduTeacher> list = service.list(null);

        return Result.ok().data("item", list);

    }

    /**
     * 删除记录
     */
    @DeleteMapping("/{id}")
    public Result delTeacherByid(@PathVariable("id") String id) {

        boolean b = service.removeById(id);
        if (b) {
            Result.ok();
        }
        return Result.error();

    }

    /**
     * 分页查询EduTeacher表
     */
    @GetMapping("/{current}/{size}")
    public Result getAllPageTeachers(@PathVariable("current") long current,
                                     @PathVariable("size") long size) {
        Map<String, Object> data = new HashMap<>();
        Page<EduTeacher> page = new Page<>(current, size);
        service.page(page, null);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        data.put("records", records);
        data.put("total", total);
        System.out.println(records.size());
        return Result.ok().data(data);
    }

    /**
     * 不定条件查询EduTeacher表
     */
    @PostMapping("/{current}/{size}")
    public Result conditonPageTeachers(@PathVariable("current") long current,
                                       @PathVariable("size") long size,
                                       @RequestBody(required = false) QueryTeacher query) {

        Page<EduTeacher> page = new Page<>(current, size);
        service.condionQuery(page, query);
        List<EduTeacher> records = page.getRecords();
        long total = page.getTotal();
        return Result.ok().data("total", total).
                data("records", records).
                data("page",current).data("size",size);
    }

    /**
     * 添加一条记录
     */
    @PostMapping()
    public Result addTeacher(@RequestBody(required = false) EduTeacher teacher) {

        boolean save = this.service.save(teacher);
        if (save) {
            return Result.ok();
        } else {

            return Result.error();
        }
    }


    /**
     * 根据id查询记录
     */
    @GetMapping("/{id}")
    public Result getThInfo(@PathVariable("id") long id) {

        EduTeacher byId = this.service.getById(id);
        if (byId != null) {
            return Result.ok().data("teacher", byId);
        }
        return Result.error();
    }

    /**
     * 根据id更新数据信息
     */
    @PutMapping("/{id}")
    public Result updateThInfo(@PathVariable("id") String id,
                               @RequestBody EduTeacher updatTh) {

        updatTh.setId(id);

        boolean b = this.service.updateById(updatTh);
        if (b) {
            return Result.ok();
        }
        return Result.error();

    }

    @GetMapping("/exception")
    public Result exceptionTest() {
        int i =10/0;
        return Result.ok();
    }

}