package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Menu;
import com.whli.autumn.system.dto.MenuDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>菜单数据操作</p>
 * @author whli
 * @version 1.0.0
 * @since 2019-09-01 17:13
 */
@Mapper
public interface IMenuDao extends IBaseDao<Menu> {

    /**
     * <p>依据登录用户、公司、父ID查询授权菜单</p>
     * @param entity com.whli.autumn.system.dto.MenuDTO
     * @return java.util.List<com.whli.autumn.system.dto.MenuDTO>
     */
    List<MenuDTO> listMenuByUserAndCompanyAndParent(@Param("entity") MenuDTO entity);

    /**
     * <p>依据登录用户、公司、父ID查询授权按钮</p>
     * @param entity com.whli.autumn.system.dto.MenuDTO
     * @return java.util.List<com.whli.autumn.model.system.Menu>
     */
    List<Menu> listButtonByUserAndCompanyAndParentUrl(@Param("entity") MenuDTO entity);

    /**
     * 验证菜单名称、排序唯一性
     * @param entity com.whli.autumn.model.system.Menu
     * @return com.whli.autumn.model.system.Menu
     */
    Menu validate(@Param("entity") Menu entity);
}
