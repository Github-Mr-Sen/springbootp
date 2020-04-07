package com.zsxk.online.subjectservice.controller;


import com.zsxk.online.common.response.Result;
import com.zsxk.online.subjectservice.entity.EduVideo;
import com.zsxk.online.subjectservice.service.EduVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author 张在森
 * @since 2020-03-31
 */
@RestController
@RequestMapping("/subjectservice/video")
@CrossOrigin
public class EduVideoController {

    @Autowired
    EduVideoService service;
    /**
     * 添加小结
     * */
    @PostMapping
    public Result saveNewVideo(@RequestBody(required = true) EduVideo video) {

        boolean save = this.service.save(video);

        return save ? Result.ok():Result.error();
    }

    /**
     * 删除小节
     *  TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
     *
     *  */
    @DeleteMapping("/{id}")
    public Result deleteVideo(@PathVariable("id") String id) {

        boolean b = this.service.removeById(id);

        return b? Result.ok():Result.error();
    }

    /**
     * 修改小节
     *  TODO 后面这个方法需要完善：删除小节时候，同时把里面视频删除
     *
     *  */
    @PutMapping
    public Result updateVideo(@RequestBody(required = true) EduVideo video) {
        boolean b = this.service.updateById(video);
        return b? Result.ok():Result.error();
    }
    /**
     * 修改小节时的数据回显
     *
     *  */
    @GetMapping("/{id}")
    public Result getVideoInfoByid(@PathVariable("id") String id) {
        EduVideo byId = this.service.getById(id);

//        boolean b = this.service.updateById(video);
        return byId!=null? Result.ok().data("video",byId):Result.error();
    }




}

