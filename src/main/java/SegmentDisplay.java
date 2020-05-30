import javax.swing.*;
import java.awt.*;

public class SegmentDisplay extends JPanel {
    final static int SEGMENT_SIZE = 14;
    final static int SEGMENT_MASK = 0b00000000000000;
    final static int LINE_WIDTH = 100;
    final static int LINE_HEIGHT = 20;
    final static String LINE_IMG_NAME = "src/main/java/linelayout.jpg";

    ImageIcon lineImage;
    static int i;
    JLabel[] display;

    public SegmentDisplay() {
        setVisible(true);
        setSize(200, 100);

        lineImage = new ImageIcon(LINE_IMG_NAME);
        lineImage = new ImageIcon(lineImage.getImage()
                .getScaledInstance(LINE_WIDTH, LINE_HEIGHT, Image.SCALE_SMOOTH));

        display = new JLabel[14];
        for (i = 0; i < SEGMENT_SIZE; i++) {
            display[i] = new JLabel() {
                @Override
                protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    Graphics2D g2 = (Graphics2D)g;
                    switch (i) {
                        case 0:
                            break;

                        case 1:
                            break;
                        case 2:
                            break;
                        case 3:
                            break;
                        case 4:
                            break;
                        case 5:
                            break;
                        case 6:
                            break;
                        case 7:
                            break;
                        case 8:
                            break;

                        case 9:
                            break;

                        case 10:
                            break;

                        case 11:
                            break;

                        case 12:
                            break;

                        case 13:
                            break;


                    }
                }
            };
            display[i].setBackground(Color.GRAY);
            display[i].setVisible(true);
        }
    }

}
