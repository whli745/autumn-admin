package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Dict;

import java.util.List;
import java.util.Map;

/**
 * <p>字典业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface IDictService extends IBaseService<Dict> {

    /**
     * 依据Code查询字典
     *
     * @param code java.lang.String 父字典编码
     * @return
     */
    List<Dict> listByCode(String code);

    /**
     * 依据Code查询字典，并用Code作为KEY
     *
     * @param code java.lang.String 父字典编码
     * @return
     */
    Map<String, String> listCodeKeyByCode(String code);

    /**
     * 依据Code查询字典，并用Name作为KEY
     *
     * @param code java.lang.String 父字典编码
     * @return
     */
    Map<String, String> listNameKeyByCode(String code);

    /**
     * 依据多个Code查询，查询结果以Code作为KEY，并最终以KEY-VALUE形式展现
     * @param codes java.util.List<java.lang.String>  父字典编码
     * @return
     */
    Map<String,Map<String,String>> listCodeKeyByCodes(List<String> codes);

    /**
     * 验证字典编码、排序的唯一性
     * @param entity com.whli.autumn.model.system.Dict
     * @return
     */
    Dict validate(Dict entity);
}
