package com.zsxk.online.subjectservice.service;

import com.zsxk.online.subjectservice.entity.EduChapter;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zsxk.online.subjectservice.entity.course.Chapter;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-31
 */
public interface EduChapterService extends IService<EduChapter> {

    List<Chapter> chapterInfoByCourseId(String id);

    boolean deleteChapter(String chapterId);

}
