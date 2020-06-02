
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

    Alarm alarm;

    public void requestAlarmSelectMode(Alarm alarm2) {
        // TODO implement here

        this.alarm = alarm2;

        mode = -1;

        changeMode();
    }

    /**
     * 
     */
    public void requestIntervalSettingMode() {
        // TODO implement here

        this.mode = 1;
        changeMode();

    }

    /**
     * 
     */
    public void requestAlarmVolumeMode() {
        // TODO implement here
        this.mode = 2;
        changeMode();
    }

    /**
     * 
     */
    public void requestSave() {
        // TODO implement here
        AlarmCustom alarmCustom = new AlarmCustom();
        alarmCustom.interval = this.interval;
        alarmCustom.volume = this.volume;
        setCustom(alarmCustom);
    }

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

        if(this.mode == 1){

            this.interval = 1;


        } else if (this.mode == 2)
        {

            this.volume = 0;

        } else {

        }

    }

    /**
     * @param diff
     */
    public void changeValue(int diff) {

        if(this.mode == 1)
        {
            this.interval += diff;
            if(this.interval > 3)
            {
                this.interval = 3;
            } else if (this.interval < 1)
            {
                this.interval = 1;
            }
        }
        else if (this.mode == 2)
        {
            this.volume += diff;
            if(this.volume > 4)
            {
                this.volume = 4;
            } else if( this.volume < 0)
            {
                this.volume = 0;
            }
        }


    }

    /**
     * 
     */
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