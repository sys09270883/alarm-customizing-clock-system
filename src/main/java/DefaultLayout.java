import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class DefaultLayout extends JLayeredPane implements ActionListener {

    final static int FRAME_WIDTH = 1000;
    final static int FRAME_HEIGHT = 550;
    final static int PADDING_X = 100;
    final static int PADDING_Y = 120;
    final static int BTN_WIDTH = 100;
    final static int BTN_HEIGHT = 50;
    final static String CLOCK_IMG_NAME = "src/main/resources/clocklayout.jpg";

    protected JPanel mainPanel;
    System system;
    ImageIcon clockImage;
    JButton startBtn;
    JButton resetBtn;
    JButton selectBtn;
    JButton modeBtn;
    JLabel clockLabel;

    public DefaultLayout(System system) {
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
        setVisible(true);
        setLayout(null);

        this.system = system;

        clockImage = new ImageIcon(CLOCK_IMG_NAME);
        clockImage = new ImageIcon(clockImage.getImage().getScaledInstance(FRAME_WIDTH, FRAME_HEIGHT, Image.SCALE_SMOOTH));
        clockLabel = new JLabel(clockImage);
        clockLabel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);

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

        mainPanel = new JPanel();
        mainPanel.setBounds(0, 0, FRAME_WIDTH, FRAME_HEIGHT);
        mainPanel.setLayout(null);
        mainPanel.add(startBtn);
        mainPanel.add(resetBtn);
        mainPanel.add(selectBtn);
        mainPanel.add(modeBtn);
        mainPanel.add(clockLabel);
        mainPanel.setVisible(true);

        add(mainPanel, new Integer(0));
    }

    private static void setBtn(JButton btn) {
        btn.setBorderPainted(false);
        btn.setVisible(true);
        btn.setBackground(Color.lightGray);
    }

    // 버튼 이벤트 처리할 부분
    @Override
    public void actionPerformed(ActionEvent e) {
        int btnFlag = -1;
        if (e.getSource() == startBtn)
            btnFlag = 0;
        else if (e.getSource() == resetBtn)
            btnFlag = 1;
        else if (e.getSource() == selectBtn)
            btnFlag = 2;
        else if (e.getSource() == modeBtn)
            btnFlag = 3;

        if (btnFlag == -1)
            return;

        // 눌린 버튼을 System에게 전달한다.
        // System이 선택된 버튼을 처리해야 한다.
    }

    protected void displaySegment(int x, int y, int w, int h, String str) {
        w /= str.length();
        for (int i = 0; i < str.length(); i++) {
            add(new SegmentDisplay(x, y, w, h, str.charAt(i)), new Integer(2));
            x += w;
        }
    }

}
