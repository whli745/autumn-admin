package com.whli.autumn.core.web.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.whli.autumn.core.exception.BusinessException;
import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.web.entity.BaseEntity;
import com.whli.autumn.core.web.service.IBaseService;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>通用API</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/6/23 16:15
 */
public abstract class BaseController<T extends BaseEntity> {

    public abstract IBaseService<T> getService();

    /**
     * 分页查询
     * @param entity T
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping("/listByPage")
    @ApiOperation("分页查询")
    public ResponseBean listByPage(@RequestBody T entity, HttpServletRequest request) throws Exception{
        IPage<T> page = getService().listByPage(entity, entity.getPageNumber(), entity.getPageSize());

        return ResponseBean.getInstance(0,null,page.getRecords(),new Long(page.getTotal()).intValue());
    }

    /**
     * 分页查询，返回结果Map
     * @param entity T
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping("/listMapByPage")
    @ApiOperation("分页查询，以Map形式返回")
    public ResponseBean listMapByPage(@RequestBody T entity, HttpServletRequest request) throws Exception{
        IPage<Map<String,Object>> page = getService().listMapByPage(entity, entity.getPageNumber(), entity.getPageSize());

        return ResponseBean.getInstance(0,null,page.getRecords(),new Long(page.getTotal()).intValue());
    }

    /**
     * 依据主键查询
     * @param entity T
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/getByPK")
    @ApiOperation("根据主键查询")
    public ResponseBean getByPK(@RequestBody T entity, HttpServletRequest req) throws Exception{
        entity = getService().getByPK(entity.getId());
        if (entity == null){
            throw new BusinessException("未找到数据");
        }
        return ResponseBean.success(null,entity);
    }

    /**
     * 根据多个主键查询
     * @param ids java.util.List<String>
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/listByPKs")
    @ApiOperation("根据多个主键查询")
    public ResponseBean listByPKs(@RequestBody List<String> ids, HttpServletRequest req) throws Exception{
        List<T> entities = getService().listByPKs(ids);
        return ResponseBean.success(null,entities);
    }

    /**
     * 查询所有
     * @param entity T
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/listAll")
    @ApiOperation("查找所有结果（可带参数）")
    public ResponseBean listAll(@RequestBody T entity, HttpServletRequest req) throws Exception{
        List<T> entities = getService().listAll(entity);
        return ResponseBean.success(null,entities);
    }

    /**
     * 带参数查询所有，以Map结构返回
     * @param entity T
     * @return com.whli.autumn.core.share.ResponseBean
     */
    @PostMapping(value = "/listMapAll")
    @ApiOperation("查找所有结果（可带参数），以Map形式返回")
    public ResponseBean listMapAll(@RequestBody T entity, HttpServletRequest req) throws Exception{
        List<Map<String, Object>> entities = getService().listMapAll(entity);
        return ResponseBean.success(null,entities);
    }

    /**
     * 新增
     * @param entity T
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/save")
    @ApiOperation("新增")
    public ResponseBean save(@RequestBody T entity, HttpServletRequest req) throws Exception{
        int rows = getService().save(entity);
        if (rows > 0) {
            return ResponseBean.success("新增成功");
        }
        return ResponseBean.fail("新增失败");
    }

    /**
     * 批量新增
     * @param entities java.util.List<T>
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/saveBatch")
    @ApiOperation("批量新增")
    public ResponseBean saveBatch(@RequestBody List<T> entities, HttpServletRequest req) throws Exception{
        int rows = getService().saveBatch(entities);
        if (rows > 0) {
            return ResponseBean.success("批量新增成功");
        }
        return ResponseBean.fail("批量新增失败");
    }

    /**
     * 根据主键修改
     * @param entity T
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/updateByPK")
    @ApiOperation("根据主键修改")
    public ResponseBean updateByPK(@RequestBody T entity, HttpServletRequest req) throws Exception{
        int rows = getService().updateByPK(entity);
        if (rows > 0) {
           return ResponseBean.success("修改成功");
        }
        return ResponseBean.fail("修改失败");
    }

    /**
     * 根据主键批量修改
     * @param entities java.util.List<T>
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/updateBatchByPK")
    @ApiOperation("根据主键批量修改")
    public ResponseBean updateBatchByPK(@RequestBody List<T> entities, HttpServletRequest req) throws Exception{
        int rows = getService().updateBatchByPK(entities);
        if (rows > 0) {
            return ResponseBean.success("批量修改成功");
        }
        return ResponseBean.fail("批量修改失败");
    }

    /**
     * 删除
     * @param entity T
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/deleteByPK")
    @ApiOperation("删除")
    public ResponseBean deleteByPK(@RequestBody T entity, HttpServletRequest req) throws Exception{
        int rows = getService().deleteByPK(entity.getId());
        if (rows > 0){
            return ResponseBean.success("删除成功");
        }
        return ResponseBean.fail("删除失败");
    }

    /**
     * 根据主键批量删除
     * @param ids java.util.List<String>
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/deleteBatchByPK")
    @ApiOperation("根据主键批量删除")
    public ResponseBean deleteBatchByPk(@RequestBody List<String> ids, HttpServletRequest req) throws Exception{
        int rows = getService().deleteBatchByPK(ids);
        if (rows > 0){
            return ResponseBean.success("批量删除成功");
        }
        return ResponseBean.fail("批量删除失败");
    }

    /**
     * 根据用户定义删除
     * @param entity T
     * @param req javax.servlet.http.HttpServletRequest
     * @return com.whli.autumn.core.share.ResponseBean
     * @throws Exception
     */
    @PostMapping(value = "/delete")
    @ApiOperation("根据用户定义删除")
    public ResponseBean delete(@RequestBody T entity,HttpServletRequest req) throws Exception{
        int rows = getService().delete(entity);
        if (rows > 0){
            return ResponseBean.success("删除成功");
        }
        return ResponseBean.fail("删除失败");
    }
}
