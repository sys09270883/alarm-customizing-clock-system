
/**
 * @author Yoonseop Shin
 */
public class Alarm extends Function {

    /**
     * Default constructor
     */
    public Alarm() {
    }

    /**
     * 
     */
    private AlarmData curAlarm;

    /**
     * 
     */
    private AlarmData[] alarmList;

    /**
     * 
     */
    private int alarmPointer;

    /**
     * 
     */
    private int mode;


    /**
     * 
     */
    public void requestAlarmSettingMode() {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestSave() {
        // TODO implement here
    }

    /**
     * @param alarmTime
     */
    public void addTimeToAlarmList(Time alarmTime) {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestDeleteAlarm() {
        // TODO implement here
    }

    /**
     * @param alarmIdx
     */
    public void deleteAlarm(int alarmIdx) {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestStopAlarmBuzzer() {
        // TODO implement here
    }

    /**
     * @param diff
     */
    public void movePointer(int diff) {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestAlarmSelectMode() {
        // TODO implement here
    }

    /**
     * 
     */
    public void timeout() {}


    /**
     * 
     */
    public void cancel() {}

    /**
     * 
     */
    public void changeMode() {}

    /**
     * @param diff
     */
    public void changeValue(int diff) {}

    /**
     * 
     */
    public void changeType() {}

}