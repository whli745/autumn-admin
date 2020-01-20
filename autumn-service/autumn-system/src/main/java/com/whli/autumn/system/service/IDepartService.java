package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Depart;

/**
 * <p>部门业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-10-26 00:20
 */
public interface IDepartService extends IBaseService<Depart>{

    /**
     * 验证部门编码的唯一性
     * @param entity com.whli.autumn.model.system.Depart
     * @return
     */
    Depart validate(Depart entity);
}
