package iview;

import model.ImageInfo;
import model.Inspection;

import javax.swing.*;

/**
 * BeautyInlet2が出力した画像と検出情報の組(以下、要検証組)について、
 * ある検出情報ラベルを持った要検証組の画像のみ表示し正当性を検証する画面。
 */
public interface IInspectingView {
    /**
     * この画面ではどの検出情報ラベルを持った要検証組の画像が表示されるのか表示する。
     * @param label この画面で取扱う検出情報ラベル
     */
    void setTargetLabelName(String label);

    /**
     * 新たに画像を表示させる。
     * @param image
     */
    void addImage(ImageInfo image);

    /**
     * 全ての要検証組が検証されたら呼ばれる。
     */
    void onInspectedAllPair();

    void gotoDetectionLabelChoosingView(Inspection inspection);

    void setLabelKeybindMessage(String labelKeybindMessage);

    void removeImage(ImageInfo imageInfo);
}
