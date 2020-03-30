package model;

import java.io.File;
import java.util.List;

public class InspectionFactory {
    /**
     * @param detectionDir 検出情報が含まれるディレクトリ
     * @param imageDir     検証・未訂正の画像が含まれるディレクトリ
     * @param labelList    扱う検出情報のラベル
     * @return inspection
     */
    public static Inspection createInspection(File detectionDir, File imageDir, List<String> labelList) {
        LabelInfo labelInfo = new LabelInfo();
        for (String label : labelList) {
            labelInfo.addLabel(label);
        }
        ImageDatabase imageDatabase = new ImageDatabase(imageDir);
        DetectionDatabase detectionDatabase = new DetectionDatabase(detectionDir, labelInfo);
        Inspection inspection = new Inspection(detectionDatabase.getDetectionInfoList(), imageDatabase.getImageInfoList());
        return inspection;
    }
}
