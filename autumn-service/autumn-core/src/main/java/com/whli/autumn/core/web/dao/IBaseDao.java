package com.whli.autumn.core.web.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.whli.autumn.core.web.entity.BaseEntity;

/**
 * <p>通用数据模型</p>
 * @author whli
 * @version 1.0.0
 * @since 2019/9/28 8:47
 */
public interface IBaseDao<T extends BaseEntity> extends BaseMapper<T> {
}
