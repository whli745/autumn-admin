package com.whli.autumn.system.controller;

import com.whli.autumn.core.share.ResponseBean;
import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.core.web.controller.BaseController;
import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Upload;
import com.whli.autumn.system.dto.UploadDTO;
import com.whli.autumn.system.service.IUploadService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>文件上传API</p>
 * @author blog.whli745.com
 * @version 1.0.0
 * @since 2019-12-05 17:29
 */
@RestController
@RequestMapping(value="/system/upload")
@Api(description = "文件上传API")
public class UploadController extends BaseController<Upload>{

	@Autowired
	private IUploadService uploadService;
	
	@Override
    public IBaseService<Upload> getService() {
        return uploadService;
    }

    /**
     * 上传文件
     * @param entity com.whli.autumn.system.dto.UploadDTO
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/uploadFile")
    @ApiOperation("上传文件")
    public ResponseBean uploadFile(UploadDTO entity, HttpServletRequest request) throws Exception {
        entity.setCompanyId(WebUtils.getCurrentCompanyId());
        int rows = uploadService.uploadFile(entity);
        if (rows > 0) {
            return ResponseBean.success("上传文件成功");
        }
        return ResponseBean.fail("上传文件失败");
    }

    /**
     * 获取指定KEY、TYPE最大版本号的文件
     * @param entity com.whli.autumn.model.system.Upload
     * @param request javax.servlet.http.HttpServletRequest
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getByUnionKeyAndType")
    @ApiOperation("获取指定KEY、TYPE最大版本号的文件")
    public ResponseBean getByUnionKeyAndType(@RequestBody Upload entity, HttpServletRequest request) throws Exception {
        Upload result = uploadService.getByUnionKeyAndType(WebUtils.getCurrentCompanyId(),entity.getUniqueKey(), entity.getType());
        return ResponseBean.success(result);
    }
}

