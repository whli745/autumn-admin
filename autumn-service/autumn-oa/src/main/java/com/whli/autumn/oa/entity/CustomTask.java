package com.whli.autumn.oa.entity;

import org.activiti.engine.impl.persistence.entity.TaskEntity;

/**
 * <p>Activity 任务类</p>
 *
 * @author whli
 * @version 1.0.0
 * @date 2019/6/26 10:16
 */
public class CustomTask extends TaskEntity {
    //分页相关
    private Integer pageNumber;
    private Integer pageSize ;

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
