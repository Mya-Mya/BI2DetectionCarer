package view;

import iview.IInspectingView;
import model.ImageInfo;
import model.Inspection;
import presenter.InspectingPresenter;
import ui.ColorFactory;
import ui.CompFactory;
import ui.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;
import java.util.Map;

public class InspectingView extends JPanel implements IInspectingView, KeyListener, ActionListener {
    private InspectingPresenter presenter;
    private JLabel cTargetLabelNameLabel;//要らないかも？
    private JLabel cLabelKeybindMessageLabel;
    private JPanel cImageViewPanel;
    private Map<ImageInfo,JLabel>imageViewInfo;
    private JButton cGoBackButton;
    private MediaTracker tracker;

    /**
     * @param inspection  Inspectionオブジェクト
     * @param targetLabel と付随した要検証組の画像のみ表示する
     */
    public InspectingView(Inspection inspection, String targetLabel) {
        super();
        setBackground(ColorFactory.back);
        setLayout(new BorderLayout());

        cTargetLabelNameLabel = CompFactory.label();
        cTargetLabelNameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        cTargetLabelNameLabel.setFont(FontFactory.large);
        //add(cTargetLabelNameLabel, BorderLayout.NORTH);

        cLabelKeybindMessageLabel = CompFactory.label();
        cLabelKeybindMessageLabel.setHorizontalAlignment(SwingConstants.LEFT);
        cLabelKeybindMessageLabel.setFont(FontFactory.large);
        add(cLabelKeybindMessageLabel, BorderLayout.NORTH);

        cImageViewPanel = CompFactory.panel();
        cImageViewPanel.setLayout(new FlowLayout(FlowLayout.LEFT,40,40));
        add(cImageViewPanel,BorderLayout.CENTER);

        cGoBackButton = CompFactory.button();
        cGoBackButton.setText("戻る");
        cGoBackButton.setActionCommand("GOBACK");
        cGoBackButton.addActionListener(this);
        add(cGoBackButton, BorderLayout.SOUTH);

        imageViewInfo=new HashMap<>();
        tracker=new MediaTracker(this);
        setVisible(true);
        presenter = new InspectingPresenter(this, inspection, targetLabel);
    }

    @Override
    public void setTargetLabelName(String label) {
        cTargetLabelNameLabel.setText(label);
    }

    @Override
    public void setLabelKeybindMessage(String message) {
        cLabelKeybindMessageLabel.setText(message);
    }

    @Override
    public void addImage(ImageInfo image) {
        Image scaledImage=image.image.getImage().getScaledInstance(300,-1,Image.SCALE_SMOOTH);
        tracker.addImage(scaledImage,image.file.hashCode());
        JLabel component=new JLabel(new ImageIcon(scaledImage));
        cImageViewPanel.add(component);
        imageViewInfo.put(image,component);
    }

    @Override
    public void removeImage(ImageInfo image) {
        JLabel removingTarget=imageViewInfo.get(image);
        cImageViewPanel.remove(removingTarget);
        cImageViewPanel.validate();
    }

    @Override
    public void onInspectedAllPair() {
        cLabelKeybindMessageLabel.setText("仕訳は全て完了した");
        cTargetLabelNameLabel.setText("仕分けは全て完了した");
    }

    @Override
    public void gotoDetectionLabelChoosingView(Inspection inspection) {
        MasterView.inst.changeScene(
                new DetectionLabelChoosingView(inspection)
        );
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

        presenter.onKeyTyped(keyEvent.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        presenter.onGoBackButtonPushed();
    }
}
