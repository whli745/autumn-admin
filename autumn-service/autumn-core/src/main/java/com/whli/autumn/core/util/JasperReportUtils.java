package com.whli.autumn.core.util;

import com.whli.autumn.core.enums.FilePathEnum;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporter;
import net.sf.jasperreports.engine.export.JRPrintServiceExporterParameter;
import net.sf.jasperreports.engine.util.JRLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.print.PrintService;
import javax.servlet.http.HttpServletResponse;
import java.awt.print.PrinterJob;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * <p>单据或报告打印</p>
 * @author whli
 * @version 1.0.0
 * @date 2019/8/4 11:06
 */
@Component
public class JasperReportUtils {

    private static final Logger logger = LoggerFactory.getLogger(JasperReportUtils.class);

    public static final String REPORT_PATH = FilePathEnum.TEMPLATE.getPath();

    /**
     * <p>单张打印</p>
     * @param templateName  模板名称
     * @param params  打印参数
     * @param dataSource  打印数据
     * @throws Exception
     */
    public static void report(String templateName, Map<String,Object> params, Collection<?> dataSource,OutputStream outputStream) throws Exception{
        if (params == null || CollectionUtils.isEmpty(dataSource)){
            logger.error("请选择需要打印的数据");
            return;
        }

        JasperPrint jasperPrint = createJasperPrint(templateName,params,dataSource);
        JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
    }

    /**
     * <p>单张打印</p>
     * @param templateName  模板名称
     * @param params  打印参数
     * @param dataSource  打印数据
     * @param response
     * @throws Exception
     */
    public static void report(String templateName, Map<String,Object> params, Collection<?> dataSource,HttpServletResponse response) throws Exception{
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        response.setCharacterEncoding("UTF-8");
        try (OutputStream outputStream = response.getOutputStream()) {
            report(templateName, params, dataSource, outputStream);
        }
    }

    /**
     * <p>批量打印</p>
     * @param templateName 模板名称
     * @param params 打印参数
     * @throws Exception
     */
    public static void reportBatch(String templateName, List<Map<String,Object>> params,OutputStream outputStream) throws Exception{

        if (CollectionUtils.isEmpty(params)){
            logger.error("请选择需要打印的数据");
            return;
        }

        List<JasperPrint> printList = createBatchJasperPrint(templateName,params);
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, printList);
        exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, outputStream);
        exporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "UTF-8");
        exporter.exportReport();
    }

    /**
     * <p>批量打印</p>
     * @param templateName 模板名称
     * @param params 打印参数
     * @param response
     * @throws Exception
     */
    public static void reportBatch(String templateName, List<Map<String,Object>> params,HttpServletResponse response) throws Exception{

        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "inline;");
        response.setCharacterEncoding("UTF-8");
        try (OutputStream outputStream = response.getOutputStream()) {
            reportBatch(templateName,params,outputStream);
        }
    }

    /**
     * 调用打印机打印
     * @param templateName
     * @param params
     * @param dataSource
     * @param printName
     * @throws Exception
     */
    public static void printer(String templateName, Map<String,Object> params, Collection<?> dataSource, String printName) throws Exception{

        if (params == null || CollectionUtils.isEmpty(dataSource)){
            logger.error("请选择需要打印的数据");
            return;
        }

        //获取指定打印机
        PrintService printService = getPrintService(printName);
        if (printService == null){
            logger.error("未找到【"+printName+"】打印机");
            return;
        }

        JasperPrint jasperPrint = createJasperPrint(templateName,params,dataSource);
        JRAbstractExporter je = new JRPrintServiceExporter();
        je.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
        //设置指定打印机
        je.setParameter(JRPrintServiceExporterParameter.PRINT_SERVICE, printService);
        je.setParameter(JRPrintServiceExporterParameter.DISPLAY_PAGE_DIALOG, false);
        je.setParameter(JRPrintServiceExporterParameter.DISPLAY_PRINT_DIALOG, false);
        //打印
        je.exportReport();
    }

    /**
     * 调用打印机批量打印
     * @param templateName
     * @param params
     * @param printName
     * @throws Exception
     */
    public static void printerBatch(String templateName,List<Map<String,Object>> params,String printName) throws Exception{

        if (CollectionUtils.isEmpty(params)){
            logger.error("请选择需要打印的数据");
            return;
        }

        for (Map<String,Object> param : params){
            printer(templateName, param, (Collection<?>) param.get("data"),printName);
        }

    }

    /**
     * 创建一个JasperPrint
     * @param templateName
     * @param params
     * @param dataSource
     * @return
     * @throws Exception
     */
    private static JasperPrint createJasperPrint(String templateName, Map<String,Object> params, Collection<?> dataSource) throws Exception{
        try (InputStream jasperStream = new FileInputStream(REPORT_PATH+templateName)) {

            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataSource);
            JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, beanColDataSource);

            return jasperPrint;

        }
    }

    /**
     * 批量创建JasperPrint
     * @param templateName
     * @param params
     * @return
     * @throws Exception
     */
    private static List<JasperPrint> createBatchJasperPrint(String templateName, List<Map<String,Object>> params) throws Exception{

        List<JasperPrint> printList = new ArrayList<>();

        for (Map<String,Object> map : params){
            try(InputStream jasperStream = new FileInputStream(REPORT_PATH+templateName)){
                JRBeanCollectionDataSource beanColDataSource = null;
                Collection temp = (Collection<?>) map.get("data");
                if (!CollectionUtils.isEmpty(temp)){
                    beanColDataSource = new JRBeanCollectionDataSource(temp);
                }
                JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
                JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, map, beanColDataSource);
                printList.add(jasperPrint);
            }
        }

        return printList;
    }

    /**
     * 选择指定打印机
     * @param printName
     * @return
     */
    private static PrintService getPrintService(String printName){
        //循环服务器本地打印机，查询指定的打印机
        PrintService printService = null;
        PrintService[] printServices = PrinterJob.lookupPrintServices();
        for (PrintService service : printServices){
            if (printName.equalsIgnoreCase(service.getName())){
                printService = service;
            }
        }
        return printService;
    }
}
