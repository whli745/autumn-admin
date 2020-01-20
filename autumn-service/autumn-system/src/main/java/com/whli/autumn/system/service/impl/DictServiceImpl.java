package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.IDictDao;
import com.whli.autumn.model.system.Dict;
import com.whli.autumn.system.service.IDictService;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>字典业务实现</p>
 *
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("dictService")
public class DictServiceImpl extends BaseServiceImpl<Dict> implements IDictService {

    @Autowired
    private IDictDao dictDao;

    @Override
    public IBaseDao<Dict> getDao() {
        return dictDao;
    }

    @Override
    public List<Dict> listByCode(String code) {
        return dictDao.listByCode(code);
    }

    @Override
    public Map<String, String> listCodeKeyByCode(String code) {
        Map<String, String> result = new HashMap<>();
        List<Dict> dicts = this.listByCode(code);
        if (CollectionUtils.isNotEmpty(dicts)) {
            dicts.parallelStream().forEach(dict -> result.put(dict.getCode(), dict.getName()));
        }
        return result;
    }

    @Override
    public Map<String, String> listNameKeyByCode(String code) {
        Map<String, String> result = new HashMap<>();
        List<Dict> dicts = this.listByCode(code);
        if (CollectionUtils.isNotEmpty(dicts)) {
            dicts.parallelStream().forEach(dict -> result.put(dict.getName(), dict.getCode()));
        }
        return result;
    }

    @Override
    public Map<String, Map<String, String>> listCodeKeyByCodes(List<String> codes) {

        Map<String, Map<String, String>> result = new HashMap<>();

        codes.forEach(code -> {
            Map<String,String> dictMap = this.listCodeKeyByCode(code);
            result.put(code,dictMap);
        });

        return result;
    }

    @Override
    public Dict validate(Dict entity) {
        return dictDao.validate(entity);
    }
}
