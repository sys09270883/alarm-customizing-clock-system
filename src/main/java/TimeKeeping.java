
/**
 * @author Yoonseop Shin
 */
public class TimeKeeping extends Function {

    /**
     * Default constructor
     */
    public TimeKeeping() {
    }

    /**
     * 
     */
    private int mode;

    /**
     * 
     */
    private Time curTime;

    /**
     * 
     */
    private Date curDate;

    /**
     * 
     */
    private int d_day;

    /**
     * 
     */
    private int alarmCnt;

    /**
     * 
     */
    private int dayOfTheWeek;

    /**
     * 
     */
    private int type;

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