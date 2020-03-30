package com.zsxk.online.subjectservice.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.zsxk.online.common.response.Result;
import com.zsxk.online.subjectservice.entity.EduSubject;
import com.zsxk.online.subjectservice.entity.treenode.ChildNode;
import com.zsxk.online.subjectservice.entity.treenode.ParentNode;
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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
 *读取excel中的数据，再将数据保存到数据库中
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


    @Override
    public List<ParentNode> getSubTree() {

        /*
        *
        * {
          id: 1,
          label: '一级 1',
          children: [{
            id: 4,
            label: '二级 1-1',
            children: [{
              id: 9,
              label: '三级 1-1-1'
            }, {
              id: 10,
              label: '三级 1-1-2'
            }]
          }]
        }
        *
        * */

        Set<ParentNode> pn = new HashSet<>();

        List<ParentNode> pList = new ArrayList<>();


        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("parent_id", "0");
        //2
        List<EduSubject> parent = this.service.list(wrapper);
        for (int i = 0; i < parent.size(); i++) {
            ParentNode pnode = new ParentNode();
            pnode.setId(parent.get(i).getId());
            pnode.setTitle(parent.get(i).getTitle());

            QueryWrapper<EduSubject> childWra = new QueryWrapper<>();
            childWra.eq("parent_id", parent.get(i).getId());
            List<EduSubject> children = this.service.list(childWra);


            List<ChildNode> cList = new ArrayList<>();
            //4
            for (EduSubject sub:children) {
                ChildNode childNode = new ChildNode();
                childNode.setId(sub.getId());
                childNode.setTitle(sub.getTitle());
                cList.add(childNode);//4
            }
            pnode.setChildren(cList); //1-4
            pList.add(pnode);
        }
        return pList;

    }

}
