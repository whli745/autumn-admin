package com.whli.autumn.system.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Barcode;
import com.whli.autumn.system.dto.BarcodeDTO;
import com.whli.autumn.system.service.IBarcodeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>条码生成API</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@RestController
@RequestMapping(value="/system/barcode")
@Api(description = "条码生成API")
public class BarcodeController extends BaseController<Barcode> {

	@Autowired
	private IBarcodeService barcodeService;
	
	@Override
    public IBaseService<Barcode> getService() {
        return barcodeService;
    }

    /**
     * 分布查询，并关联条码规则
     * @param entity com.whli.autumn.system.dto.BarcodeDTO
     * @param request javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping("/listBarcodeByPage")
    @ApiOperation("分页查询")
    public ResponseBean listBarcodeByPage(@RequestBody BarcodeDTO entity, HttpServletRequest request) throws Exception{
        IPage<BarcodeDTO> page = barcodeService.listBarcodeByPage(entity, entity.getPageNumber(), entity.getPageSize());

        return ResponseBean.getInstance(0,null,page.getRecords(),new Long(page.getTotal()).intValue());
    }

    /**
     * 验证条码生成编码的唯一性
     * @param entity com.whli.autumn.model.system.Barcode
     * @param request javax.servlet.http.HttpServletRequest
     * @return java.lang.String
     * @throws Exception
     */
    @PostMapping("validate")
    @ApiOperation("验证条码生成编码的唯一性")
    public String validate(Barcode entity, HttpServletRequest request) throws Exception{
        Barcode temp = barcodeService.validate(entity);
        if (temp == null){
            return "true";
        }
        return "false";
    }
}

