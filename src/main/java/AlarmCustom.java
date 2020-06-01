
/**
 * @author Yoonseop Shin
 */
public class AlarmCustom extends Function {

    /**
     * Default constructor
     */


    final public static int default_interval = 2;
    final public static int default_volume = 2;

    public final int INTERVAL_TOP_LIMIT = 3;
    public final int INTERVAL_BOTTOM_LIMIT = 1;
    public final int VOLUME_TOP_LIMIT = 4;
    public final int VOLUME_BOTTOM_LIMIT = 0;


    public AlarmCustom() {
        interval = default_interval;
        volume = default_volume;
        intervalPointer = 0;
        volumePointer = 0;
        mode = 6;
        valueIndex = 0;

    }

    /**
     * 
     */
    private int interval;
    private int volume;
    private int intervalPointer;
    private int volumePointer;
    private int customPointer = 0;
    private int mode;

    Alarm alarm = new Alarm();

    private int valueIndex;

    public void requestAlarmSelectMode() {
        // TODO implement here
        alarm.changeMode();

        /* 아래 조건문 바꿔야 함.*/

        while(true)
        {
            //start(+)
            if(true) {
                alarm.movePointer(1, intervalPointer);
            }
            //reset(-)
            else if(true) {
                alarm.movePointer(-1, intervalPointer);
            }
            if (true) // timeout or Select Btn
            {
                break;
            }
        }

        requestIntervalSettingMode();
    }

    /**
     * 
     */
    public void requestIntervalSettingMode() {
        // TODO implement here

        alarm.curAlarm = alarm.alarmList[intervalPointer];

        interval = alarm.curAlarm.alarmCustom.interval;
        valueIndex = interval;

        while(true)
        {

            // start btn (+)
            if (true) {
                changeValue(1);
                if(valueIndex > 3)
                {
                    valueIndex = 3;
                }
            }
            // reset btn (-)
            else if(true) {
                changeValue(-1);
                if(valueIndex < 1)
                {
                    valueIndex = 1;
                }
            }

            if (true) // timeout
            {
                break;
            }
        }

        interval = valueIndex;

        requestAlarmVolumeMode(interval);

    }


    /**
     * 
     */
    public void requestAlarmVolumeMode(int interval) {
        // TODO implement here

        alarm.curAlarm = alarm.alarmList[intervalPointer];

        volume = alarm.curAlarm.alarmCustom.volume;
        valueIndex = volume;
        while(true)
        {

            // start btn (+)
            if (true) {
                changeValue(1);
                if(valueIndex > 4)
                {
                    valueIndex = 4;
                }
            }
            // reset btn (-)
            else if(true) {
                changeValue(-1);
                if(valueIndex < 0)
                {
                    valueIndex = 0;
                }
            }

            if (true) // timeout or PressBtn(Mode)
            {
                break;
            }
        }

        volume = valueIndex;

        requestSave();

    }

    /**
     * 
     */
    public void requestSave() {
        // TODO implement here

        alarm.curAlarm.alarmCustom.interval = interval;
        alarm.curAlarm.alarmCustom.volume = volume;

        setCustom(alarm.curAlarm.alarmCustom);

        changeMode();

    }

    /**
     * @param alarmCustom
     */
    public void setCustom(AlarmCustom alarmCustom) {
        // TODO implement here

        AlarmData alarmData = new AlarmData();
        alarmData.alarmCustom = alarmCustom;
        alarmData.alarmTime = alarm.alarmList[intervalPointer].alarmTime;

        alarm.alarmList[intervalPointer] = alarmData;

    }

    /**
     * 
     */
    public void timeout() {

    }

    /**
     * 
     */
    public void cancel() {

    }

    /**
     * 
     */
    public void changeMode() {

    }

    /**
     * @param diff
     */
    public void changeValue(int diff) {

        valueIndex += diff;
    }

    /**
     * 
     */
    public void changeType() {


    }

}