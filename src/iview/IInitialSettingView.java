package iview;

import model.Inspection;

import java.util.List;

/**
 * 最初に起動した際に表示される画面。
 * ここでは未検証・未訂正の画像が含まれているディレクトリと検出情報が含まれているディレクトリを指定する。
 */
public interface IInitialSettingView {
    void setLabelListText(List<String>labelList);
    void setImageDirText(String dir);
    void setDetectionDirText(String dir);
    void gotoDetectionLabelChoosingView(Inspection inspection,List<String>labelList);
}
