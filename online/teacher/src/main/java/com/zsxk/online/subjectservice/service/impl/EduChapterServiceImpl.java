package com.zsxk.online.subjectservice.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsxk.online.subjectservice.entity.EduChapter;
import com.zsxk.online.subjectservice.entity.EduVideo;
import com.zsxk.online.subjectservice.entity.course.Chapter;
import com.zsxk.online.subjectservice.entity.course.Video;
import com.zsxk.online.subjectservice.mapper.EduChapterMapper;
import com.zsxk.online.subjectservice.service.EduChapterService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zsxk.online.subjectservice.service.EduVideoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author 张在森
 * @since 2020-03-31
 */
@Service
public class EduChapterServiceImpl extends ServiceImpl<EduChapterMapper, EduChapter> implements EduChapterService {

    @Autowired
    private EduVideoService videoService;
    @Override
    public List<Chapter> chapterInfoByCourseId(String id) {

        //根据id查询所有的chapter
        QueryWrapper<EduChapter> chapterQueryWrapper = new QueryWrapper<>();
        chapterQueryWrapper.eq("course_id", id);
        List<EduChapter> eduChapters = this.baseMapper.selectList(chapterQueryWrapper);
        //根据chapter_id查询所有的video
        QueryWrapper<EduVideo> eduVideoQueryWrapper = new QueryWrapper<>();
        eduVideoQueryWrapper.eq("course_id", id);
        List<EduVideo> eduVideos = this.videoService.list(eduVideoQueryWrapper);
        //将所有的video挂在到对应的chapter上面
        ArrayList<Chapter> finalChaptere = new ArrayList<>();
        for (EduChapter chapter :eduChapters) {

            Chapter chapter1 = new Chapter();
            BeanUtils.copyProperties(chapter,chapter1);
            ArrayList<Video> videos = new ArrayList<>();
            for (EduVideo video : eduVideos) {
                if (chapter.getId().equals(video.getChapterId())) {
                    Video video1 = new Video();
                    BeanUtils.copyProperties(video,video1);
                    videos.add(video1);
                    chapter1.setChildren(videos);
                }
            }

            finalChaptere.add(chapter1);
        }


        return finalChaptere;
    }

    /**
     *
     *查看是否某个chapter下有video
     * */
    public boolean isExitVideos(String chapterId) {
        QueryWrapper<EduVideo> wrapper = new QueryWrapper<>();

        wrapper.eq("chapter_id", chapterId);
        int count = this.videoService.count(wrapper);

        return count>0;
    }

    /**
     *
     * 先查看章节下面是否有小结，如果如果有小结不能别删除
     * */

    @Override
    public boolean deleteChapter(String chapterId) {
        boolean exitVideos = this.isExitVideos(chapterId);
        if (exitVideos) {
            throw new RuntimeException("该章节下没有可删除的小节");
        } else {
            int i = this.baseMapper.deleteById(chapterId);
            return i>0;
        }
    }

}
