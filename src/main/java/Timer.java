
/**
 * @author Yoonseop Shin
 */
public class Timer extends Function {

    /**
     * Default constructor
     */
    public Timer(System system) {
        fid = 3;
        mode = 0;
        timer = new Time(0);
    }

    private int mode;
    private Time timer;
    public void requestTimerSettingMode() {
        // TODO implement here
    }

    /**
     * 설정한 시간으로 timer을 세팅해 저장합니다.
     */
    public void requestSave() {
        //timer = Time(hour, min, sec); Time 생성자 구현 필요
    }

    /**
     * 
     */
    public void requestStartTimer() {
        timer.run();
    }

    /**
     * 
     */
    public void requestResetTimer() {
        timer.clearTime();
    }

    /**
     * 
     */
    public void requestPauseTimer() {
        timer.pauseTime();
    }


    public void changeMode() {}

    /**
     * @param diff
     */
    public void changeValue(int diff) {}

    /**
     * 
     */
    public void changeType() {}

    public int getMode() {
        return this.mode;
    }
}