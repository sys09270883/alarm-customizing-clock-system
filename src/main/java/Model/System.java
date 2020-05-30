package Model;

import java.util.Arrays;

/**
 * @author Yoonseop Shin
 */
public class System extends Function {

    /**
     * Default constructor
     */
    public System() {
        functionNum = new int[6];
        Arrays.fill(functionNum, 0);
        functionNum[0] = 1;
        functionNum[1] = 2;
        functionNum[2] = 3;
        functionNum[3] = 4;
        status = 0b00;
        type = 0;
        selectedFunction = 0;
        buzzer = new Buzzer();
        blink = new Blink();
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