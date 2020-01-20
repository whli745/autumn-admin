package com.whli.autumn.core.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.oned.Code128Writer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>条形码生成</p>
 * @author  whli
 * @version 1.0.0
 * @since  2019/8/4 15:41
 */
public class BarCodeUtils {

    private BarCodeUtils(){}

    /**
     * 生成PNG图片条形码并写入指定的文件
     * @param text  条码内容
     * @param width  条码宽
     * @param height  条码高
     * @param filePath  输出文件
     * @throws WriterException
     * @throws IOException
     */
    public static void generateBarCode(String text, int width, int height, String filePath) throws WriterException, IOException {
        //设置图片的文字编码以及内边框
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  //编码
        hints.put(EncodeHintType.MARGIN, 0);  //边框距

        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height,hints);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    /**
     * 生成PNG图片条形码并插入输出流
     * @param text  条形码内容
     * @param width  条形码宽
     * @param height  条形码高
     * @param stream  输出流
     * @throws WriterException
     * @throws IOException
     */
    public static void generateBarCode(String text, int width, int height,OutputStream stream) throws WriterException, IOException {
        //设置图片的文字编码以及内边框
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  //编码
        hints.put(EncodeHintType.MARGIN, 0);  //边框距

        Code128Writer writer = new Code128Writer();
        BitMatrix bitMatrix = writer.encode(text, BarcodeFormat.CODE_128, width, height,hints);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
    }

    /**
     * 生成PNG图片条形码并转换为字节数组
     * @param text 条形码内容
     * @param width  条形码宽
     * @param height  条形码高
     * @return byte[] 返回字节数组
     * @throws WriterException
     * @throws IOException
     */
    public static byte[] generateBarCode(String text, int width, int height) throws WriterException, IOException {
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        generateBarCode(text,width,height,pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}
