import javax.swing.*;

public class TimerView extends DefaultLayout {
    final static int TIMER_WIDTH = 330;
    final static int TIMER_HEIGHT = 170;

    JPanel timerPanel;

    public TimerView(System system) {
        super(system);

        timerPanel = new JPanel();
        timerPanel.setLayout(null);
        timerPanel.setBounds(335, 190, TIMER_WIDTH, TIMER_HEIGHT);
        timerPanel.setVisible(true);

        add(timerPanel, new Integer(1));
    }
}
