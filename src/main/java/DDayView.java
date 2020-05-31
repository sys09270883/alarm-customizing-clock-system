import javax.swing.*;

public class DDayView extends DefaultLayout {

    final static int D_DATE_WIDTH = 330;
    final static int D_DATE_HEIGHT = 100;
    final static int D_DAY_WIDTH = 250;
    final static int D_DAY_HEIGHT = 60;

    JPanel datePanel;
    JPanel d_dayPanel;

    public DDayView(System system) {
        super(system);

        datePanel = new JPanel();
        datePanel.setLayout(null);
        datePanel.setBounds(335, 190, D_DATE_WIDTH, D_DATE_HEIGHT);
        datePanel.setVisible(true);

        displaySegment(335, 190, D_DATE_WIDTH, D_DATE_HEIGHT, "200531");

        d_dayPanel = new JPanel();
        d_dayPanel.setLayout(null);
        d_dayPanel.setBounds(375, 310, D_DAY_WIDTH, D_DAY_HEIGHT);
        d_dayPanel.setVisible(true);

        displaySegment(375, 310, D_DAY_WIDTH, D_DAY_HEIGHT, "000");

        add(datePanel, new Integer(1));
        add(d_dayPanel, new Integer(1));
    }
}
