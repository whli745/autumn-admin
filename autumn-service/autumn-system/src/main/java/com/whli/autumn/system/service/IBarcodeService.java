package com.whli.autumn.system.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Barcode;
import com.whli.autumn.system.dto.BarcodeDTO;

/**
 * <p>条码生成业务</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
public interface IBarcodeService extends IBaseService<Barcode> {

    /**
     * 依据标识生成条码
     * @param code java.lang.String 条码标识
     * @return java.lang.String
     */
    String generatorCode(String code);

    IPage<BarcodeDTO> listBarcodeByPage(BarcodeDTO entity, Integer pageNumber, Integer pageSize);

    /**
     * 验证条码生成编码的唯一性
     * @param entity com.whli.autumn.model.system.Barcode
     * @return
     */
    Barcode validate(Barcode entity);
}
