package presenter;

import iview.IInspectingView;
import model.ImageInfo;
import model.InspectablePair;
import model.Inspection;

import java.util.*;

public class InspectingPresenter {
    private IInspectingView view;
    private String targetLabel;
    private Inspection inspection;
    private List<InspectablePair> inspectablePairList;

    private static final List<Character> SMALL_ALPHABET_LIST =
            Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l');
    private char selectingAlphabet = SMALL_ALPHABET_LIST.get(0);
    private Map<Character, String> labelKeybind;
    private String labelKeybindMessage;

    public InspectingPresenter(IInspectingView view, Inspection inspection, String targetLabel) {
        this.view = view;
        this.inspection = inspection;
        this.targetLabel = targetLabel;

        inspectablePairList = inspection.getInspectableListByDetectionLabel(targetLabel);

        labelKeybind = new HashMap<>();
        List<String> labelList = new ArrayList<>(inspection.getDetectionLabelSet());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < SMALL_ALPHABET_LIST.size() && i < labelList.size(); i++) {
            labelKeybind.put(SMALL_ALPHABET_LIST.get(i), labelList.get(i));
            sb.append(SMALL_ALPHABET_LIST.get(i));
            sb.append(" -> ");
            sb.append(labelList.get(i));
            sb.append(" ");
        }
        labelKeybindMessage = sb.toString();
        view.setLabelKeybindMessage(labelKeybindMessage);
        view.setTargetLabelName(targetLabel);

        for (InspectablePair pair : inspectablePairList) {
            view.addImage(pair.imageInfo);
        }
    }

    public void onKeyTyped(char keyChar) {
        System.out.println("InspectingPresenter.onKeyTyped");
        if (SMALL_ALPHABET_LIST.contains(keyChar)) {//検出情報のラベルと紐付いているキーが押された場合
            selectingAlphabet = keyChar;
            view.setLabelKeybindMessage(labelKeybindMessage + " | > " + keyChar);
        } else if (String.valueOf(keyChar).matches("[0-9]{1}")) {//数字キーが押された場合
            int num = Integer.parseInt(String.valueOf(keyChar));
            String label = labelKeybind.get(selectingAlphabet);
            for (int i = 0; i < num; i++) {
                inspection.onInspected(inspectablePairList.get(0), label);
                view.removeImage(inspectablePairList.get(0).imageInfo);
                inspectablePairList.remove(0);
            }

        }
    }

    public void onGoBackButtonPushed() {
        view.gotoDetectionLabelChoosingView(inspection);
    }
}
