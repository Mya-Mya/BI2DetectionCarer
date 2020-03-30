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
    private List<String >labelList;

    public InitialSettingPresenter(IInitialSettingView view){
        this.view=view;
        labelList= Arrays.asList("Idunno","Notseen","Seen");
        view.setLabelListText(labelList);
        view.setImageDirText("未設定");
        view.setDetectionDirText("未設定");
    }
    public void onImageDirChosen(File imageDir){
        this.imageDir=imageDir;
        view.setImageDirText(imageDir.getAbsolutePath());
    }
    public void onDetectionDirChosen(File detectionDir){
        this.detectionDir=detectionDir;
        view.setDetectionDirText(detectionDir.getAbsolutePath());
    }
    public void onOKButtonPushed(){
        Inspection inspection= InspectionFactory.createInspection(
                detectionDir,imageDir,labelList
        );
        view.gotoDetectionLabelChoosingView(inspection,labelList);
    }
}
