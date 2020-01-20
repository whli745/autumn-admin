package com.whli.autumn.core.web.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 通用entity类
 * Created by whli on 2018/1/10.
 */
@Data
public class BaseEntity implements Serializable {

    @TableId(type = IdType.ID_WORKER_STR)
    private String id;

    /**
     * createBy 创建者
     */
    private String createBy;

    /**
     * createDate 创建时间
     */
    private Date createDate;

    /**
     * updateBy 修改者
     */
    private String updateBy;

    /**
     * updateDate 修改时间
     */
    private Date updateDate;


    /**
     * 分页条件
     */
    @TableField(exist = false)
    private Integer pageNumber = 1;
    @TableField(exist = false)
    private Integer pageSize = 10;
}
