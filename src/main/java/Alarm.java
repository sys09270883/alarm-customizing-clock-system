
/**
 * @author Yoonseop Shin
 */
public class Alarm extends Function {

    /**
     * Default constructor
     */
    public Alarm() {  // 초기
        curAlarm  = new AlarmData();
        alarmList = new AlarmData[10];
        mode = 3;
        alarmPointer = 0;
    }

    AlarmData curAlarm ;
    AlarmData[] alarmList;

    private int alarmPointer;
    private int mode;





    /**
     * 
     */
    public void requestAlarmSettingMode() {
        // TODO implement here
        changeMode();



    }


    /**
     * 
     */
    public void requestSave() {
        // TODO implement here
        //addTimeToAlarmList(curAlarm.);

    }

    /**
     * @param alarmTime
     */
    public void addTimeToAlarmList(Time alarmTime) {
        // TODO implement here

       // (alarmList.length);


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
        alarmList[alarmIdx] = null;
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
        alarmPointer += diff;
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
    @Override
    public void changeMode() {mode = 1;}

    @Override
    public void changeValue(int diff) {

    }


    public void changeValue(int diff, int Value) {

        if(Value == 1)
        {
            Value += diff;
        }else if(Value == 2)
        {

        }

    }

    /**
     * 
     */
    public void changeType() {

    }

}