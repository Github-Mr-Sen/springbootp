package com.zsxk.online.subjectservice.service;

import com.zsxk.online.subjectservice.entity.EduSubject;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 课程科目 服务类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-29
 */
public interface EduSubjectService extends IService<EduSubject> {

    void saveSub(MultipartFile fil);

}
