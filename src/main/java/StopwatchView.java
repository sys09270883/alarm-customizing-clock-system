import javax.swing.*;

public class StopwatchView extends DefaultLayout {
    final static int STOPWATCH_WIDTH = 300;
    final static int STOPWATCH_HEIGHT = 45;
    final static int STOPWATCH_LIST_HEIGHT = 135;

    JPanel curStopwatchPanel;
    JPanel stopwatchListPanel;

    public StopwatchView(System system) {
        super(system);
        curStopwatchPanel = new JPanel();
        curStopwatchPanel.setLayout(null);
        curStopwatchPanel.setBounds(350, 180, STOPWATCH_WIDTH, STOPWATCH_HEIGHT);
        curStopwatchPanel.setVisible(true);

        stopwatchListPanel = new JPanel();
        stopwatchListPanel.setLayout(null);
        stopwatchListPanel.setBounds(350, 240, STOPWATCH_WIDTH, STOPWATCH_LIST_HEIGHT);
        stopwatchListPanel.setVisible(true);

        add(stopwatchListPanel, new Integer(1));
        add(curStopwatchPanel, new Integer(1));
    }
}
