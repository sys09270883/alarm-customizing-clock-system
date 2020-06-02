import javax.swing.*;

public class AlarmView extends DefaultLayout {
    final static int ALARM_WIDTH = 300;
    final static int ALARM_HEIGHT = 45;
    final static int ALARM_LIST_HEIGHT = 135;
    final static int DISPLAY_AMT = 3;

    JPanel curAlarmPanel;
    JPanel alarmListPanel;

    public AlarmView(System system) {
        super(system);

        curAlarmPanel = new JPanel();
        curAlarmPanel.setLayout(null);
        curAlarmPanel.setBounds(350, 180, ALARM_WIDTH, ALARM_HEIGHT);
        curAlarmPanel.setVisible(true);

        displaySegment(350, 180, ALARM_WIDTH, ALARM_HEIGHT, "000000");

        alarmListPanel = new JPanel();
        alarmListPanel.setLayout(null);
        alarmListPanel.setBounds(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT);
        alarmListPanel.setVisible(true);

        displaySegment(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE");
        displaySegment(350, 240 + ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE");
        displaySegment(350, 240 + 2 * ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE");

        add(alarmListPanel, new Integer(1));
        add(curAlarmPanel, new Integer(1));
    }

    public void setAlarm(String str) {
        displaySegment(350, 180, ALARM_WIDTH, ALARM_HEIGHT, str, layer++);
    }

    public void setAlarmList(AlarmData[] alarmData, int pointer, int size) {

        Time alarmData1;

        switch (size) {
            case 0 :
                displaySegment(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE", layer++);
                displaySegment(350, 240 + ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE" , layer++);
                displaySegment(350, 240 + 2 * ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE", layer++);
                break;
            case 1:
                displaySegment(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[0].getTime().getCurrentTime()) , layer++);
                displaySegment(350, 240 + ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE" , layer++);
                displaySegment(350, 240 + 2 * ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE", layer++);
                break;
            case 2:
                alarmData1 = alarmData[0].getTime();
                displaySegment(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[0].getTime().getCurrentTime()), layer++);

                alarmData1 = alarmData[1].getTime();
                displaySegment(350, 240 + ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[1].getTime().getCurrentTime()) , layer++);
                displaySegment(350, 240 + 2 * ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, "  NONE", layer++);
                break;
            case 3:
                displaySegment(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[0].getTime().getCurrentTime()), layer++);
                displaySegment(350, 240 + ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[1].getTime().getCurrentTime()), layer++);
                displaySegment(350, 240 + 2 * ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT,String.format("%6s",alarmData[2].getTime().getCurrentTime()), layer++);
            default:
                displaySegment(350, 240, ALARM_WIDTH, ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[pointer-1].alarmTime.getCurrentTime()), layer++);
                displaySegment(350, 240 + ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[pointer].alarmTime.getCurrentTime()) , layer++);
                displaySegment(350, 240 + 2 * ALARM_LIST_HEIGHT / DISPLAY_AMT, ALARM_WIDTH
                        , ALARM_LIST_HEIGHT / DISPLAY_AMT, String.format("%6s",alarmData[pointer+1].alarmTime.getCurrentTime()), layer++);
                break;
        }




    }


}
