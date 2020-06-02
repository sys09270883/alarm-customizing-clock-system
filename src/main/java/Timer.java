
/**
 * @author Yoonseop Shin
 */
public class Timer extends Function {

    private final int TYPE_SIZE = 3;
    private int timeSettingValue[] = {-1, -1, -1};
    /**
     * Default constructor
     */
    public Timer(System system) {
        fid = 3;
        mode = 0;
        timer = new Time(0);
        type = 0;
        curTime = new Time(0);
    }


    private Time timer;

    private int type;

    private Time curTime;


    /**
     * 
     */

    public void requestTimerSettingMode() {
        changeMode();
        String curTimeStr = curTime.getCurrentTime();
        String splited[] = curTimeStr.split(" ");
        curTime.setTime(Integer.parseInt(splited[0]), Integer.parseInt(splited[1]), Integer.parseInt(splited[2]));

    }

    /**
     * 설정한 시간으로 timer을 세팅해 저장합니다.
     */
    public void requestSave() {
        Time setTimer = new Time(0);
        setTimer.setTime(timeSettingValue[0], timeSettingValue[1], timeSettingValue[2]);

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


    /**
     * 
     */
    public void changeMode() {
        if(mode == 0) mode = 1;
        else mode = 0;
    }


    /**
     * @param diff
     */
    public void changeValue(int diff) {
        timeSettingValue[type] += diff;
        switch(type) {
            case 0:
                if (timeSettingValue[type] < timer.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = timer.HOUR_TOP_LIMIT;
                else if (timeSettingValue[type] > timer.HOUR_TOP_LIMIT)
                    timeSettingValue[type] = timer.TIME_BOTTOM_LIMIT;
                break;
            case 1:
                if (timeSettingValue[type] < timer.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = timer.MINUTE_TOP_LIMIT;
                else if (timeSettingValue[type] > timer.MINUTE_TOP_LIMIT)
                    timeSettingValue[type] = timer.TIME_BOTTOM_LIMIT;
                break;
            case 2:
                if (timeSettingValue[type] < timer.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = timer.SECOND_TOP_LIMIT;
                else if (timeSettingValue[type] > timer.SECOND_TOP_LIMIT)
                    timeSettingValue[type] = timer.TIME_BOTTOM_LIMIT;
                break;
        }
    }

    /**
     * 
     */
    public void changeType() { type = (type + 1) % TYPE_SIZE; }

    public int getMode() {
        return this.mode;
    }
}