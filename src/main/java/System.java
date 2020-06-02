import java.util.Arrays;

/**
 * @author Yoonseop Shin
 */
public class System extends Function {

    // 6개 중 4개 인스턴스만 갖고있음.
    // 알람, 알람커스텀은 항상 둘 다 포함되거나 포함되지 않아야 한다.
    MainFrame GUI;
    TimeKeeping timeKeeping;
    Stopwatch stopwatch;
    Timer timer;
    D_day d_day;
    Alarm alarm;
    AlarmCustom alarmCustom;
    Buzzer buzzer;
    Blink blink;
    private int functionNumIdx = 0;
    private int[] functionNum;
    private int selectedFid;
    private int status; // 비트마스킹: 0b00 0b01 0b10 0b11
    private int type;
    private Thread checkTimeOut;
    private long lastOperateTime;

    public System() {
        GUI = new MainFrame(this);

        functionNum = new int[4];
        Arrays.fill(functionNum, 0);
        functionNum[0] = 1;
        functionNum[1] = 2;
        functionNum[2] = 3;
        functionNum[3] = 4;
        status = 0b00;
        type = 0;
        selectedFid = 1;

        timeKeeping = new TimeKeeping(this);
        stopwatch = new Stopwatch(this);
        timer = new Timer(this);
        d_day = new D_day(this);
        alarm = null;
        alarmCustom = null;

        buzzer = new Buzzer();
        blink = new Blink();
    }

    public static void main(String[] args) {
        System system = new System();
    }

    public void startCheckTimeOut() {
        checkTimeOut = new Thread() {
            public void run() {
                Function curFunction;
                switch(functionNum[functionNumIdx]) {
                    case 1 : curFunction = timeKeeping; break;
                    case 2 : curFunction = stopwatch; break;
                    case 3 : curFunction = timer; break;
                    case 4 : curFunction = d_day; break;
                    case 5 : curFunction = alarm; break;
                    case 6 : curFunction = alarmCustom; break;
                    default: curFunction = null;
                }

                while(curFunction.getMode() == 1) {
                    try {
                        Thread.sleep(1000);
                        if(java.lang.System.currentTimeMillis() - lastOperateTime >= 3000) {
                            cancel(curFunction);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };

        checkTimeOut.start();
    }

    public void startBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0)
                    return;
                // GUI에 반영해야 함.
                timeKeeping.changeValue(1);
                int type = timeKeeping.getType();
                int[] timeSettingValue = timeKeeping.getTimeSettingValue();
                GUI.timekeepingView.setHour(String.format("%2s", timeSettingValue[0]));
                GUI.timekeepingView.setMinute(String.format("%2s", timeSettingValue[1]));
                GUI.timekeepingView.setCurTime2(String.format("%2s", timeSettingValue[2]));
                GUI.timekeepingView.setDate(String.format("%2s", timeSettingValue[3]).substring(2, 4)
                        + String.format("%2s", timeSettingValue[4])
                        + String.format("%2s", timeSettingValue[5]));
                // GUI type에 해당하는 부분이 깜빡이는 효과를 추가해야 함.

                break;
            case 2: // stopwatch

                break;
            case 3: // timer

                break;
            case 4: // d-day

                break;
            case 5: // alarm

                break;
            case 6: // alarm custom

                break;
        }
    }

    public void resetBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
    }

    public void selectBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    timeKeeping.requestTimeSettingMode();
                    startCheckTimeOut();
                }
                else {
                    timeKeeping.changeType();
                }
                break;
            case 2: // stopwatch

                break;
            case 3: // timer

                break;
            case 4: // d-day

                break;
            case 5: // alarm

                break;
            case 6: // alarm custom

                break;
        }

    }

    public void modeBtnPressed() {
        lastOperateTime = java.lang.System.currentTimeMillis();
        if (updateStatus() > -1)
            return;
        switch (selectedFid) {
            case 1: // timekeeping에서 현재시간 설정하는 것
                if (timeKeeping.getMode() == 0) {
                    nextFunction();
                }
                else {
                    timeKeeping.requestSave();
                }

                break;
            case 2: // stopwatch
                if (stopwatch.getMode() == 0) {
                    nextFunction();
                }

                break;
            case 3: // timer
                if (timer.getMode() == 0) {
                    nextFunction();
                }

                break;
            case 4: // d-day
                if (d_day.getMode() == 0) {
                    nextFunction();
                }

                break;
            case 5: // alarm
                if (alarm.getMode() == 0) {
                    nextFunction();
                }

                break;
            case 6: // alarm custom
                if (alarmCustom.getMode() == 0) {
                    nextFunction();
                }

                break;
        }
    }

//    @Override
//    public void timeout() {
//
//    }

    public void cancel(Function curFunction) {
        curFunction.changeMode();
    }

    @Override
    public void changeMode() {

    }


    public void changeType() {
        type = (type + 1) % 6;
    }

    public void changeValue(int diff) {
        // TODO implement here
    }

    public void setFunction(int[] selected) {
        // TODO implement here
    }

    public void selectFunction() {
        // TODO implement here
    }

    public void beepBuzzer() {
        // TODO implement here
    }

    public int updateStatus() {
        if (status == 3) {
            status = 1;
            return 2;
        }
        else if (status == 2) {
            status = 0;
            return 1;
        }
        else if (status == 1) {
            status = 0;
            return 0;
        }
        return -1;
    }

    public void set() {
        // TODO implement here
    }

    public void nextFunction() {
        functionNumIdx = (functionNumIdx + 1) % 4;
        selectedFid = functionNum[functionNumIdx];

        switch (selectedFid) {
            case 1:
                GUI.setView(GUI.timekeepingView);
                break;
            case 2:
                GUI.setView(GUI.stopwatchView);
                break;
            case 3:
                GUI.setView(GUI.timerView);
                break;
            case 4:
                GUI.setView(GUI.d_dayView);
                break;
            case 5:
                GUI.setView(GUI.alarmView);
                break;
            case 6:
                GUI.setView(GUI.alarmCustomView);
                break;
        }
    }
}