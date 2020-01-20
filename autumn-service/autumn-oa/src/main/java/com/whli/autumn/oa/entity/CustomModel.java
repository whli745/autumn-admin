package com.whli.autumn.oa.entity;

import org.activiti.engine.impl.persistence.entity.ModelEntity;

/**
 * <p>Activity 模型类</p>
 * @author whli
 * @date 2019/1/16 10:39
 */
public class CustomModel extends ModelEntity {
    //分页相关
    private Integer pageNumber;
    private Integer pageSize ;

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
