package com.whli.autumn.system.dto;

import com.whli.autumn.model.system.Menu;
import lombok.Data;

import java.util.List;

/**
 * <p></p>
 * @author whli
 * @version 1.0.0
 * @since 2019/10/18 16:44
 */
@Data
public class MenuDTO extends Menu {

    private String userId;
    private String companyId;
    private String type;
    private String superAdmin;


    private List<Menu> children;

}
