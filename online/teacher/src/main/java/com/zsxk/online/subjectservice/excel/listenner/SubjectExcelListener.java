package com.zsxk.online.subjectservice.excel.listenner;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zsxk.online.subjectservice.entity.EduSubject;
import com.zsxk.online.subjectservice.excel.SubjectData;
import com.zsxk.online.subjectservice.service.EduSubjectService;

public class SubjectExcelListener extends AnalysisEventListener<SubjectData>{

    private EduSubjectService service;

    public SubjectExcelListener() {
    }
    /**
     * 因为easyexcel的llistener不能被ioc容器管理
     * 所以通过构造器实例化数据库接口
     *
    */
    public SubjectExcelListener(EduSubjectService service) {
        this.service = service;
    }

    /**
    *
    *读取excel内容
    * */
    @Override
    public void invoke(SubjectData data, AnalysisContext context) {
        if (data == null) {
            throw new RuntimeException("表格没有数据");

        }
        EduSubject exitPatentSub = this.isExitPatentSub(data.getParentSub());
        if (exitPatentSub == null) {
            exitPatentSub = new EduSubject();
            exitPatentSub.setParentId("0");
            exitPatentSub.setTitle(data.getParentSub());
            this.service.save(exitPatentSub);
        }

        EduSubject exitChildtSub = this.isExitChildtSub(data.getChildSub(), exitPatentSub.getId());

        if (exitChildtSub == null) {
            exitChildtSub = new EduSubject();
            exitChildtSub.setTitle(data.getChildSub());
            exitChildtSub.setParentId(exitPatentSub.getId());
            this.service.save(exitChildtSub);
        }


    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {

    }

    private EduSubject isExitPatentSub(String data){

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",data);
        wrapper.eq("parent_id", "0");
        EduSubject one = service.getOne(wrapper);
        return one;
    }
    private EduSubject isExitChildtSub(String title,String pid){

        QueryWrapper<EduSubject> wrapper = new QueryWrapper<>();
        wrapper.eq("title",title);
        wrapper.eq("parent_id", pid);
        EduSubject one = service.getOne(wrapper);
        return one;
    }
}
