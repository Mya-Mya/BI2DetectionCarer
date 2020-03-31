package view;

import iview.IDetectionLabelChoosingView;
import model.Inspection;
import presenter.DetectionLabelChoosingPresenter;
import ui.ColorFactory;
import ui.CompFactory;
import ui.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class DetectionLabelChoosingView extends JPanel implements IDetectionLabelChoosingView, ActionListener {
    private DetectionLabelChoosingPresenter presenter;
    private Inspection inspection;
    private JPanel buttonPanel;

    public DetectionLabelChoosingView(Inspection inspection) {
        super();
        this.inspection = inspection;
        setLayout(new BorderLayout());
        setBackground(ColorFactory.back);

        JLabel label = CompFactory.label();
        label.setText("見たい検出情報のラベルを選んでね");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(FontFactory.large);
        add(label, BorderLayout.NORTH);

        buttonPanel = CompFactory.panel();
        buttonPanel.setLayout(new GridLayout(-1, 1, 50, 40));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(60, 60, 60, 60));
        add(buttonPanel, BorderLayout.CENTER);

        presenter = new DetectionLabelChoosingPresenter(this, inspection);
    }

    @Override
    public void setLabelTextList(List<String> labelList) {
        buttonPanel.removeAll();
        for (String label : labelList) {
            JButton button = CompFactory.button();
            button.setText(label);
            button.setActionCommand(label);
            button.addActionListener(this);
            buttonPanel.add(button);
        }
        validate();
    }

    @Override
    public void gotoInspectingView(Inspection inspection, String targetLabel) {
        MasterView.inst.changeScene(
                new InspectingView(inspection,targetLabel)
        );
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        presenter.onDetectionLabelChosen(actionEvent.getActionCommand());
    }
}
