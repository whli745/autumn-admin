package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.ISequenceDao;
import com.whli.autumn.model.system.Sequence;
import com.whli.autumn.system.service.ISequenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>条码规则业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("sequenceService")
public class SequenceServiceImpl extends BaseServiceImpl<Sequence> implements ISequenceService {

	@Autowired
	private ISequenceDao sequenceDao;

	@Override
	public IBaseDao<Sequence> getDao() {
		return sequenceDao;
	}

	@Override
	public Sequence validate(Sequence entity) {
		return sequenceDao.validate(entity);
	}
}
