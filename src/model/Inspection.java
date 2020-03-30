package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * BeautyInlet2が出力した未検証・未訂正の画像と検出情報のうち、2つとも揃い検証可能な組を生成し、検証する。
 */
public class Inspection {
    private List<ImageInfo> imageInfoList;
    private List<DetectionInfo> detectionInfoList;
    private List<InspectablePair> inspectableList;

    /**
     * @param detectionInfoList 今ある検出情報
     * @param imageInfoList     今ある未検証・未訂正の画像
     */
    public Inspection(List<DetectionInfo> detectionInfoList, List<ImageInfo> imageInfoList) {
        this.detectionInfoList = detectionInfoList;
        this.imageInfoList = imageInfoList;
        inspectableList = new ArrayList<>();

        for (DetectionInfo detectionInfo : detectionInfoList) {
            for (ImageInfo imageInfo : imageInfoList) {
                if (detectionInfo.year == imageInfo.year &&
                        detectionInfo.month == imageInfo.month &&
                        detectionInfo.day == imageInfo.day &&
                        detectionInfo.hour == imageInfo.hour &&
                        detectionInfo.min == imageInfo.min &&
                        detectionInfo.sec == imageInfo.sec
                ) {
                    inspectableList.add(new InspectablePair(detectionInfo, imageInfo));
                }
            }
        }
    }

    public int getNumInspectablePair() {
        return inspectableList.size();
    }

    /**
     * 要検証組を検証した時に呼び出すこと。
     *
     * @param pair 検証した要検証組
     */
    public void onInspected(InspectablePair pair) {
        inspectableList.remove(pair);
    }

    /**
     * 要検証組のうち、特定の検出情報を持つ組のみを取り出す。
     *
     * @param label 検出情報のラベル
     * @return 特定の検出情報を持つ組
     */
    public List<InspectablePair> getInspectableListByDetectionLabel(String label) {
        List<InspectablePair> list = new ArrayList<>();
        for (InspectablePair pair : inspectableList) {
            if (pair.detectionInfo.label.equals(label)) {
                list.add(pair);
            }
        }
        return list;
    }

}
