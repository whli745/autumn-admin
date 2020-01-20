package com.whli.autumn.system.dto;

import com.whli.autumn.model.system.Upload;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p></p>
 *
 * @author blog.whli745.com
 * @version 1.0.0
 * @since 2019/12/5 20:09
 */
@Data
public class UploadDTO extends Upload {

    private MultipartFile file;
}
