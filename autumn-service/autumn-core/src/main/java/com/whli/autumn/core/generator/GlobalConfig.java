package com.whli.autumn.core.generator;

import com.whli.autumn.core.constant.SysConstants;
import lombok.Data;

import java.util.List;

/**
 * <em>代码生成基本类</em>
 * @author whli
 * @version 2018/9/4 9:26
 */
@Data
public class GlobalConfig {

    /**  作者 */
    private String author;
    /** 包名 */
    private String basePackage;
    /** 静态html文件所在文件夹 */
    private String moduleName;
    /** 代码生成输出路径 */
    private String outRootDir = SysConstants.USER_HOME+"/generator/" ;
    /** 数据库用户名 */
    private String username;
    /** 数据库密码 */
    private String password;
    /** 数据库url */
    private String jdbcUrl;
    /** 数据库驱动（默认mysql） */
    private String jdbcDriver;
    /** 忽略表前缀，多个以','分隔(ts_,tm_) */
    private String tablePrefixes;
    /** 需要一键生成代码的表名 */
    private List<String> tableNames;
    /** 是否自动打开生成目录，默认true */
    private boolean open = true;
}
