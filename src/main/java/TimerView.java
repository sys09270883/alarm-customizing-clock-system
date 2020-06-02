import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class TimerView extends DefaultLayout {
    final static int TIMER_WIDTH = 330;
    final static int TIMER_HEIGHT = 170;

    JPanel timerPanel;
    JPanel borderPanel;

    public TimerView(System system) {
        super(system);

        timerPanel = new JPanel();
        timerPanel.setLayout(null);
        timerPanel.setBounds(335, 190, TIMER_WIDTH, TIMER_HEIGHT);
        timerPanel.setVisible(true);

        displaySegment(350, 180, TIMER_WIDTH, TIMER_HEIGHT, "000000");

        borderPanel = new JPanel();
        borderPanel.setVisible(false);
        borderPanel.setBorder(new LineBorder(Color.GRAY, 5));
        borderPanel.setBounds(timerPanel.getX() - 5, timerPanel.getY() - 5,
                (timerPanel.getWidth() + 10) / 2, timerPanel.getHeight() + 10);

        add(timerPanel, new Integer(1));
    }
}
