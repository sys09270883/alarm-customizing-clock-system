
/**
 * @author Yoonseop Shin
 */
public class Alarm extends Function {

    /**
     * Default constructor
     */
    public Alarm(System system) {
        fid = 5;
        alarmList = new AlarmData[10];
    }

    /**
     * 
     */
    private AlarmData curAlarm;

    /**
     * 
     */
    private AlarmData[] alarmList;


    public int getSize() {
        int cnt = 0;
        for (AlarmData alarmData : alarmList) {
            if (alarmData != null)
                cnt++;
        }
        return cnt;
    }

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