package com.whli.autumn.core.util;

import java.util.Map;

/**
 * Created by whli on 2017/11/22.
 */
public class WordUtils {

    private WordUtils(){}

    /**
     * 使用velocity模板生成word文件
     * @author whli
     * @since 2016-1-22下午05:33:42
     * @param dataMap word中需要展示的动态数据，用map集合来保存
     * @param templateName word模板名称，例如：test.ftl
     * @param outPath 输出文件路径
     */
    @SuppressWarnings({ "deprecation", "rawtypes" })
    public static void createWord(Map dataMap, String templateName, String outPath) throws Exception{
        FreemarkerUtils.writer(dataMap,templateName,outPath);
    }
}
