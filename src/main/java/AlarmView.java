import javax.swing.*;

public class AlarmView extends DefaultLayout {
    final static int ALARM_WIDTH = 300;
    final static int ALARM_HEIGHT = 45;
    final static int ALARM_LIST_HEIGHT = 135;

    JPanel curAlarmPanel;
    JPanel alarmListPanel;

    public AlarmView(System system) {
        super(system);

        curAlarmPanel = new JPanel();
        curAlarmPanel.setLayout(null);
        curAlarmPanel.setBounds(350, 180, ALARM_WIDTH, ALARM_HEIGHT);
        curAlarmPanel.setVisible(true);

        alarmListPanel = new JPanel();
        alarmListPanel.setLayout(null);
        alarmListPanel.setBounds(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT);
        alarmListPanel.setVisible(true);

        add(alarmListPanel, new Integer(1));
        add(curAlarmPanel, new Integer(1));

    }
}
