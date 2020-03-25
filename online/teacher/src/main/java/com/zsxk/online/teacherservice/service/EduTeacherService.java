package com.zsxk.online.teacherservice.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsxk.online.teacherservice.entity.EduTeacher;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsxk.online.teacherservice.entity.QueryTeacher;

/**
 * <p>
 * 讲师 服务类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-25
 */
public interface EduTeacherService extends IService<EduTeacher> {

    void condionQuery(Page<EduTeacher> page, QueryTeacher query);
}
