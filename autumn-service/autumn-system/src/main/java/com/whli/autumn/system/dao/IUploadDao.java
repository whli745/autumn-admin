package com.whli.autumn.system.dao;

import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.model.system.Upload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>文件上传数据操作</p>
 * @author blog.whli745.com
 * @version 1.0.0
 * @since 2019-12-05 17:29
 */
@Mapper
public interface IUploadDao extends IBaseDao<Upload>{

    /**
     * 依据公司、标识、类型查询最大版本号的文件
     * @param companyId java.lang.String 公司ID
     * @param uniqueKey java.lang.String 文件标识
     * @param type java.lang.String 文件类型
     * @return com.whli.autumn.model.system.Upload
     */
    Upload getMaxVersionByCompanyAndUniqueKeyAndType(@Param("companyId") String companyId,@Param("uniqueKey") String uniqueKey,@Param("type") String type);

}
