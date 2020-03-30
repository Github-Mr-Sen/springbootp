package com.zsxk.online.subjectservice.controller;


import com.alibaba.excel.EasyExcel;
import com.zsxk.online.common.response.Result;
import com.zsxk.online.subjectservice.entity.treenode.ParentNode;
import com.zsxk.online.subjectservice.excel.SubjectData;
import com.zsxk.online.subjectservice.excel.listenner.SubjectExcelListener;
import com.zsxk.online.subjectservice.service.EduSubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

/**
 * <p>
 * 课程科目 前端控制器
 * </p>
 *
 * @author 张在森
 * @since 2020-03-29
 */
@RestController
@RequestMapping("/subjectservice/subject")
@CrossOrigin
public class EduSubjectController {

    @Autowired
    private EduSubjectService service;
    @PostMapping
    public Result addSubject(MultipartFile file) {

        try {
//            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(service));

            service.saveSub(file);
            return Result.ok();


        } catch (Exception e) {
            e.printStackTrace();
            return Result.error().message(e.getMessage());
        }

    }

    @GetMapping
    public Result subTree() {
       List<ParentNode> treeList =  service.getSubTree();

        return Result.ok().data("list",treeList);

    }
}

