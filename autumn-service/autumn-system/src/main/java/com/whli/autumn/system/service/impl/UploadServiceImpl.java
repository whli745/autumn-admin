package com.whli.autumn.system.service.impl;

import com.whli.autumn.core.constant.SysConstants;
import com.whli.autumn.core.enums.FilePathEnum;
import com.whli.autumn.core.enums.FileTypeEnum;
import com.whli.autumn.core.exception.BusinessException;
import com.whli.autumn.core.util.DateUtils;
import com.whli.autumn.core.util.WebUtils;
import com.whli.autumn.core.web.dao.IBaseDao;
import com.whli.autumn.core.web.service.impl.BaseServiceImpl;
import com.whli.autumn.model.system.Upload;
import com.whli.autumn.system.dao.IUploadDao;
import com.whli.autumn.system.dto.UploadDTO;
import com.whli.autumn.system.service.IUploadService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Date;

/**
 * <p>文件上传业务实现</p>
 * @author blog.whli745.com
 * @version 1.0.0
 * @since 2019-12-05 17:29
 */
@Service("uploadService")
public class UploadServiceImpl extends BaseServiceImpl<Upload> implements IUploadService {

	@Autowired
	private IUploadDao uploadDao;

	@Override
	public IBaseDao<Upload> getDao() {
		return uploadDao;
	}

	@Override
	public int uploadFile(UploadDTO entity) {
		MultipartFile uploadFile = entity.getFile();
		if (uploadFile == null || uploadFile.isEmpty()) {
			throw new BusinessException( "请选择上传文件");
		}

		try {
			String originalFilename = uploadFile.getOriginalFilename();
			String fileSuffix= originalFilename.substring(originalFilename.lastIndexOf("."));

			//依据文件类型，赋予相应的路径，默认other
			String tempFilePath = FilePathEnum.OTHER.getPath();

			if (FileTypeEnum.WORD.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.WORD.getPath();

			}else if (FileTypeEnum.EXCEL.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.EXCEL.getPath();

			}else if (FileTypeEnum.PPT.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.PPT.getPath();

			}else if (FileTypeEnum.PDF.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.PDF.getPath();

			}else if (FileTypeEnum.JPEG.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.JPEG.getPath();

			}else if (FileTypeEnum.PNG.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.PNG.getPath();

			}else if (FileTypeEnum.GIF.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.GIF.getPath();

			}else if (FileTypeEnum.TEMPLATE.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.TEMPLATE.getPath();

			}else if (FileTypeEnum.APP.getType().equals(entity.getType())){

				tempFilePath = FilePathEnum.APP.getPath();

			}

			//记录上传日志
			Upload temp = new Upload();
			BeanUtils.copyProperties(entity, temp);

			if (StringUtils.isEmpty(temp.getVersion())){
				temp.setVersion("1");
				Upload oldFile = uploadDao.getMaxVersionByCompanyAndUniqueKeyAndType(entity.getCompanyId(),entity.getUniqueKey(),entity.getType());
				if (oldFile != null) {
					temp.setVersion((Integer.parseInt(oldFile.getVersion()) + 1)+"");
				}
			}

			String filename = entity.getUniqueKey()+"_"+ DateUtils.dateToString(new Date(),
					DateUtils.DEFAULT_NOT_SEPARATE_YYYY_MM_DD_HH_MM_SS) + fileSuffix;
			temp.setName(originalFilename.substring(0,originalFilename.lastIndexOf(".")));
			temp.setUrl("/upload/"+ tempFilePath + "/" + filename);
			this.save(temp);

			String dirPath = SysConstants.USER_HOME+tempFilePath;
			File dir = new File(dirPath);
			if (!dir.exists()) {
				dir.mkdirs();
			}
			File tempFile = new File(dirPath + "/" + filename);
			uploadFile.transferTo(tempFile);
		} catch (IOException e) {
			throw new BusinessException("上传文件失败：" + e.getMessage());
		}
		return 1;
	}

	@Override
	public Upload getByUnionKeyAndType(String companyId,String uniqueKey, String type) {
		return uploadDao.getMaxVersionByCompanyAndUniqueKeyAndType(companyId,uniqueKey,type);
	}
}
