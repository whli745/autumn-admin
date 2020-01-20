package com.whli.autumn.core.enums;

/**
 * <p></p>
 *
 * @author whli
 * @version 1.0.0
 * @since 19-10-19 上午6:29
 */
public enum FilePathEnum {
    WORD("word"),
    EXCEL("excel"),
    PPT("ppt"),
    PDF("pdf"),
    JPEG("image/jpg"),
    PNG("image/png"),
    GIF("image/gif"),
    TEMPLATE("template"),
    APP("app"),
    OTHER("other");

    private final String path;

    private FilePathEnum(String path){
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
