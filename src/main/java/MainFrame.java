

import javax.swing.*;

public class MainFrame extends JFrame {

    final static int FRAME_WIDTH = 1000;
    final static int FRAME_HEIGHT = 550;

    TimekeepingView timekeepingView;
    StopwatchView stopwatchView;
    TimerView timerView;

    public MainFrame() {
        setTitle("Alarm Customizing Clock System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);

        timekeepingView = new TimekeepingView();
        stopwatchView = new StopwatchView();
        timerView = new TimerView();

        // Default view: TimekeepingView
        setView(timekeepingView);
    }

    /**
     * view를 바꿔 껴주는 함수
     * @param view: TimekeepingView, StopwatchView, ...
     */
    public void setView(JPanel view) {
        setContentPane(view);
    }

}
