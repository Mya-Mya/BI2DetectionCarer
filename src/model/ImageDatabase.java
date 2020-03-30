package model;

import javax.swing.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * BeautyInlet2が出力した未検証・未訂正の画像を読み込む。
 */
public class ImageDatabase {
    private List<ImageInfo> imageInfoList;

    /**
     * @param imageDir 未検証・未訂正の画像が含まれるcsvファイルが入っているディレクトリ
     */
    public ImageDatabase(File imageDir) {
        imageInfoList = new ArrayList<>();
        File[] fileList = imageDir.listFiles();
        if (fileList == null) return;
        for (File f : fileList) {
            if (!f.getName().matches(
                    "[0-9]{4}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}.jpg"
            )) continue;
            addImage(f);
        }
    }

    private void addImage(File imageFile) {
        ImageIcon image = new ImageIcon(imageFile.getAbsolutePath());
        String[] splitted = imageFile.getName().split("(_|\\.)");
        ImageInfo info = new ImageInfo(
                Integer.parseInt(splitted[0]),
                Integer.parseInt(splitted[1]),
                Integer.parseInt(splitted[2]),
                Integer.parseInt(splitted[3]),
                Integer.parseInt(splitted[4]),
                Integer.parseInt(splitted[5]),
                imageFile,
                image
        );
        imageInfoList.add(info);
    }

    public List<ImageInfo> getImageInfoList() {
        return imageInfoList;
    }
}
