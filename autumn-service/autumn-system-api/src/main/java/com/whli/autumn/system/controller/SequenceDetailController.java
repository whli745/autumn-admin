package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.SequenceDetail;
import com.whli.autumn.system.service.ISequenceDetailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>条码规则明细API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/sequenceDetail")
@Api(description = "条码规则明细API")
public class SequenceDetailController extends BaseController<SequenceDetail>{

	@Autowired
	private ISequenceDetailService sequenceDetailService;
	
	@Override
    public IBaseService<SequenceDetail> getService() {
        return sequenceDetailService;
    }
}

