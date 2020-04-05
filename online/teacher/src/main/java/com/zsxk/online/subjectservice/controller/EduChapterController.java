package com.zsxk.online.subjectservice.controller;


import com.baomidou.mybatisplus.extension.api.R;
import com.zsxk.online.common.response.Result;
import com.zsxk.online.subjectservice.entity.EduChapter;
import com.zsxk.online.subjectservice.entity.course.Chapter;
import com.zsxk.online.subjectservice.service.EduChapterService;
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
@RequestMapping("/subjectservice/chapter")
@CrossOrigin
public class EduChapterController {
    @Autowired
    private EduChapterService service;
    /**
     * 获取指定课程的所有的chapter和video
     * **/
    @GetMapping("/{id}")
    public Result getChapter(@PathVariable String id){
        //id为educourse 中的id
        List<Chapter> chapters =this.service.chapterInfoByCourseId(id);

        return Result.ok().data("chapters",chapters);
    }

    /**
     * 添加章节
     * */
    @PostMapping
    public Result addChapter(@RequestBody EduChapter chapter){
        this.service.save(chapter);
        return Result.ok();
    }
    /**
     *修改章节
     * */
    @PutMapping()
    public Result updateChapter(@RequestBody EduChapter chapter) {
        this.service.updateById(chapter);
        return Result.ok();
    }

    //删除的方法
    @DeleteMapping("/{chapterId}")
    public Result deleteChapter(@PathVariable String chapterId) {
        boolean flag = service.deleteChapter(chapterId);
        if(flag) {
            return Result.ok();
        } else {
            return Result.error();
        }

    }

    //根据章节id查询，用于回显数据
    @GetMapping("/update/{chapterId}")
    public Result getChapterInfo(@PathVariable String chapterId) {
        EduChapter eduChapter = service.getById(chapterId);
        return Result.ok().data("chapter",eduChapter);
    }


}

