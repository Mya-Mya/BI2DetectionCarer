package model;

/**
 * BeautyInlet2が出力した検出情報を表す。
 */
public class DetectionInfo {
    public final int year;
    public final int month;
    public final int day;
    public final int hour;
    public final int min;
    public final int sec;
    public final String label;
    public DetectionInfo(int year,int month,int day,int hour,int min,int sec,String label){
        this.year=year;
        this.month=month;
        this.day=day;
        this.hour=hour;
        this.min=min;
        this.sec=sec;
        this.label=label;
    }
}
