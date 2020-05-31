

import javax.swing.*;

public class MainFrame extends JFrame {

    final static int FRAME_WIDTH = 1000;
    final static int FRAME_HEIGHT = 550;

    protected System system;
    TimekeepingView timekeepingView;
    StopwatchView stopwatchView;
    TimerView timerView;
    DDayView d_dayView;
    AlarmView alarmView;
    AlarmCustomView alarmCustomView;

    public MainFrame(System system) {
        setTitle("Alarm Customizing Clock System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        this.system = system;
        timekeepingView = new TimekeepingView(system);
        stopwatchView = new StopwatchView(system);
        timerView = new TimerView(system);
        d_dayView = new DDayView(system);
        alarmView = new AlarmView(system);
        alarmCustomView = new AlarmCustomView(system);

        setView(timekeepingView);
//        setView(stopwatchView);
//        setView(alarmView);
//        setView(timerView);
//        setView(d_dayView);
//        setView(alarmCustomView);
    }


    /**
     * view를 바꿔 껴주는 함수
     * @param view: JLayeredPane
     */
    public void setView(JLayeredPane view) {
        setContentPane(view);
    }

}
