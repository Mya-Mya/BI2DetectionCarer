package presenter;

import iview.IDetectionLabelChoosingView;
import model.Inspection;

import java.util.List;

public class DetectionLabelChoosingPresenter {
    private IDetectionLabelChoosingView view;
    private Inspection inspection;
    private List<String> labelList;
    public DetectionLabelChoosingPresenter(IDetectionLabelChoosingView view, Inspection inspection, List<String>labelList){
        this.view=view;
        this.inspection=inspection;
        this.labelList=labelList;

        view.setLabelTextList(labelList);
    }
    public void onDetectionLabelChosen(String label){
        //検出情報ラベルlabelが付随した要検証組を取り出す
        view.gotoInspectingView();
    }

}
