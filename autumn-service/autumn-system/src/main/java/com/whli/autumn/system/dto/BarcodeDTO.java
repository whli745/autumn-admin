package com.whli.autumn.system.dto;

import com.whli.autumn.model.system.Barcode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>描述</p>
 *
 * @author blog.whli745.com
 * @version 1.0.0
 * @since 2019/12/21 18:29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
public class BarcodeDTO extends Barcode {
    private String sequenceName;
}
