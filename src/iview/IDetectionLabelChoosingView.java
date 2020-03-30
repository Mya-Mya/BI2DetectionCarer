package iview;

import java.util.List;

/**
 * BeautyInlet2が出力した画像と検出情報の組(以下、要検証組)について、
 * どの検出情報の要検証組だけを表示するか決めさせる画面。
 */
public interface IDetectionLabelChoosingView {
    void setLabelTextList(List<String> labelList);
    void gotoInspectingView();
}
