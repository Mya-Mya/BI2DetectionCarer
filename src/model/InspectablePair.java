package model;

/**
 * BeautyInlet2が出力した未検証・未訂正の画像と検出情報との組を表す。
 */
public class InspectablePair {
    public final DetectionInfo detectionInfo;
    public final ImageInfo imageInfo;
    public InspectablePair(DetectionInfo detectionInfo,ImageInfo imageInfo){
        this.detectionInfo=detectionInfo;
        this.imageInfo=imageInfo;
    }
}
