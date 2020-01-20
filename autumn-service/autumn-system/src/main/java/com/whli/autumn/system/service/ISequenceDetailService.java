package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.SequenceDetail;

import java.util.List;

/**
 * <p>条码规则明细业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface ISequenceDetailService extends IBaseService<SequenceDetail>{

    /**
     * 依据条码规则Code查询序列明细
     * @param code java.lang.String 条码规则编码
     * @return
     */
    List<SequenceDetail> getByBarcode(String code);
}
