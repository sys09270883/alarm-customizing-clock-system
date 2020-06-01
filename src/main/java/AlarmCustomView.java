import javax.swing.*;
import java.awt.*;

public class AlarmCustomView extends DefaultLayout {
    final static int ALARM_LIST_WIDTH = 300;
    final static int ALARM_LIST_HEIGHT = 135;
    final static int _WIDTH = 60;
    final static int _HEIGHT = 60;
    final static String VOLUME_IMG_NAME = "src/main/resources/soundimage.jpg";
    final static String INTERVAL_IMG_NAME = "src/main/resources/intervalimage.jpg";
    final static int DISPLAY_AMT = 3;

    ImageIcon volumeImage;
    ImageIcon intervalImage;
    JLabel volumeLabel;
    JPanel volumeImgPanel;
    JPanel volumeControlPanel;
    JLabel intervalLabel;
    JPanel intervalImgPanel;
    JPanel intervalControlPanel;
    JPanel alarmListPanel;

    public AlarmCustomView(System system) {
        super(system);

        volumeImage = new ImageIcon(VOLUME_IMG_NAME);
        volumeImage = new ImageIcon(volumeImage.getImage()
                .getScaledInstance(_WIDTH, _HEIGHT, Image.SCALE_SMOOTH));
        volumeLabel = new JLabel(volumeImage);
        volumeLabel.setBounds(0, 0, _WIDTH, _HEIGHT);
        volumeLabel.setVisible(true);

        volumeImgPanel = new JPanel();
        volumeImgPanel.setLayout(null);
        volumeImgPanel.setVisible(true);
        volumeImgPanel.setBounds(370, 165, _WIDTH, _HEIGHT);
        volumeImgPanel.add(volumeLabel);

        volumeControlPanel = new JPanel();
        volumeControlPanel.setLayout(null);
        volumeControlPanel.setVisible(true);
        volumeControlPanel.setBounds(430, 165, _WIDTH, _HEIGHT);

        displaySegment(430, 165, _WIDTH, _HEIGHT, "0");

        intervalImage = new ImageIcon(INTERVAL_IMG_NAME);
        intervalImage = new ImageIcon(intervalImage.getImage()
                .getScaledInstance(_WIDTH, _HEIGHT, Image.SCALE_SMOOTH));
        intervalLabel = new JLabel(intervalImage);
        intervalLabel.setBounds(0, 0, _WIDTH, _HEIGHT);
        intervalLabel.setVisible(true);

        intervalImgPanel = new JPanel();
        intervalImgPanel.setLayout(null);
        intervalImgPanel.setVisible(true);
        intervalImgPanel.setBounds(490, 165, _WIDTH, _HEIGHT);
        intervalImgPanel.add(intervalLabel);

        intervalControlPanel = new JPanel();
        intervalControlPanel.setLayout(null);
        intervalControlPanel.setVisible(true);
        intervalControlPanel.setBounds(550, 165, _WIDTH, _HEIGHT);

        displaySegment(550, 165, _WIDTH, _HEIGHT, "0");

        alarmListPanel = new JPanel();
        alarmListPanel.setLayout(null);
        alarmListPanel.setBounds(350, 240, ALARM_LIST_WIDTH, ALARM_LIST_HEIGHT);
        alarmListPanel.setVisible(true);

        displaySegment(350, 240, ALARM_LIST_WIDTH, ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE");
        displaySegment(350, 240 + ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_LIST_WIDTH
                , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE");
        displaySegment(350, 240 + 2 * ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_LIST_WIDTH
                , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE");

        add(intervalControlPanel, new Integer(1));
        add(intervalImgPanel, new Integer(1));
        add(volumeControlPanel, new Integer(1));
        add(alarmListPanel, new Integer(1));
        add(volumeImgPanel, new Integer(1));
    }

}