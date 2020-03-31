package model;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * BeautyInlet2が出力した検出情報を読み込む。
 */

public class DetectionDatabase {
    private List<DetectionInfo> detectionInfoList;
    private Set<String> detectionLabelSet;

    /**
     * @param detectionDir 検出情報が含まれるcsvファイルが入っているディレクトリ
     */
    public DetectionDatabase(File detectionDir) {
        detectionInfoList = new ArrayList<>();
        detectionLabelSet = new HashSet<>();
        File[] fileList = detectionDir.listFiles();
        if (fileList == null) return;
        for (File f : fileList) {
            if (!f.getName().matches("[0-9]{4}_[0-9]{2}.csv")) continue;
            readDetectionFile(f);
        }
    }

    /**
     * 指定の検出情報が記入されたcsvファイルを開き保存する。
     *
     * @param detectionFile csvファイル
     */
    private void readDetectionFile(File detectionFile) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new InputStreamReader(new FileInputStream(detectionFile)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return;
        }

        String line;
        boolean head = true;
        try {
            while ((line = reader.readLine()) != null) {
                if (head) {
                    head = false;
                    continue;
                }
                String[] splitted = line.split(",");
                String label = splitted[6];
                detectionLabelSet.add(label);
                DetectionInfo info = new DetectionInfo(
                        Integer.parseInt(splitted[0]),
                        Integer.parseInt(splitted[1]),
                        Integer.parseInt(splitted[2]),
                        Integer.parseInt(splitted[3]),
                        Integer.parseInt(splitted[4]),
                        Integer.parseInt(splitted[5]),
                        label
                );

                detectionInfoList.add(info);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<DetectionInfo> getDetectionInfoList() {
        return detectionInfoList;
    }

    /**
     * 検出情報のラベルの集合を返す。
     * @return 検出情報のラベルの集合
     */
    public Set<String> getDetectionLabelSet() {
        return detectionLabelSet;
    }
}
