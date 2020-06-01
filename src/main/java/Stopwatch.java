
/**
 * @author Yoonseop Shin
 */
public class Stopwatch extends Function {

    /**
     * Default constructor
     */
    public Stopwatch() {
        fid = 2;
        mode = 0;
        stopwatchRecord = new Time[10];
        for(int i=0; i<10; i++) {
            stopwatchRecord[i] = null;
        }
        stopwatch = new Time(1);
    }

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
     * Stopwatch 실행 요청
     */
    public void requestStartStopwatch() {
        stopwatch.run();
    }

    /**
     * Stopwatch 멈춤 요청
     */
    public void requestPauseStopwatch() {
        stopwatch.pauseTime();
    }

    /*
     * requestResetStopwatch() Class Diagram에 추가해야합니다
     */
    public void requestResetStopwatch() {
        stopwatch.clearTime();
    }

    /**
     * stopwatch record list를 초기화
     */
    public void clearList() {
        for(int i=0; i<10; i++) {
            stopwatchRecord[i] = null;
        }
    }

    /**
     * record(Time stopwatchTime)를 단순히 호출하는 방식이라
     */
    public void requestSaveRecord() {
    }

    /**
     * @param stopwatchTime
     */
    public void record(Time stopwatchTime) {

        for(int i=0; i<10; i++) {
            //stopwatch의 기록이 10개 미만일 때
            if(stopwatchRecord[i] == null) {
                stopwatchRecord[i] = stopwatchTime; //주석 수정해야합니다.
                break;
            }

            //stopwatch의 기록이 10개일 때
            if(i==9 || stopwatchRecord[i] != null) {
                for(int j=0; j<9; j++) {
                    stopwatchRecord[j] = stopwatchRecord[j+1];
                }
                stopwatchRecord[9] = stopwatchTime;
            }
        }
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