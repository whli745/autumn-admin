package com.whli.autumn.system.dto;

import com.whli.autumn.model.system.RoleMenu;
import lombok.Data;

import java.util.List;

/**
 * <p></p>
 *
 * @author whli
 * @version 1.0.0
 * @since 2019/11/24 11:21
 */
@Data
public class RoleMenuDTO extends RoleMenu {

    private List<String> menuIds;
}
