package com.lzz.model;

import java.util.List;

/**
 * Created by lzz on 2017/8/4.
 */
public class MasterNode extends SlaveNode{
    private List<SlaveNode> children;

    public List<SlaveNode> getChildren() {
        return children;
    }

    public void setChildren(List<SlaveNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "MasterNode{" +
                "children=" + children +
                '}';
    }
}
