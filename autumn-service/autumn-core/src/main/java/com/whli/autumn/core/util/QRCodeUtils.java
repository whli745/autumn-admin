package com.whli.autumn.core.util;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>二维码生成</p>
 * @author  whli
 * @version 1.0.0
 * @since  2019/8/4 15:41
 */
public class QRCodeUtils {

    private QRCodeUtils(){}

    /**
     * 生成PNG图片二维码并写入指定的文件
     * @param text
     * @param width
     * @param height
     * @param filePath
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQRCode(String text, int width, int height, String filePath) throws WriterException, IOException {
        //设置图片的文字编码以及内边框
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  //编码
        hints.put(EncodeHintType.MARGIN, 0);  //边框距

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height,hints);
        Path path = FileSystems.getDefault().getPath(filePath);
        MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);

    }

    /**
     * 生成PNG图片二维码并插入输出流
     * @param text
     * @param width
     * @param height
     * @param stream
     * @throws WriterException
     * @throws IOException
     */
    public static void generateQRCode(String text, int width, int height,OutputStream stream) throws WriterException, IOException {
        //设置图片的文字编码以及内边框
        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");  //编码
        hints.put(EncodeHintType.MARGIN, 0);  //边框距

        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height,hints);
        MatrixToImageWriter.writeToStream(bitMatrix, "PNG", stream);
    }

    /**
     * 生成PNG图片二维码并转换为字节数组
     * @param text
     * @param width
     * @param height
     * @return
     * @throws WriterException
     * @throws IOException
     */
    public static byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException {
        ByteArrayOutputStream pngOutputStream = new ByteArrayOutputStream();
        generateQRCode(text,width,height,pngOutputStream);
        return pngOutputStream.toByteArray();
    }
}
