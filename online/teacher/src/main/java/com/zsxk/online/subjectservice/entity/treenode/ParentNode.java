package com.zsxk.online.subjectservice.entity.treenode;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParentNode {
    private String id;
    private String title;

    private List<ChildNode> children = new ArrayList<>();

}
