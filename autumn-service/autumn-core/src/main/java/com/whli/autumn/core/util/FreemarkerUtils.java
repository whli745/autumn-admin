package com.whli.autumn.core.util;

import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * <p>Freemarker工具类</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/8/11 15:04
 */
public class FreemarkerUtils {

    private static Configuration configuration;

    static{
        configuration = new Configuration(Configuration.getVersion());
        configuration.setDefaultEncoding("UTF-8");
        configuration.setClassForTemplateLoading(FreemarkerUtils.class,"/static/template/");
    }

    public static void writer(Map<String, Object> dataMap,String templateName, String outputFile) throws Exception{
        Template template = configuration.getTemplate(templateName);
        FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFile));
        template.process(dataMap, new OutputStreamWriter(fileOutputStream, Charset.forName("UTF-8").name()));
        fileOutputStream.close();
    }
}
