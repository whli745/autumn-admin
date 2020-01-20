package com.whli.autumn.system.dto;

import com.whli.autumn.model.system.User;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * <p></p>
 *
 * @author whli
 * @version 1.0.0
 * @since 2019/11/19 21:39
 */
@Data
public class UserDTO extends User {

    //查询条件
    private String companyId;

    //授权
    private boolean checked;
    private String urId;
    private String roleId;

    public void setUrId(String urId) {
        this.urId = urId;
        this.checked = StringUtils.isNotBlank(urId);
    }

    //启用禁用用户
    private List<String> ids;
}
