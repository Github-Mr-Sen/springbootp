package com.zsxk.online.teacherservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zsxk.online.teacherservice.entity.EduTeacher;
import com.zsxk.online.teacherservice.entity.QueryTeacher;
import com.zsxk.online.teacherservice.mapper.EduTeacherMapper;
import com.zsxk.online.teacherservice.service.EduTeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * <p>
 * 讲师 服务实现类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-25
 */
@Service
public class EduTeacherServiceImpl extends ServiceImpl<EduTeacherMapper, EduTeacher> implements EduTeacherService {

    @Override
    /*
    * 自定义不定条件查询
    * */
    public void condionQuery(Page<EduTeacher> page, QueryTeacher query) {

        QueryWrapper<EduTeacher> wrapper = new QueryWrapper<>();
        //this.baseMapper 在ServiceImpl 中定义的
        if (query == null) {
            this.baseMapper.selectPage(page, null);
            return;
        }

        String name = query.getName();
        String level = query.getLevel();
        String begin = query.getBegin();
        String end = query.getEnd();
//如果name不为空，添加到查询条件
        wrapper.like(StringUtils.isNotEmpty(name), "name", name);
//        通俗的上面的写法
//        if (!StringUtils.isEmpty(name)) {
//            wrapper.like("name", name);
//        }
        if (!StringUtils.isEmpty(level)) {
            wrapper.eq("level", level);//如果level不为空添加到查询条件
        }
        if (!StringUtils.isEmpty(begin)&&!StringUtils.isEmpty(end)) {
            wrapper.gt("gmt_create", begin);
            wrapper.lt("gmt_create", end);
        }else {

            wrapper.eq(StringUtils.isNotEmpty(end),"gmt_create", end);
            wrapper.eq(StringUtils.isNotEmpty(begin),"gmt_create", begin);
//            try {
//                if (!StringUtils.isEmpty(begin)) {
//                    wrapper.eq("gmt_create", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin));
//
//                }
//                if (!StringUtils.isEmpty(end)) {
//                    wrapper.eq("gmt_create", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end));
//
//                }
//                //这种方式在实践转换时会抛异常
////                wrapper.eq(!StringUtils.isEmpty(begin),"gmt_create", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(begin));
////                wrapper.eq(!StringUtils.isEmpty(begin),"gmt_create", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(end));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
        }

        this.baseMapper.selectPage(page, wrapper);


    }
}
