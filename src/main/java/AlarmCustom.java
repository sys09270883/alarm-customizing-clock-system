
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
        fid = 6;
        mode = 0;
        interval = default_interval;
        volume = default_volume;
        intervalPointer = 0;
        volumePointer = 0;
        customPointer = 0;
        valueIndex = 0;
    }

    /**
     * 
     */
    private int interval;

    /**
     * 
     */
    private int volume;

    /**
     * 
     */
    private int intervalPointer;
    private int volumePointer;
    private int customPointer;
    private int valueIndex;


    public int getInterval(){
        return this.interval;
    }


    public int getVolume(){
        return this.volume;
    }

    /**
     * 
     */
    public void requestAlarmSelectMode(Alarm alarm) {
        // TODO implement here
        mode = 1;

        changeMode();
    }

    /**
     * 
     */
    public void requestIntervalSettingMode() {
        // TODO implement here

        valueIndex = interval;
    }

    /**
     * 
     */
    public void requestAlarmVolumeMode() {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestSave() {
        // TODO implement here
    }

    /**
     * @param alarmCustom
     */
    public void setCustom(AlarmCustom alarmCustom) {
        // TODO implement here
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

    }

    /**
     * 
     */
    public void changeType() {

    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}