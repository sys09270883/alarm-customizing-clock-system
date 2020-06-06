import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * @author Yoonseop Shin
 */
public class Timer extends Function {

    final static int FID = 3;
    private final int TYPE_SIZE = 3;
    private int timeSettingValue[] = {-1, -1, -1};
    private Time timer;
    private int type;
    private Time timerCheckThread;
    System system;

    public Time getTimer() {
        return timer;
    }

    public Timer(System system) {
        this.system = system;
        fid = 3;
        mode = 0;
        timer = new Time(0);
        timer.setTime(0, 0, 0);
        timerCheckThread = null;

        timer.setSecondListener(() -> {
            if (mode == 2) {
                String str = timer.getCurrentTime();
                StringTokenizer st = new StringTokenizer(str, " ");
                system.GUI.timerView.setHour(String.format("%02d", Integer.parseInt(st.nextToken())));
                system.GUI.timerView.setMinute(String.format("%02d", Integer.parseInt(st.nextToken())));
                system.GUI.timerView.setSecond(String.format("%02d", Integer.parseInt(st.nextToken())));

                st = new StringTokenizer(str, " ");
                if (st.nextToken().equals("0") && st.nextToken().equals("0") && st.nextToken().equals("0")) {
                    mode = 0;
                    system.beepBuzzer();
                    // timer가 안주금.
                }
            }
        });

        type = 0;
    }

    public void requestTimerSettingMode() {
        changeMode(1);
    }

    public void requestSave() {
        timer.setTime(timeSettingValue[0], timeSettingValue[1], timeSettingValue[2]);
        type = 0;
        changeMode(0);
    }

    public void requestStartTimer() {
        String str = timer.getCurrentTime();
        StringTokenizer st = new StringTokenizer(str, " ");
        if (st.nextToken().equals("0") && st.nextToken().equals("0") && st.nextToken().equals("0")) {
        }
        else {
            changeMode(2);
            timer.startTime();
        }
    }

    public void requestResetTimer() {
        if (mode == 2)
            requestPauseTimer();
        timer.clearTime();
        changeMode(0);
    }

    public void requestPauseTimer() {
        timer.pauseTime();
        try {
            timer.getTimeThread().join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        changeMode(0);
    }

    public void changeMode(int mode) {
        this.mode = mode;
        if (this.mode == 0) {
            Arrays.fill(timeSettingValue, -1);
        }
        else if (this.mode == 1)
            Arrays.fill(timeSettingValue, 0);
    }

    public int[] getTimeSettingValue() {
        return timeSettingValue;
    }

    public void changeValue(int diff) {
        timeSettingValue[type] += diff;
        switch(type) {
            case 0:
                if (timeSettingValue[type] < timer.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = timer.TIME_BOTTOM_LIMIT;
                else if (timeSettingValue[type] > timer.HOUR_TOP_LIMIT)
                    timeSettingValue[type] = timer.HOUR_TOP_LIMIT;
                break;
            case 1:
                if (timeSettingValue[type] < timer.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = timer.TIME_BOTTOM_LIMIT;
                else if (timeSettingValue[type] > timer.MINUTE_TOP_LIMIT)
                    timeSettingValue[type] = timer.MINUTE_TOP_LIMIT;
                break;
            case 2:
                if (timeSettingValue[type] < timer.TIME_BOTTOM_LIMIT)
                    timeSettingValue[type] = timer.TIME_BOTTOM_LIMIT;
                else if (timeSettingValue[type] > timer.SECOND_TOP_LIMIT)
                    timeSettingValue[type] = timer.SECOND_TOP_LIMIT;
                break;
        }
    }

    public void changeType() { type = (type + 1) % TYPE_SIZE; }

    public int getMode() {
        return this.mode;
    }

    public int getType() {
        return this.type;
    }
}