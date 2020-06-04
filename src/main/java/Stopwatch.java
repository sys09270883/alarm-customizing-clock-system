import java.util.StringTokenizer;

/**
 * @author Yoonseop Shin
 */
public class Stopwatch extends Function {

    final static int FID = 2;
    public final int STOPWATCH_TOP_LIMIT = 7;
    public final int STOPWATCH_BOTTOM_LIMIT = 0;

    private final int TYPE_SIZE = 3;
    private int timeSettingValue[] = {-1, -1, -1};

    /**
     * Default constructor
     */
    public Stopwatch(System system) {
        fid = 2;
        mode = 0;
        type = 0;
        stopwatchRecord = new String[10];
        for(int i=0; i<10; i++) {
            stopwatchRecord[i] = null;
        }
        stopwatch = new Time(1);
        stopwatch.setTime(0, 0, 0);
        stopwatch.setSecondListener(() -> {
                    String time = stopwatch.getCurrentTime();
                    StringTokenizer st = new StringTokenizer(time, " ");
                    system.GUI.stopwatchView.setStopwatch(String.format("%02d", Integer.parseInt(st.nextToken()))
                    + String.format("%02d", Integer.parseInt(st.nextToken())) +
                            String.format("%02d", Integer.parseInt(st.nextToken())));
                }
                );
        recordPointer = 0;
    }

    private int mode;

    private Time stopwatch;

    public String[] getStopwatchRecord() {
        return stopwatchRecord;
    }

    private String[] stopwatchRecord;

    private int recordPointer;

    private int type;



    /**
     * Stopwatch 실행 요청
     */
    public void requestStartStopwatch() {
        changeMode(1);
        stopwatch.startTime();
    }

    /**
     * Stopwatch 멈춤 요청
     */
    public void requestPauseStopwatch() {
        stopwatch.pauseTime();
        try {
            stopwatch.getTimeThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeMode(0);
    }

    /*
     * requestResetStopwatch() Class Diagram에 추가해야합니다
     */
    public void requestResetStopwatch() {
        if (mode == 1)
            requestPauseStopwatch();
        stopwatch.clearTime();
        clearList();
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
     *
     */
    public void requestSaveRecord() {
        String time = stopwatch.getCurrentTime();
        record(time);
    }

    /**
     * @param stopwatchTime
     */
    public void record(String stopwatchTime) {
        for(int i=0; i<10; i++) {
            //stopwatch의 기록이 10개 미만일 때
            if(stopwatchRecord[i] == null) {
                stopwatchRecord[i] = stopwatchTime; //주석 수정해야합니다.
                break;
            }

            //stopwatch의 기록이 10개일 때
            if(i==9 && stopwatchRecord[i] != null) {
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
        changeMode(2);
        // TODO move pointer 후 Recordcheck가 끝나면 changeMode()가 한번 더 필요함.
    }


    public int getRecordPointer() {
        return this.recordPointer;
    }
    /**
     * @param diff
     */
    public void movePointer(int diff) {
        recordPointer+=diff;

        if(recordPointer < STOPWATCH_BOTTOM_LIMIT)
            recordPointer = STOPWATCH_BOTTOM_LIMIT;
        else if(recordPointer > STOPWATCH_TOP_LIMIT)
            recordPointer = STOPWATCH_TOP_LIMIT;
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
    public void changeMode(int mode) {
        this.mode = mode;
    }


    /**
     * @param diff
     */
    public void changeValue(int diff) {
        timeSettingValue[type] += diff;
        switch(type) {
            case 0:
                if (timeSettingValue[type] < stopwatch.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = stopwatch.HOUR_TOP_LIMIT;
                else if (timeSettingValue[type] > stopwatch.HOUR_TOP_LIMIT)
                    timeSettingValue[type] = stopwatch.TIME_BOTTOM_LIMIT;
                break;
            case 1:
                if (timeSettingValue[type] < stopwatch.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = stopwatch.MINUTE_TOP_LIMIT;
                else if (timeSettingValue[type] > stopwatch.MINUTE_TOP_LIMIT)
                    timeSettingValue[type] = stopwatch.TIME_BOTTOM_LIMIT;
                break;
            case 2:
                if (timeSettingValue[type] < stopwatch.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = stopwatch.SECOND_TOP_LIMIT;
                else if (timeSettingValue[type] > stopwatch.SECOND_TOP_LIMIT)
                    timeSettingValue[type] = stopwatch.TIME_BOTTOM_LIMIT;
                break;
        }
    }

    /**
     * 
     */
    public void changeType() { type = (type + 1) % TYPE_SIZE; }

    public int getMode() {
        return this.mode;
    }
}