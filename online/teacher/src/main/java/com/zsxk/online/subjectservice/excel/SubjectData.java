package com.zsxk.online.subjectservice.excel;


import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
public class SubjectData {

    @ExcelProperty(index = 0)
    private String parentSub;

    @ExcelProperty(index = 1)
    private String childSub;
}
