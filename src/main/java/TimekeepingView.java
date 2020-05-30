import javax.swing.*;
import java.awt.*;

public class TimekeepingView extends DefaultLayout {

    final static int ALARM_WIDTH = 60;
    final static int ALARM_HEIGHT = 60;
    final static String ALARM_IMG_NAME = "src/main/java/alarmlayout.jpg";


    JPanel timekeepingPanel;
    JPanel alarmPanel;
    ImageIcon alarmImage;
    JLabel alarmLabel;

    public TimekeepingView(System system) {
        super(system);

        alarmImage = new ImageIcon(ALARM_IMG_NAME);
        alarmImage = new ImageIcon(alarmImage.getImage()
                .getScaledInstance(ALARM_WIDTH, ALARM_HEIGHT, Image.SCALE_SMOOTH));
        alarmLabel = new JLabel(alarmImage);
        alarmLabel.setBounds(0, 0, ALARM_WIDTH, ALARM_HEIGHT);
        alarmLabel.setVisible(true);

        alarmPanel = new JPanel();
        alarmPanel.setLayout(null);
        alarmPanel.setBounds(335, 175, ALARM_WIDTH, ALARM_HEIGHT);
        alarmPanel.add(alarmLabel);
        alarmPanel.setVisible(true);

//        timekeepingPanel = new JPanel();
//        timekeepingPanel.setLayout(null);

        add(alarmPanel, new Integer(1));
    }
}
