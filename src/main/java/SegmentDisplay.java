import javax.swing.*;
import java.awt.*;

public class SegmentDisplay extends JPanel {

    final static String ZERO = "src/main/resources/zero.jpg";
    final static String ONE = "src/main/resources/one.jpg";
    final static String TWO = "src/main/resources/two.jpg";
    final static String THREE = "src/main/resources/three.jpg";
    final static String FOUR = "src/main/resources/four.jpg";
    final static String FIVE = "src/main/resources/five.jpg";
    final static String SIX = "src/main/resources/six.jpg";
    final static String SEVEN = "src/main/resources/seven.jpg";
    final static String EIGHT = "src/main/resources/eight.jpg";
    final static String NINE = "src/main/resources/nine.jpg";
    final static String N = "src/main/resources/N.jpg";
    final static String O = "src/main/resources/O.jpg";
    final static String E = "src/main/resources/E.jpg";
    final static String R = "src/main/resources/R.jpg";
    final static String U = "src/main/resources/U.jpg";
    final static String S = "src/main/resources/S.jpg";
    final static String F = "src/main/resources/F.jpg";
    final static String I = "src/main/resources/I.jpg";
    final static String T = "src/main/resources/T.jpg";
    final static String H = "src/main/resources/H.jpg";
    final static String W = "src/main/resources/W.jpg";
    final static String A = "src/main/resources/A.jpg";
    final static String M = "src/main/resources/M.jpg";
    final static String D = "src/main/resources/D.jpg";
    final static String Y = "src/main/resources/Y.jpg";
    final static String blank = "src/main/resources/blank.jpg";

    ImageIcon segmentImage;
    JLabel segmentLabel;

    public SegmentDisplay(int x, int y, int width, int height, char c) {
        setVisible(true);
        setLayout(null);
        setBounds(x, y, width, height);

        if (c == '0')
            segmentImage = new ImageIcon(ZERO);
        else if (c == '1')
            segmentImage = new ImageIcon(ONE);
        else if (c == '2')
            segmentImage = new ImageIcon(TWO);
        else if (c == '3')
            segmentImage = new ImageIcon(THREE);
        else if (c == '4')
            segmentImage = new ImageIcon(FOUR);
        else if (c == '5')
            segmentImage = new ImageIcon(FIVE);
        else if (c == '6')
            segmentImage = new ImageIcon(SIX);
        else if (c == '7')
            segmentImage = new ImageIcon(SEVEN);
        else if (c == '8')
            segmentImage = new ImageIcon(EIGHT);
        else if (c == '9')
            segmentImage = new ImageIcon(NINE);
        else if (c == 'N')
            segmentImage = new ImageIcon(N);
        else if (c == 'O')
            segmentImage = new ImageIcon(O);
        else if (c == 'E')
            segmentImage = new ImageIcon(E);
        else if (c == 'R')
            segmentImage = new ImageIcon(R);
        else if (c == 'U')
            segmentImage = new ImageIcon(U);
        else if (c == 'S')
            segmentImage = new ImageIcon(S);
        else if (c == 'F')
            segmentImage = new ImageIcon(F);
        else if (c == 'I')
            segmentImage = new ImageIcon(I);
        else if (c == 'T')
            segmentImage = new ImageIcon(T);
        else if (c == 'H')
            segmentImage = new ImageIcon(H);
        else if (c == 'W')
            segmentImage = new ImageIcon(W);
        else if (c == 'A')
            segmentImage = new ImageIcon(A);
        else if (c == 'M')
            segmentImage = new ImageIcon(M);
        else if (c == 'D')
            segmentImage = new ImageIcon(D);
        else if (c == 'Y')
            segmentImage = new ImageIcon(Y);
        else
            segmentImage = new ImageIcon(blank);

        segmentImage = new ImageIcon(segmentImage.getImage()
                .getScaledInstance(width, height, Image.SCALE_SMOOTH));
        segmentLabel = new JLabel(segmentImage);
        segmentLabel.setBounds(0, 0, width, height);
        segmentLabel.setVisible(true);

        add(segmentLabel);
    }

}
