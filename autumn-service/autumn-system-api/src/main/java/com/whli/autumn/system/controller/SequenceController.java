package com.whli.autumn.system.controller;

import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Sequence;
import com.whli.autumn.system.service.ISequenceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>条码规则API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/sequence")
@Api(description = "条码规则API")
public class SequenceController extends BaseController<Sequence>{

	@Autowired
	private ISequenceService sequenceService;
	
	@Override
    public IBaseService<Sequence> getService() {
        return sequenceService;
    }

    /**
     * 验证条码规则编码的唯一性
     * @param entity com.whli.autumn.model.system.Sequence
     * @param request javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证条码规则编码的唯一性")
    public String validate(Sequence entity, HttpServletRequest request) throws Exception{
        Sequence temp = sequenceService.validate(entity);
        if (temp == null){
            return "true";
        }
        return "false";
    }
}

