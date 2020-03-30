package model;

import javax.swing.*;
import java.io.File;

/**
 * BeautyInlet2が出力した未検証・未訂正の画像をいくつかの情報と共に示す。
 */
public class ImageInfo {
    public final int year;
    public final int month;
    public final int day;
    public final int hour;
    public final int min;
    public final int sec;
    public final File file;
    public final ImageIcon image;

    public ImageInfo(int year, int month, int day, int hour, int min, int sec, File file, ImageIcon image) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.min = min;
        this.sec = sec;
        this.file = file;
        this.image = image;
    }
}
