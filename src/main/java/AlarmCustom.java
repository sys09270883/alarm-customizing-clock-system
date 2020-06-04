import java.util.Arrays;

/**
 * @author Yoonseop Shin
 */
public class AlarmCustom extends Function {

    /**
     * Default constructor
     */

    private int ALARM_TOP_LIMIT;
    private final int ALARM_BOTTOM_LIMIT = 0;

    final public static int default_interval = 2;
    final public static int default_volume = 2;

    public final int INTERVAL_TOP_LIMIT = 3;
    public final int INTERVAL_BOTTOM_LIMIT = 1;
    public final int VOLUME_TOP_LIMIT = 4;
    public final int VOLUME_BOTTOM_LIMIT = 0;

    public int[] getCustomSettingValue() {
        return customSettingValue;
    }

    public void setCustomSettingValue(int[] customSettingValue) {
        this.customSettingValue = customSettingValue;
    }

    int[] customSettingValue;

    public AlarmCustom(System system) {
        this.system = system;
        fid = 6;
        mode = 0;
        intervalPointer = 0;
        volumePointer = 0;
        customPointer = 0;
        valueIndex = 0;
        alarm = system.alarm;
        customSettingValue = new int[3];
        Arrays.fill(customSettingValue, -1);
        type = 0;
    }


    /**
     * 
     */
    private int intervalPointer;
    private int volumePointer;
    private int customPointer;
    private int valueIndex;


    /**
     * 
     */

    Alarm alarm;

    public void requestAlarmSelectMode() {
        // TODO implement here

        changeMode(1);
    }

    /**
     * 
     */
    public void requestIntervalSettingMode() {
        changeMode(2);
    }

    /**
     * 
     */
    public void requestAlarmVolumeMode() {
        // TODO implement here
        changeMode(3);
    }

    /**
     * 
     */
//    public void requestSave() {
//        // TODO implement here
//        AlarmCustom alarmCustom = new AlarmCustom();
//        alarmCustom.interval = this.interval;
//        alarmCustom.volume = this.volume;
//        setCustom(alarmCustom);
//    }

    System system;
    /**
     * @param alarmCustom
     */
    public void setCustom(AlarmCustom alarmCustom) {
        // TODO implement here
        int size = this.alarm.getSize();
        this.alarm.getAlarmList()[size].setAlarmCustom(alarmCustom);
        mode = 0;
        changeMode();

    }

    public void changeMode(int mode) {
        if (mode == 1){
            if ((ALARM_TOP_LIMIT = system.alarm.getSize()) == 0) {
                this.mode = 0;
                return;
            }
            customSettingValue[0] = 0;
        } else if (mode == 2)
        {
            if (this.mode == 1) {
                AlarmData[] tmp = system.alarm.getAlarmList();
                customSettingValue[1] = tmp[customSettingValue[0]].getInterval();
            }
        } else if (mode == 3) {
            AlarmData[] tmp = system.alarm.getAlarmList();
            customSettingValue[2] = tmp[customSettingValue[0]].getVolume();
        }
        else {
            Arrays.fill(customSettingValue, -1);
        }
        this.mode = mode;
    }

    int type;

    public void changeValue(int diff) {
        customSettingValue[type] += diff;

        switch (type) {
            case 0:
                if (customSettingValue[type] > ALARM_TOP_LIMIT)
                    customSettingValue[type] = ALARM_TOP_LIMIT;
                if (customSettingValue[type] < ALARM_BOTTOM_LIMIT)
                    customSettingValue[type] = ALARM_BOTTOM_LIMIT;
                break;
            case 1:
                if (customSettingValue[type] > INTERVAL_TOP_LIMIT)
                    customSettingValue[type] = INTERVAL_TOP_LIMIT;
                if (customSettingValue[type] < INTERVAL_BOTTOM_LIMIT)
                    customSettingValue[type] = INTERVAL_BOTTOM_LIMIT;
                break;
            case 2:
                if (customSettingValue[type] > VOLUME_TOP_LIMIT)
                    customSettingValue[type] = VOLUME_TOP_LIMIT;
                if (customSettingValue[type] < VOLUME_BOTTOM_LIMIT)
                    customSettingValue[type] = VOLUME_BOTTOM_LIMIT;
                break;
        }

    }



    public void changeType() {
        //return this.mode;

    }

    public int getMode() {
        return this.mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}