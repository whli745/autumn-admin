package com.whli.autumn.core.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;
import com.whli.autumn.core.util.XMLUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.dom4j.Element;
import org.springframework.util.StringUtils;

import java.awt.*;
import java.io.File;
import java.net.URL;

/**
 * <b><em>代码一键生成</em></b>
 *
 * @author whli
 * @version 2018/9/2 11:02
 */
public class AutoGenerator {

    /**
     * <b><em>设置代码生成基础参数</em></b>
     * @param gc 全局设置
     */
    private static void setBasicAttribute(GlobalConfig gc) {
        try{
            URL url = AutoGenerator.class.getClassLoader().getResource("generator.xml");
            File currentFile = new File(url.getFile());
            //将XML转换为Doument文档
            XMLUtils.setDocument(currentFile);
            //获取根节点
            Element rootElement = XMLUtils.getRootElement();
            //设置basePackage
            Element author = XMLUtils.getChildElementByAttribute(rootElement, "key", "author");
            if (author != null && !StringUtils.isEmpty(gc.getAuthor())) {
                XMLUtils.updateElement(author, gc.getAuthor());
            }

            //设置basePackage
            Element basePackage = XMLUtils.getChildElementByAttribute(rootElement, "key", "basepackage");
            if (basePackage != null && !StringUtils.isEmpty(gc.getBasePackage())) {
                XMLUtils.updateElement(basePackage, gc.getBasePackage());
            }
            //设置namespace
            Element namespace = XMLUtils.getChildElementByAttribute(rootElement, "key", "moduleName");
            if (namespace != null && !StringUtils.isEmpty(gc.getModuleName())) {
                XMLUtils.updateElement(namespace, gc.getModuleName());
            }
            //设置outRoot
            Element outRooDir = XMLUtils.getChildElementByAttribute(rootElement, "key", "outRoot");
            if (outRooDir != null && !StringUtils.isEmpty(gc.getOutRootDir())) {
                XMLUtils.updateElement(outRooDir, gc.getOutRootDir());
            }
            //设置username
            Element username = XMLUtils.getChildElementByAttribute(rootElement, "key", "jdbc_username");
            if (username != null && !StringUtils.isEmpty(gc.getUsername())) {
                XMLUtils.updateElement(username, gc.getUsername());
            }
            //设置password
            Element password = XMLUtils.getChildElementByAttribute(rootElement, "key", "jdbc_password");
            if (password != null && !StringUtils.isEmpty(gc.getPassword())) {
                XMLUtils.updateElement(password, gc.getPassword());
            }
            //设置data_url
            Element jdbcUrl = XMLUtils.getChildElementByAttribute(rootElement, "key", "jdbc_url");
            if (jdbcUrl != null && !StringUtils.isEmpty(gc.getJdbcUrl())) {
                XMLUtils.updateElement(jdbcUrl, gc.getJdbcUrl());
            }
            //设置data_driver
            Element jdbcDriver = XMLUtils.getChildElementByAttribute(rootElement, "key", "jdbc_driver");
            if (jdbcDriver != null && !StringUtils.isEmpty(gc.getJdbcDriver())) {
                XMLUtils.updateElement(jdbcDriver, gc.getJdbcDriver());
            }
            //设置tableRemovePrefixes
            Element prefixes = XMLUtils.getChildElementByAttribute(rootElement, "key", "tableRemovePrefixes");
            if (prefixes != null && !StringUtils.isEmpty(gc.getTablePrefixes())) {
                XMLUtils.updateElement(prefixes, gc.getTablePrefixes());
            }
            //保存XML文件
            XMLUtils.saveDocument(currentFile);
        } catch(Exception e){
            e.printStackTrace();
        }

    }

    /**
     * <b><em>一键生成代码</em></b>
     *
     * @param all 是否整个数据库生成代码
     * @param gc  单表生成代码
     */
    public static void generator(boolean all, GlobalConfig gc) {
        try {
            setBasicAttribute(gc);

            GeneratorFacade g = new GeneratorFacade();
            g.getGenerator().addTemplateRootDir("classpath:template");
            if (all) {
                g.generateByTable("*");
            } else {
                if (CollectionUtils.isNotEmpty(gc.getTableNames())) {
                    for (String tableName : gc.getTableNames()) {
                        g.generateByTable(tableName);
                    }
                }
            }
            if (gc.isOpen()){
                //打开所在文件
                Desktop.getDesktop().open(new File(gc.getOutRootDir()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
