package presenter;

import iview.IInitialSettingView;

import java.io.File;

public class InitialSettingPresenter {
    private IInitialSettingView view;
    public InitialSettingPresenter(IInitialSettingView view){
        this.view=view;
    }
    public void onImageDirChosen(File imageDir){

    }
    public void onDetectionDirChosen(File detectionDir){

    }
}
