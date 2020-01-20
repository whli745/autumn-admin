package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.SequenceDetail;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>条码规则明细数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface ISequenceDetailDao extends IBaseDao<SequenceDetail> {

    /**
     * 依据条码规则Code查询序列明细
     * @param code java.lang.String 条码规则编码
     * @return java.util.List<com.whli.autumn.model.system.SequenceDetail>
     */
    List<SequenceDetail> getByBarcode(String code);
}
