package com.zsxk.online.subjectservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.zsxk.online.subjectservice.entity.EduSubject;
import com.zsxk.online.subjectservice.excel.SubjectData;
import com.zsxk.online.subjectservice.excel.listenner.SubjectExcelListener;
import com.zsxk.online.subjectservice.mapper.EduSubjectMapper;
import com.zsxk.online.subjectservice.service.EduSubjectService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 * 课程科目 服务实现类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-29
 */
@Service
public class EduSubjectServiceImpl extends ServiceImpl<EduSubjectMapper, EduSubject> implements EduSubjectService {
    @Autowired
    private EduSubjectService service;
/**
 * 添加课程分类
 *
 * @param file*/
    @Override
    public void saveSub(MultipartFile file) {
        try {
            InputStream inputStream = file.getInputStream();
            EasyExcel.read(inputStream, SubjectData.class,new SubjectExcelListener(service)).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
