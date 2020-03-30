package presenter;

import iview.IDetectionLabelChoosingView;
import model.Inspection;
import model.LabelInfo;

import java.util.List;

public class DetectionLabelChoosingPresenter {
    private IDetectionLabelChoosingView view;
    private Inspection inspection;
    private List<String> labelList;
    public DetectionLabelChoosingPresenter(IDetectionLabelChoosingView view, Inspection inspection, List<String>labelList){
        this.view=view;
        this.inspection=inspection;
        this.labelList=labelList;
    }
}
