package com.zsxk.online.subjectservice.service.impl;

import com.zsxk.online.subjectservice.entity.EduCourse;
import com.zsxk.online.subjectservice.entity.EduCourseDescription;
import com.zsxk.online.subjectservice.entity.vo.CourseInfo;
import com.zsxk.online.subjectservice.mapper.EduCourseMapper;
import com.zsxk.online.subjectservice.service.EduCourseDescriptionService;
import com.zsxk.online.subjectservice.service.EduCourseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-31
 */
@Service
public class EduCourseServiceImpl extends ServiceImpl<EduCourseMapper, EduCourse> implements EduCourseService {

    @Autowired
    private EduCourseDescriptionService descriptionService;
    /**
     * 保存课程信息，课程主表信息，描述信息
     * */
    @Override
    public void saveCouseInfo(CourseInfo info) {
        EduCourse course = new EduCourse();
        BeanUtils.copyProperties(info,course);
        //向EduCourse表中添加数据
        int insert = this.baseMapper.insert(course);
        if (insert<=0) {
            throw new RuntimeException("课程添加数据失败");
        }
        //EduCourse 表中数据添加成功后，再向EduCourseDescription表中添加描述信息
        EduCourseDescription description = new EduCourseDescription();
        BeanUtils.copyProperties(info,description);
        //课程id和描述信息的id相等，这样就建立了关联
        description.setId(course.getId());
        this.descriptionService.save(description);

    }
}
