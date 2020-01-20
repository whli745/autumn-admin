package com.whli.autumn.system.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Barcode;
import com.whli.autumn.system.dto.BarcodeDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>条码生成数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IBarcodeDao extends IBaseDao<Barcode> {

    /**
     * 分页查询
     * @param page com.baomidou.mybatisplus.extension.plugins.pagination.Page
     * @param entity com.whli.autumn.system.dto.BarcodeDTO
     * @return java.util.List<com.whli.autumn.system.dto.BarcodeDTO>
     */
    List<BarcodeDTO> listBarcodeByPage(@Param("page") Page<BarcodeDTO> page, @Param("entity") BarcodeDTO entity);


    /**
     * 验证条码生成编码唯一性
     * @param entity com.whli.autumn.model.system.Barcode
     * @return com.whli.autumn.model.system.Barcode
     */
    Barcode validate(@Param("entity") Barcode entity);
}
