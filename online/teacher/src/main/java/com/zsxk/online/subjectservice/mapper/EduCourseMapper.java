package com.zsxk.online.subjectservice.mapper;

import com.zsxk.online.subjectservice.entity.EduCourse;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zsxk.online.subjectservice.entity.vo.CoursePublis;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author 张在森
 * @since 2020-03-31
 */
public interface EduCourseMapper extends BaseMapper<EduCourse> {
    public CoursePublis getCoursePublishInfo(String courseId);

}
