package com.zsxk.online.subjectservice.service;

import com.zsxk.online.subjectservice.entity.EduCourse;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsxk.online.subjectservice.entity.vo.CourseInfo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-31
 */
public interface EduCourseService extends IService<EduCourse> {

    String saveCouseInfo(CourseInfo info);

    CourseInfo getCourseInfoById(String id);

    void updateCourse(CourseInfo courseInfo);
}
