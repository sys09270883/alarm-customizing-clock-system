
/**
 * @author Yoonseop Shin
 */
public class Stopwatch extends Function {

    /**
     * Default constructor
     */
    public Stopwatch() {
    } //branch test

    /**
     * 
     */
    private int mode;

    /**
     * 
     */
    private Time stopwatch;

    /**
     * 
     */
    private Time[] stopwatchRecord;

    /**
     * 
     */
    private int recordPointer;


    /**
     * 
     */
    public void requestStartStopwatch() {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestPauseStopwatch() {
        // TODO implement here
    }

    /**
     * 
     */
    public void clearList() {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestSaveRecord() {
        // TODO implement here
    }

    /**
     * @param stopwatchTime
     */
    public void record(Time stopwatchTime) {
        // TODO implement here
    }

    /**
     * 
     */
    public void requestRecordCheckMode() {
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
    public void timeout() {

    }

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