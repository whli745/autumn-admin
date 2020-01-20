package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Sequence;

/**
 * <p>条码规则业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface ISequenceService extends IBaseService<Sequence>{

    /**
     * 验证条码规则编码的唯一性
     * @param entity com.whli.autumn.model.system.Sequence
     * @return
     */
    Sequence validate(Sequence entity);
}
