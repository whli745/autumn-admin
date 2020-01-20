package com.whli.autumn.core.util;

import com.whli.autumn.core.exception.BusinessException;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;

/**
 * <p>导入导出Excel</p>
 * @author  whli
 * @since  2019/1/7 9:05
 */
public class ExcelUtils {
    private static Logger logger = LoggerFactory.getLogger(ExcelUtils.class);
    /**
     *  //2003- 版本的excel
     */
    private final static String EXCEL2003L = ".xls";
    private final static String EXCEL2007U = ".xlsx";

    private ExcelUtils(){}

    /**
     * 导出Excel
     * @param response
     * @param excel
     */
    public static void exportExcel(HttpServletResponse response,BaseExcel excel) throws Exception{
        List<Map<String,Object>> dataMaps = excel.datas();
        if (CollectionUtils.isEmpty(dataMaps)){
            throw new BusinessException("不存在需要导出的数据！");
        }
        //创建Excel
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet();
        //创建Excel表头
        LinkedHashMap<String,String> headers = excel.headers();
        HSSFRow headRow = sheet.createRow(0);
        //遍历表头
        Iterator<Map.Entry<String, String>> it = headers.entrySet().iterator();
        List<String> keys = new ArrayList<String>();
        int cells = 0;
        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();
            keys.add(entry.getKey());
            //创建列标题
            HSSFCell headCell = headRow.createCell(cells);
            headCell.setCellValue(entry.getValue());
            sheet.setColumnWidth(cells,entry.getValue().length()*512);
            cells++;
        }

        //创建Excel数据
        int dataSize = dataMaps.size();
        for (int i = 0; i < dataSize; i++){
            HSSFRow dataRow = sheet.createRow(i+1);
            Map<String,Object> data = dataMaps.get(i);

            int keySize = keys.size();
            for (int j = 0; j < keySize; j++){
                //获取整列的宽
                int cellWidth = sheet.getColumnWidth(j);
                //创建行的新列
                HSSFCell dataCell = dataRow.createCell(j);

                Object object = data.get(keys.get(j));
                if (object == null){
                    continue;
                }

                if (object instanceof Date){
                    String date = DateUtils.dateToString((Date)object,DateUtils.DEFAULT);
                    dataCell.setCellValue(date);
                    if ((date.length()*512) > cellWidth){
                        sheet.setColumnWidth(j,date.length()*512);
                    }

                }else {
                    String str = object+"";
                    dataCell.setCellValue(str);
                    if ((str.length()*512) > cellWidth){
                        sheet.setColumnWidth(j,str.length()*512);
                    }
                }

            }
        }

        OutputStream outputStream = null;
        response.reset();
        response.setContentType("application/octet-stream;charset=utf-8");
        response.setHeader("Content-disposition", "attachment;filename="
                +new String(excel.fileName().getBytes(),"iso-8859-1")+".xls");
        outputStream = response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


    /**
     * 导入Excel
     * @param filePath
     * @param c
     * @param headers
     * @return
     */
    public static <T> List<T> importExcel(String filePath, Class<T> c, String[] headers) throws Exception{
        if (StringUtils.isBlank(filePath)){
            throw new BusinessException("文件不存在或该文件已被删除！");
        }

        return importExcel(new File(filePath), c, headers);
    }

    /**
     * 导入Excel
     * @param file
     * @param c
     * @param headers
     * @return
     */
    public static <T> List<T> importExcel(File file, Class<T> c, String[] headers) throws Exception{

        if (file == null){
            throw new BusinessException("文件不存在或该文件已被删除！");
        }

        String fileSuffix = file.getName().substring(file.getName().lastIndexOf("."));

        if (!EXCEL2003L.equals(fileSuffix)){
            throw new BusinessException("导入的文件格式有误！");
        }

       return importExcel(new FileInputStream(file),c,headers);
    }

    /**
     * 导入Excel
     * @param stream
     * @param c
     * @param headers
     * @return
     */
    public static <T> List<T> importExcel(InputStream stream, Class<T> c, String[] headers) throws Exception{

        if (stream == null){
            throw new BusinessException("未找到需要导入的文件！");
        }

        Workbook workbook = new HSSFWorkbook(stream);

        if (workbook == null || headers == null || headers.length == 0){
            throw new BusinessException("未找到需要导入的文件！");
        }
        int headLength = headers.length;

        //获取类字段
        Field[] fields = c.getDeclaredFields();
        Map<String,Field> fieldMaps = new HashMap<String,Field>();
        for (Field field : fields) {
            fieldMaps.put(field.getName(),field);
        }

        List<T> objects = new ArrayList<T>();

        int sheetNums = workbook.getNumberOfSheets();
        //读取Excel
        for (int i = 0; i < sheetNums; i++) {
            Sheet sheet = workbook.getSheetAt(i);
            if (sheet == null) {
                continue;
            }
            int firstRowNum = sheet.getFirstRowNum();
            int lastRowNum = sheet.getLastRowNum();
            // 循环读取行
            for (int j = firstRowNum + 1; j <= lastRowNum; j++) {
                Row row = sheet.getRow(j);
                if (row == null){
                    continue;
                }
                T entity = c.newInstance();
                //循环读取列
                for (int m = 0; m < headLength; m++){
                    Cell cell = row.getCell(m);
                    String key = headers[m];
                    Field field = fieldMaps.get(key);
                    if (cell == null || field == null){
                        continue;
                    }
                    String methodName = "set" + key.substring(0, 1).toUpperCase() + key.substring(1);
                    Class cc = field.getType();
                    Method method = c.getMethod(methodName, cc);
                    method.invoke(entity, parseValue(cell,cc) );
                }
                objects.add(entity);
            }
        }
        return objects;
    }

    private static Object parseValue(Cell cell, Class c){

        String s = String.valueOf(cell);
        if(StringUtils.isBlank(s)){
            return null;
        }

        if (s.endsWith(".0")) {
            s = s.substring(0, s.length() - 2);
        }

        Object obj = null;
        String className = c.getName();

        switch (className){
            case "java.lang.Short":
                obj = obj = Short.valueOf(s);
                break;

            case "short":
                obj = Short.parseShort(s);
                break;

            case "java.lang.Integer":
                obj = Integer.valueOf(s);
                break;

            case "int":
                obj = Integer.parseInt(s);
                break;

            case "java.lang.Double":
                obj = Double.parseDouble(s);
                break;

            case "double":
                obj = Double.parseDouble(s);
                break;

            case "java.lang.Float":
                obj = Float.parseFloat(s);
                break;

            case "float":
                obj = Float.parseFloat(s);
                break;

            case "java.lang.Long":
                obj = Long.valueOf(s);
                break;

            case "long":
                obj = Long.parseLong(s);
                break;

            case "java.lang.Boolean":
                obj = Boolean.valueOf(s);
                break;

            case "boolean":
                obj = Boolean.parseBoolean(s);
                break;

            case "java.util.Date":
                obj = DateUtil.getJavaDate(cell.getNumericCellValue());
                break;

            case "java.math.BigDecimal":
                obj = new BigDecimal(s);
                break;

            case "java.math.BigInteger":
                obj = new BigInteger(s);
                break;

            default:
                obj= s;
                break;
        }

        return obj;
    }

}



