package com.whli.autumn.system.service;

import com.whli.autumn.core.web.service.IBaseService;
import com.whli.autumn.model.system.Upload;
import com.whli.autumn.system.dto.UploadDTO;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>文件上传业务</p>
 * @author blog.whli745.com
 * @version 1.0.0
 * @since 2019-12-05 17:29
 */
public interface IUploadService extends IBaseService<Upload>{

    /**
     * 上传文件
     * @param entity UploadDTO
     * @return java.Lang.int
     */
    @Transactional
    int uploadFile(UploadDTO entity);

    /**
     * 获取指定KEY、TYPE最大版本号的文件
     * @param companyId  java.lang.String 公司ID
     * @param uniqueKey  java.lang.String 文件唯一KEY
     * @param type  java.lang.String 文件类型
     * @return
     */
    Upload getByUnionKeyAndType(String companyId,String uniqueKey, String type);
}
