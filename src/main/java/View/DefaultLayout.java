package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DefaultLayout extends JPanel implements ActionListener {

    final static int FRAME_WIDTH = 1000;
    final static int FRAME_HEIGHT = 550;
    final static int PADDING_X = 100;
    final static int PADDING_Y = 120;
    final static int BTN_WIDTH = 100;
    final static int BTN_HEIGHT = 50;
    final static String CLOCK_IMG_NAME = "clocklayout.jpg";

    ImageIcon clockImage;
    JButton startBtn;
    JButton resetBtn;
    JButton selectBtn;
    JButton modeBtn;

    public DefaultLayout() {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setLayout(null);

        startBtn = new JButton("START");
        startBtn.setBounds(PADDING_X, PADDING_Y, BTN_WIDTH, BTN_HEIGHT);
        setBtn(startBtn);

        resetBtn = new JButton("RESET");
        resetBtn.setBounds(FRAME_WIDTH - PADDING_X - BTN_WIDTH, PADDING_Y, BTN_WIDTH, BTN_HEIGHT);
        setBtn(resetBtn);

        selectBtn = new JButton("SELECT");
        selectBtn.setBounds(PADDING_X, FRAME_HEIGHT - PADDING_Y - BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
        setBtn(selectBtn);

        modeBtn = new JButton("MODE");
        modeBtn.setBounds(FRAME_WIDTH - PADDING_X - BTN_WIDTH,
                FRAME_HEIGHT - PADDING_Y - BTN_HEIGHT, BTN_WIDTH, BTN_HEIGHT);
        setBtn(modeBtn);

        add(startBtn);
        add(resetBtn);
        add(selectBtn);
        add(modeBtn);

        clockImage = new ImageIcon(CLOCK_IMG_NAME);
        clockImage = new ImageIcon(clockImage.getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH));
        JLabel clockLabel = new JLabel(clockImage);
        clockLabel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        add(clockLabel);
    }

    private static void setBtn(JButton btn) {
        btn.setBorderPainted(false);
        btn.setVisible(true);
        btn.setBackground(Color.lightGray);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int idx = -1;
        if (e.getSource() == startBtn)
            idx = 0;
        else if (e.getSource() == resetBtn)
            idx = 1;
        else if (e.getSource() == selectBtn)
            idx = 2;
        else if (e.getSource() == modeBtn)
            idx = 3;
    }
}
