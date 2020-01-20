package com.whli.autumn.system.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Dict;
import com.whli.autumn.system.service.IDictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>字典API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/dict")
@Api(description = "字典API")
public class DictController extends BaseController<Dict>{

	@Autowired
	private IDictService dictService;
	
	@Override
    public IBaseService<Dict> getService() {
        return dictService;
    }

    /**
     * 依据Code查询字典
     * @param entity com.whli.autumn.model.system.Dict
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     */
    @PostMapping("listByCode")
    @ApiOperation("依据Code查询字典")
    public ResponseBean listByCode(@RequestBody Dict entity, HttpServletRequest request) throws Exception{
        List<Dict> dicts = dictService.listByCode(entity.getCode());
        return ResponseBean.success(dicts);
    }

    /**
     * 依据Code查询字典，并用Code作为KEY
     * @param entity com.whli.autumn.model.system.Dict
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     */
    @PostMapping("listCodeKeyByCode")
    @ApiOperation("依据Code查询字典，并用Code作为KEY")
    public ResponseBean listCodeKeyByCode(@RequestBody Dict entity, HttpServletRequest request) throws Exception{
        Map<String, String> dictMap = dictService.listCodeKeyByCode(entity.getCode());
        return ResponseBean.success(dictMap);
    }

    /**
     * 依据Code查询字典，并用Name作为KEY
     * @param entity com.whli.autumn.model.system.Dict
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     */
    @PostMapping("listNameKeyByCode")
    @ApiOperation("依据Code查询字典，并用Name作为KEY")
    public ResponseBean listNameKeyByCode(@RequestBody Dict entity, HttpServletRequest request) throws Exception{
        Map<String, String> dictMap = dictService.listNameKeyByCode(entity.getCode());
        return ResponseBean.success(dictMap);
    }

    /**
     * 依据多个Code查询，查询结果以Code作为KEY，并最终以KEY-VALUE形式展现
     * @param codes java.util.List<java.lang.String>
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     */
    @PostMapping("listCodeKeyByCodes")
    @ApiOperation("依据多个Code查询，查询结果以Code作为KEY，并最终以KEY-VALUE形式展现")
    public ResponseBean listCodeKeyByCodes(@RequestBody List<String> codes, HttpServletRequest request) throws Exception{
        Map<String, Map<String, String>> dictMaps = dictService.listCodeKeyByCodes(codes);
        return ResponseBean.success(dictMaps);
    }

    /**
     * 验证字典编码、排序的唯一性
     * @param entity com.whli.autumn.model.system.Dict
     * @param request javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证字典编码、排序的唯一性")
    public String validate(Dict entity, HttpServletRequest request) throws Exception{
        Dict temp = dictService.validate(entity);
        if (temp == null){
            return "true";
        }
        return "false";
    }
}

