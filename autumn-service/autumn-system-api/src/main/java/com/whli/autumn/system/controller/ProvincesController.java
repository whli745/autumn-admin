package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Provinces;
import com.whli.autumn.system.service.IProvincesService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>区域API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/provinces")
@Api(description = "区域API")
public class ProvincesController extends BaseController<Provinces>{

	@Autowired
	private IProvincesService provincesService;
	
	@Override
    public IBaseService<Provinces> getService() {
        return provincesService;
    }
}

