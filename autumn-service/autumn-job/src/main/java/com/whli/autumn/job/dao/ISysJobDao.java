package com.whli.autumn.job.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.job.SysJob;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>定时任务数据操作<p>
 * @author whli
 * @version 2018/12/24 13:54
 */
@Mapper
public interface ISysJobDao extends IBaseDao<SysJob> {

    /**
     * 分页查询
     * @param page
     * @param entity
     * @return
     */
    List<SysJob> listTaskByPage(@Param("page") Page<SysJob> page, @Param("entity") SysJob entity);
}
