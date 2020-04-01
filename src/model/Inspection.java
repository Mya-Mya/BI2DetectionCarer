package model;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * BeautyInlet2が出力した未検証・未訂正の画像と検出情報のうち、2つとも揃い検証可能な組を生成し、検証する。
 */
public class Inspection {
    private List<ImageInfo> imageInfoList;
    private List<DetectionInfo> detectionInfoList;
    private Set<String> detectionLabelSet;
    private List<InspectablePair> inspectableList;
    private int correctCount = 0;
    private int incorrectCount = 0;

    /**
     * @param detectionInfoList 今ある検出情報
     * @param imageInfoList     今ある未検証・未訂正の画像
     */
    public Inspection(List<DetectionInfo> detectionInfoList, List<ImageInfo> imageInfoList, Set<String> detectionLabelSet) {
        this.detectionInfoList = detectionInfoList;
        this.imageInfoList = imageInfoList;
        this.detectionLabelSet = detectionLabelSet;
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
     * 要検証組を検証したことを確認し、画像をそのラベル名のフォルダに移動する。
     *
     * @param pair  検証した要検証組
     * @param label 検証後のラベル
     */
    public void onInspected(InspectablePair pair, String label) {
        if (pair.detectionInfo.label.equals(label)) {
            correctCount++;
        } else {
            incorrectCount++;
        }
        inspectableList.remove(pair);
        File imageFile = pair.imageInfo.file;
        Path from = imageFile.toPath();
        Path to = Paths.get(
                imageFile.getParentFile().getAbsolutePath(),
                label,
                imageFile.getName()
        );
        try {
            Files.move(from, to);
        } catch (IOException e) {
            e.printStackTrace();

        }
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

    /**
     * 検出情報のラベルの集合を返す。
     *
     * @return 検出情報のラベルの集合
     */
    public Set<String> getDetectionLabelSet() {
        return detectionLabelSet;
    }

    /**
     * 検証された要検証組のうちBeautyInlet2が出力した検出情報が正しかった事例数を返す。
     */
    public int getCorrectCount() {
        return correctCount;
    }

    /**
     * 検証された要検証組のうちBeautyInlet2が出力した検出情報が誤っていた事例数を返す。
     */
    public int getIncorrectCount() {
        return incorrectCount;
    }

    /**
     * 検証された要検証組のうちBeautyInlet2が出力した検出情報が正しかった割合を返す。
     */
    public double getCorrectRatio() {
        double ratio = ((double) correctCount) / ((double) (correctCount + incorrectCount));
        return ratio;
    }
}
