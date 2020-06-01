import java.util.Arrays;

/**
 * @author Yoonseop Shin
 */
public class System extends Function {

    // 6개 중 4개 인스턴스만 갖고있음.
    // 알람, 알람커스텀은 항상 둘 다 포함되거나 포함되지 않아야 한다.
    public MainFrame GUI;
    public Function[] functions;

    public System() {
        GUI = new MainFrame(this);

        functionNum = new int[6];
        Arrays.fill(functionNum, 0);
        functionNum[0] = 1;
        functionNum[1] = 2;
        functionNum[2] = 3;
        functionNum[3] = 4;
        status = 0b00;
        type = 0;
        selectedFunction = 0;

        functions = new Function[4];
        for (int i = 0; i < 4; i++) {
            switch (i) {
                case 0:
                    functions[i] = new TimeKeeping(this);
                    break;
                case 1:
                    functions[i] = new Stopwatch();
                    break;
                case 2:
                    functions[i] = new Timer();
                    break;
                case 3:
                    functions[i] = new D_day();
                    break;
                default:
                    break;
            }
        }

        buzzer = new Buzzer();
        blink = new Blink();

    }

    public static void main(String[] args) {
        System system = new System();


    }

    @Override
    public void timeout() {

    }

    @Override
    public void cancel() {

    }

    @Override
    public void changeMode() {

    }

    /**
     * 
     */
    private int[] functionNum;

    /**
     * 
     */
    private int selectedFunction;

    /**
     * 비트마스킹: 0b00 0b01 0b10 0b11
     */
    private int status;

    /**
     * 수정할 인덱스: 연, 월, 일, 시, 분, 초 [0, 5]
     */
    private int type;

    /**
     * 
     */
    private Buzzer buzzer;

    /**
     * 
     */
    private Blink blink;





    /**
     * 
     */
    public void changeType() {
        type = (type + 1) % 6;
    }

    /**
     * @param diff: +1 or -1
     */
    public void changeValue(int diff) {
        // TODO implement here
    }

    /**
     * @param selected
     */
    public void setFunction(int[] selected) {
        // TODO implement here
    }

    /**
     * 
     */
    public void selectFunction() {
        // TODO implement here
    }

    /**
     * 
     */
    public void beepBuzzer() {
        // TODO implement here
    }

    /**
     * 
     */
    public void updateStatus() {
        // TODO implement here
    }

    /**
     * 
     */
    public void set() {
        // TODO implement here
    }

    /**
     * 
     */
    public void nextFunction() {
        // TODO implement here
    }
}