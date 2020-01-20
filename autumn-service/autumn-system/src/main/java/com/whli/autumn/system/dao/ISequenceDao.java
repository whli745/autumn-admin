package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Sequence;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>条码规则数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface ISequenceDao extends IBaseDao<Sequence> {

    /**
     * 验证条码规则编码唯一性
     * @param entity com.whli.autumn.model.system.Sequence
     * @return com.whli.autumn.model.system.Sequence
     */
    Sequence validate(@Param("entity") Sequence entity);
}
