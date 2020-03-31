package view;

import iview.IInitialSettingView;
import model.Inspection;
import presenter.InitialSettingPresenter;
import ui.ColorFactory;
import ui.CompFactory;
import ui.FontFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

public class InitialSettingView extends JPanel implements IInitialSettingView, ActionListener {
    private InitialSettingPresenter presenter;
    private JButton cImageDirChoosingButton;
    private JButton cDetectionChoosingButton;
    private JButton cOKButton;

    public InitialSettingView() {
        super();
        setBackground(ColorFactory.back);
        setLayout(new BorderLayout(0, 50));

        JLabel cTop = CompFactory.label();
        cTop.setText("初期設定");
        cTop.setHorizontalAlignment(SwingConstants.CENTER);
        cTop.setFont(FontFactory.large);
        add(cTop, BorderLayout.NORTH);

        JPanel center = CompFactory.panel();
        center.setBorder(BorderFactory.createEmptyBorder(0, 70, 0, 70));
        center.setLayout(new GridLayout(2, 2, 40, 70));


        //1
        JLabel label = CompFactory.label();
        label.setText("未検証・未訂正の画像が含まれるディレクトリ");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        center.add(label);

        cImageDirChoosingButton = CompFactory.button();
        cImageDirChoosingButton.setActionCommand("IMAGEDIRCHOOSING");
        cImageDirChoosingButton.addActionListener(this);
        cImageDirChoosingButton.setHorizontalAlignment(SwingConstants.LEFT);
        center.add(cImageDirChoosingButton);

        //2
        label = CompFactory.label();
        label.setText("検出情報が含まれるディレクトリ");
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        center.add(label);

        cDetectionChoosingButton = CompFactory.button();
        cDetectionChoosingButton.setActionCommand("DETECTIONCHOOSING");
        cDetectionChoosingButton.addActionListener(this);
        cDetectionChoosingButton.setHorizontalAlignment(SwingConstants.LEFT);
        center.add(cDetectionChoosingButton);

        add(center, BorderLayout.CENTER);

        cOKButton = CompFactory.button();
        cOKButton.setText("OK");
        cOKButton.setBackground(ColorFactory.assort);
        cOKButton.setActionCommand("OK");
        cOKButton.addActionListener(this);
        add(cOKButton, BorderLayout.SOUTH);

        presenter = new InitialSettingPresenter(this);
        setVisible(true);
    }

    @Override
    public void setImageDirText(String directoryName, String fullPath) {
        cImageDirChoosingButton.setText(directoryName);
        cImageDirChoosingButton.setToolTipText(fullPath);
    }

    @Override
    public void setDetectionDirText(String directoryName, String fullPath) {
        cDetectionChoosingButton.setText(directoryName);
        cDetectionChoosingButton.setToolTipText(fullPath);
    }


    @Override
    public void gotoDetectionLabelChoosingView(Inspection inspection, List<String> labelList) {
        MasterView.inst.changeScene(
                new DetectionLabelChoosingView(inspection, labelList)
        );
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        String acco = actionEvent.getActionCommand();
        if (acco.equals(cImageDirChoosingButton.getActionCommand())) {
            //未検証・未訂正の画像が含まれるディレクトリを指定する
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int res = chooser.showOpenDialog(this);
            if (res == JFileChooser.APPROVE_OPTION) {
                presenter.onImageDirChosen(chooser.getSelectedFile());
            }

        } else if (acco.equals(cDetectionChoosingButton.getActionCommand())) {
            //検出情報が含まれるディレクトリを指定する
            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
                presenter.onDetectionDirChosen(chooser.getSelectedFile());
            }
        } else if (acco.equals(cOKButton.getActionCommand())) {
            presenter.onOKButtonPushed();
        }
    }
}
