package presenter;

import iview.IInitialSettingView;
import model.Inspection;
import model.InspectionFactory;

import java.io.File;
import java.util.Arrays;
import java.util.List;

public class InitialSettingPresenter {
    private IInitialSettingView view;

    private File imageDir;
    private File detectionDir;

    public InitialSettingPresenter(IInitialSettingView view) {
        this.view = view;
        view.setImageDirText("未設定", "未設定");
        view.setDetectionDirText("未設定", "未設定");
    }

    public void onImageDirChosen(File imageDir) {
        this.imageDir = imageDir;
        view.setImageDirText(imageDir.getName(), imageDir.getAbsolutePath());
    }

    public void onDetectionDirChosen(File detectionDir) {
        this.detectionDir = detectionDir;
        view.setDetectionDirText(detectionDir.getName(), detectionDir.getAbsolutePath());
    }

    public void onOKButtonPushed() {
        Inspection inspection = InspectionFactory.createInspection(detectionDir, imageDir);
        view.gotoDetectionLabelChoosingView(inspection);
    }
}
