
/**
 * @author Yoonseop Shin
 */
public class AlarmCustom extends Function {

    /**
     * Default constructor
     */
    public AlarmCustom() {
        fid = 6;
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

    /**
     * 
     */
    private int volumePointer;


    /**
     * 
     */
    public void requestAlarmSelectMode() {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestIntervalSettingMode() {
        // TODO implement here
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

    public void changeMode() {

    }

    public void changeValue(int diff) {

    }

    public void changeType() {

    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
}