package com.whli.autumn.core.enums;

/**
 * <p></p>
 *
 * @author whli
 * @version 1.0.0
 * @since 19-10-19 上午6:29
 */
public enum FileTypeEnum {
    WORD("1"),
    EXCEL("2"),
    PPT("3"),
    PDF("4"),
    JPEG("5"),
    PNG("6"),
    GIF("7"),
    TEMPLATE("8"),
    APP("9"),
    OTHER("10");

    private final String type;

    private FileTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
