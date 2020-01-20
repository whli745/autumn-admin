package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.system.dao.ISequenceDetailDao;
import com.whli.autumn.model.system.SequenceDetail;
import com.whli.autumn.system.service.ISequenceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>条码规则明细业务实现</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Service("sequenceDetailService")
public class SequenceDetailServiceImpl extends BaseServiceImpl<SequenceDetail> implements ISequenceDetailService {

	@Autowired
	private ISequenceDetailDao sequenceDetailDao;

	@Override
	public IBaseDao<SequenceDetail> getDao() {
		return sequenceDetailDao;
	}

	@Override
	public List<SequenceDetail> getByBarcode(String code) {
		return sequenceDetailDao.getByBarcode(code);
	}
}
